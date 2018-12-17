package com.objectfrontier.training.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.objectfrontier.training.jdbc.exception.AppException;
import com.objectfrontier.training.jdbc.exception.ErrorCode;
import com.objectfrontier.training.jdbc.pojo.Address;

import static com.objectfrontier.training.jdbc.Constants.ADDRESS_ID;
import static com.objectfrontier.training.jdbc.Constants.STREET;
import static com.objectfrontier.training.jdbc.Constants.CITY;
import static com.objectfrontier.training.jdbc.Constants.POSTAL_CODE;

import static com.objectfrontier.training.jdbc.Statements.ADDRESS_CREATE_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_DELETE_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_READ_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_READALL_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_SEARCH_CITY_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_SEARCH_DEFAULT_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_SEARCH_POSTAL_CODE_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_SEARCH_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_SEARCH_STREET_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.ADDRESS_UPDATE_STATEMENT;
import static com.objectfrontier.training.jdbc.Statements.OR;

public class AddressService {

    public List<Address> search(Connection connection, String[] fieldName, String searchText) throws SQLException {

        int fieldCount = fieldName.length - 1;

        StringBuilder searchStatement = new StringBuilder().append(ADDRESS_SEARCH_STATEMENT);

        for (int i = 0; i <= fieldCount; i++) {
            if (fieldName[i].equalsIgnoreCase(STREET)) { fieldName[i] = STREET; }
                else if (fieldName[i].equalsIgnoreCase(CITY)) { fieldName[i] = CITY; }
                else if (fieldName[i].equalsIgnoreCase(POSTAL_CODE)) { fieldName[i] = POSTAL_CODE; }

            String columnName = fieldName[i];

            if (i == 0) { searchStatement.append(getStatement(columnName)); } 
                else { 
                    searchStatement.append(OR);
                    searchStatement.append(getStatement(columnName));
                }
            }

        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        prepareStatement = connection.prepareStatement(searchStatement.toString());

        for (int i = 0; i <= fieldCount; i++) { prepareStatement.setString(i + 1, "%" + searchText); }
        resultSet = prepareStatement.executeQuery();

        Address searchAddress = null;
        List<Address> searchList = new ArrayList<>();
        while (resultSet.next()) {
            searchAddress = constructAddress(resultSet);
            searchList.add(searchAddress);
        }

        return searchList;
    }

    private String getStatement(String columnName) {

        switch (columnName) {

        case STREET:        return ADDRESS_SEARCH_STREET_STATEMENT;

        case CITY:          return ADDRESS_SEARCH_CITY_STATEMENT;

        case POSTAL_CODE:   return ADDRESS_SEARCH_POSTAL_CODE_STATEMENT;

        default:            return ADDRESS_SEARCH_DEFAULT_STATEMENT;

        }

    }

    public int delete(Connection connection, long addressIdTOBeDelete) throws SQLException, AppException {

        PreparedStatement prepareStatement = connection.prepareStatement(ADDRESS_DELETE_STATEMENT);
        prepareStatement.setLong(1, addressIdTOBeDelete);
        Integer rowsAffected = prepareStatement.executeUpdate();

        if (rowsAffected == 0) { throw new AppException(ErrorCode.INVALID_ID); }

        return rowsAffected;
    }

    public Address update(Connection connection, Address address) throws SQLException, AppException {

        addressDetailValidation(address);

        PreparedStatement prepareStatement = connection.prepareStatement(ADDRESS_UPDATE_STATEMENT);
        prepareStatement.setString(1, address.getStreet());
        prepareStatement.setString(2, address.getCity());
        prepareStatement.setInt(3, address.getPostalCode());
        prepareStatement.setLong(4, address.getId());
        Integer rowsAffected = prepareStatement.executeUpdate();

        if (rowsAffected == 0) { throw new AppException(ErrorCode.INVALID_ID); }

        return address;
    }

    public List<Address> readAll(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(ADDRESS_READALL_STATEMENT);
        List<Address> addReadedAddress = new ArrayList<>();

        while (resultSet.next()) {
            Address readAllAddress = constructAddress(resultSet);
            addReadedAddress.add(readAllAddress);
        }
        return addReadedAddress;
    }

    public Address read(Connection connection, long addressId) throws SQLException, AppException {

        PreparedStatement prepareStatement = connection.prepareStatement(ADDRESS_READ_STATEMENT);
        prepareStatement.setLong(1, addressId);
        ResultSet resultSet = prepareStatement.executeQuery();

        Address address = null;
        while (resultSet.next()) { address = constructAddress(resultSet); }

        if (address == null) { throw new AppException(ErrorCode.INVALID_ID); }

        return address;
    }

    private Address constructAddress(ResultSet resultSet) throws SQLException {

        Address address = new Address();
        address.setId(resultSet.getInt(ADDRESS_ID));
        address.setStreet(resultSet.getString(STREET));
        address.setCity(resultSet.getString(CITY));
        address.setPostalCode(resultSet.getInt(POSTAL_CODE));
        return address;
    }

    public Address create(Connection connection, Address address) throws SQLException {

        ResultSet rs = null;
        PreparedStatement prepareStatement = null;
        prepareStatement = connection.prepareStatement(ADDRESS_CREATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);

        prepareStatement.setString(1, address.getStreet());
        prepareStatement.setString(2, address.getCity());
        int postalCode = postalCodeFormat(address.getPostalCode());
        prepareStatement.setInt(3, postalCode);
        prepareStatement.executeUpdate();
        rs = prepareStatement.getGeneratedKeys();

        while (rs.next()) { address.setId(rs.getInt(1)); }

//        if (rs.getMetaData().isAutoIncrement(1)) {System.out.println("yes");} 
//        else { throw new AppException(ErrorCode.INVALID_ID); }
        return address;
    }

    private int postalCodeFormat(int postalCode) {

        if (9999 < postalCode || postalCode > 999999) { return postalCode; }
            else { throw new AppException(ErrorCode.POSTAL_CODE_FORMAT); }
    }

    private static boolean addressDetailValidation(Address addressToCreate) throws AppException {

        if (isBlank(addressToCreate.getStreet())) { throw new  AppException(ErrorCode.STREET_NULL); }
        if (isBlank(addressToCreate.getCity())) { throw new   AppException(ErrorCode.CITY_NULL); }
        if (addressToCreate.getPostalCode() == 0) { throw new  AppException(ErrorCode.POSTAL_CODE); }

        return true;
    }

    private static boolean isBlank(String input) {
        return input.trim().length() <= 0 || input == null;
    }

}
