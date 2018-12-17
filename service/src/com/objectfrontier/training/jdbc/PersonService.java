package com.objectfrontier.training.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.objectfrontier.training.jdbc.exception.AppException;
import com.objectfrontier.training.jdbc.exception.ErrorCode;

import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;

import static com.objectfrontier.training.jdbc.Statements.PERSON_CREATE_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.PERSON_READ_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.PERSON_READALL_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.PERSON_UPDATE_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.PERSON_DELETE_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.PERSON_NAME_VALIDATION_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.PERSON_EMAIL_DUPLICATE_STATEMENT;
import static com.objectfrontier.training.jdbc.Constants.PERSON_ID;
import static com.objectfrontier.training.jdbc.Constants.FIRST_NAME;
import static com.objectfrontier.training.jdbc.Constants.LAST_NAME;
import static com.objectfrontier.training.jdbc.Constants.ADDRESS_ID;
import static com.objectfrontier.training.jdbc.Constants.BIRTH_DATE;
import static com.objectfrontier.training.jdbc.Constants.EMAIL;
import static com.objectfrontier.training.jdbc.Constants.CREATED_DATE;
import static com.objectfrontier.training.jdbc.Constants.BIRTH_DATE_FORMAT;

public class PersonService {

    public int delete(Connection connection, Person person) throws SQLException, AppException {

        PreparedStatement prepareStatement = connection.prepareStatement(PERSON_DELETE_STATEMENT);
        prepareStatement.setLong(1, person.getId());
        Integer rowsAffected = prepareStatement.executeUpdate();

        long addressId = person.getAddress().getId();
        AddressService addressService = new AddressService();
        addressService.delete(connection, addressId);

        if (rowsAffected == 0) { throw new AppException(ErrorCode.INVALID_ID); }

        return rowsAffected;
    }

    public Person update(Connection connection, Person person, boolean updatedOnlyPersonDetails)
            throws SQLException, AppException {

            PreparedStatement prepareStatement = connection.prepareStatement(PERSON_UPDATE_STATEMENT);
            prepareStatement.setString(1, person.getFirstName());
            prepareStatement.setString(2, person.getLastName());
            prepareStatement.setString(3, person.getEmail());
            prepareStatement.setString(4, person.getBirthDate());
            prepareStatement.setLong(5, person.getId());
            int rowsAffected = prepareStatement.executeUpdate();

            if (updatedOnlyPersonDetails == true) { 
                AddressService addressService = new AddressService();
                addressService.update(connection, person.getAddress());
            }

           if (rowsAffected == 0) { throw new AppException(ErrorCode.INVALID_ID); }

        return person;
    }

    public List<Person> readAll(Connection connection) throws SQLException {

        PreparedStatement prepareStatement = connection.prepareStatement(PERSON_READALL_STATEMENT);
        ResultSet resultSet = prepareStatement.executeQuery();
        List<Person> addReadedPerson = new ArrayList<>();

        while (resultSet.next()) {
            Person person = constructPerson(resultSet);
            Address address = new Address();
            long addressId = resultSet.getLong(ADDRESS_ID);
            address.setId(addressId);
            person.setAddress(address);
            addReadedPerson.add(person);
        }

        AddressService addressService = new AddressService();
        List<Address> addresses = addressService.readAll(connection);
        Stream<Address> addressStream = addresses.stream();
        for (Person person : addReadedPerson) {
            Address address = getAddressForThisPerson(addressStream, person);
            person.setAddress(address);
        }
        return addReadedPerson;

    }

    private Address getAddressForThisPerson(Stream<Address> addressStream, Person person) {
        return addressStream.filter((addr) -> person.getAddress().getId() == addr.getId()).findFirst().get();
    }

    public Person read(Connection connection, long personId, boolean addressFlag) throws SQLException, AppException {

        PreparedStatement prepareStatement = connection.prepareStatement(PERSON_READ_STATEMENT);
        prepareStatement.setLong(1, personId);
        ResultSet resultSet = prepareStatement.executeQuery();

        Person person = null;
        while (resultSet.next()) {
            person = constructPerson(resultSet);

            if (addressFlag == true) {
                long addressId = resultSet.getLong(ADDRESS_ID);
                AddressService addressService = new AddressService();
                Address address = addressService.read(connection, addressId);
                person.setAddress(address);
            }
        }
        

        if (person == null) { throw new AppException(ErrorCode.INVALID_ID); }

        return person;
    }

    private Person constructPerson(ResultSet resultSet) throws SQLException {

        Person person = new Person();
        person.setFirstName(resultSet.getString(FIRST_NAME));
        person.setLastName(resultSet.getString(LAST_NAME));
        person.setEmail(resultSet.getString(EMAIL));
        person.setBirthDate(resultSet.getString(BIRTH_DATE));
        person.setCreateDate(resultSet.getTimestamp(CREATED_DATE));
        person.setId(resultSet.getInt(PERSON_ID));
        return person;
    }

    public Person create(Connection connection, Person person) throws SQLException, ParseException, AppException {

        checkPersonNameDuplication(connection, person);
        checkPersonEmailDuplication(connection, person);
        personDetailValidation(person);
        validate(connection, person);
        AddressService addressService = new AddressService();

        Address createdAddress = addressService.create(connection, person.getAddress());
        person.setAddress(createdAddress);

        PreparedStatement prepareStatement = connection.prepareStatement(PERSON_CREATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
        
        long createdDateAsMillis = Instant.now().toEpochMilli();
//        long createdDateAsMillis = Instant.now().atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
        prepareStatement.setString(1, person.getFirstName());
        prepareStatement.setString(2, person.getLastName());
        prepareStatement.setString(3, person.getEmail());
        long birthDateInMilliSeconds = dateFormat(person.getBirthDate());
        prepareStatement.setDate(4, new Date(birthDateInMilliSeconds));
        prepareStatement.setLong(5, person.getAddress().getId());
        prepareStatement.setTimestamp(6, new Timestamp(createdDateAsMillis));
        prepareStatement.executeUpdate();
        ResultSet resultSet = prepareStatement.getGeneratedKeys();

        if (resultSet.next()) { person.setId(resultSet.getInt(1)); }
//        if (resultSet.getMetaData().isAutoIncrement(1) == false) {
//            throw new AppException(ErrorCode.DUPLICATE_ENTRY);
//        }
        return person;
//        else {throw new CustomException ("error"); }
    }

    private void validate(Connection connection, Person person) {

        
    }

    private  boolean personDetailValidation(Person personToCreate) throws AppException {

        if (isBlank(personToCreate.getFirstName())) { throw new  AppException(ErrorCode.FIRST_NAME_NULL); }
        if (isBlank(personToCreate.getLastName())) { throw new   AppException(ErrorCode.LAST_NAME_NULL); }
        if (isBlank(personToCreate.getBirthDate())) { throw new  AppException(ErrorCode.BIRTH_DATE_NULL); }
        if (isBlank(personToCreate.getEmail())) { throw new         AppException(ErrorCode.EMAIL_NULL); }

        return true;
    }

    private static boolean isBlank(String input) {

        return input.trim().length() <= 0 || input == null;
    }

    private boolean checkPersonNameDuplication(Connection connection, Person personToCheck) throws SQLException, AppException {

        PreparedStatement validationStatement = connection.prepareStatement(PERSON_NAME_VALIDATION_STATEMENT);
        validationStatement.setString(1, personToCheck.getFirstName());
        validationStatement.setString(2, personToCheck.getLastName());

        ResultSet validationResult = validationStatement.executeQuery();

        if (validationResult.next()) { throw new AppException(ErrorCode.DUPLICATE_PERSON_NAME); }

        return true;
    }

    private boolean checkPersonEmailDuplication(Connection connection, Person personToCheck) throws SQLException {

        PreparedStatement validationStatement = connection.prepareStatement(PERSON_EMAIL_DUPLICATE_STATEMENT);
        validationStatement.setString(1, personToCheck.getEmail());


        ResultSet validationResult = validationStatement.executeQuery();

        if (validationResult.next()) { throw new AppException(ErrorCode.DUPLICATE_MAIL_ENTRY); }

        return true;
    }

    private long dateFormat(String personBirthDate) throws ParseException {

        try {
        SimpleDateFormat dateFormat = new SimpleDateFormat(BIRTH_DATE_FORMAT);
        java.util.Date date = dateFormat.parse(personBirthDate);
        dateFormat.setLenient(false);
        long birthDateInMilliSeconds = date.toInstant().toEpochMilli();
        return birthDateInMilliSeconds;
        }catch (ParseException e) {
//            throw (e); 
            throw new AppException(ErrorCode.BIRTH_DATE_FORMAT);
        }
    }

}
