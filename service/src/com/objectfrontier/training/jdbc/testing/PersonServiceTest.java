package com.objectfrontier.training.jdbc.testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objectfrontier.training.jdbc.CsvParser;
import com.objectfrontier.training.jdbc.PersonService;
import com.objectfrontier.training.jdbc.dbconnection.ConnectionManager;
import com.objectfrontier.training.jdbc.exception.AppException;
import com.objectfrontier.training.jdbc.exception.ErrorCode;
import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;
import com.zaxxer.hikari.HikariDataSource;
import static com.objectfrontier.training.jdbc.Statements.RESET_TABLE;
@Test
public class PersonServiceTest {

    private PersonService personService;
    private Person person;
    private Address address;
    private static boolean flag;
    private ConnectionManager connectionManager;

    @BeforeClass
    private void init() throws SQLException {

        this.personService = new PersonService();
        this.connectionManager = new ConnectionManager();
    }

//    @BeforeTest
//    private void init() throws SQLException {
//        this.personService = new PersonService();
//        this.connection = DataBaseConnection.establishConnection();
//        this.person = new Person();
//        this.address = new Address();
//    }

//    @BeforeMethod
//    private void init() throws SQLException, IOException {
//        this.personService = new PersonService();
//        this.connection = ConnectionManager.getConnection();
//    }
//
//    @AfterMethod
//    private void release() throws SQLException {
//        ConnectionManager.release(connection, flag);
//    }

    @AfterTest
    private void reset() throws SQLException {

        Connection connection = connectionManager.get();
        PreparedStatement prepareStatement = connection.prepareStatement(RESET_TABLE);
        prepareStatement.execute();
        connection.close();
    }

    private static final String INPUTS_MSG = "Id:  = %s, Name = %s.";
    private static final String ASSERT_FAIL_MSG = " expected:<%s> \n but was:<%s>";

    @Test (dataProvider = "dpCreate_positive")
    private void testCreate_positive(Person personList, Person expectedPerson) throws SQLException, ParseException {

        Connection connection = connectionManager.get();
            try {
                System.out.println(System.currentTimeMillis());
                Person actualResult = this.personService.create(connection, personList);
                Assert.assertEquals(actualResult.toString(), expectedPerson.toString(), String.format(INPUTS_MSG, personList.getId(), personList.getFirstName()));
                flag = true;
                connectionManager.close(connection, flag);
            }catch (AppException e) {
                Assert.fail(String.format(INPUTS_MSG, person.getId(), person.getEmail() + String.format(ASSERT_FAIL_MSG, personList, expectedPerson)));
//                String actualErrCode = e.getErrorMessage();
//                Assert.assertEquals(actualErrCode, expectedErrCode, String.format(INPUTS_MSG, person.getId(), person.getEmail()));
                flag = false;
                connectionManager.close(connection, flag);
            }
        }

//    @Test (dataProvider = "dpCreate_positiveOne")
//    private void testCreate_positiveOne(Person person, Person expectedResult) throws SQLException, ParseException {
//
//        Connection connection = connectionManager.getConnection();
//        Person actualResult = this.personService.create(connection, person);
//        Assert.assertEquals(actualResult.toString(), expectedResult.toString(), String.format(INPUTS_MSG, person.getId(), person.getFirstName()));
//        flag = true;
//        connectionManager.release(connection, flag);
//    }
//
//    @Test (dataProvider = "dpCreate_negative")
//    private void testCreatePersonDuplicate_negative(Person person, String expectedErrCode) throws SQLException, ParseException {
//
//        Connection connection = connectionManager.getConnection();
//        try { 
//            Person actualResult = this.personService.create(connection, person);
//            Assert.fail(String.format(INPUTS_MSG, person.getId(), person.getEmail() + String.format(ASSERT_FAIL_MSG, actualResult, expectedErrCode)));
//        }catch (AppException e) {
//            String actualErrCode = e.getErrorMessage();
//            Assert.assertEquals(actualErrCode, expectedErrCode, String.format(INPUTS_MSG, person.getId(), person.getEmail()));
//            flag = false;
//            connectionManager.release(connection, flag);
//        }
//    }
//
//    @Test (dataProvider = "dpCreate_negative")
//    private void testCreate_negative(Person person, String expectedErrCode) throws SQLException, ParseException {
//
//        Connection connection = connectionManager.getConnection();
//        try { 
//            Person actualResult = this.personService.create(connection, person);
//            Assert.fail(String.format(INPUTS_MSG, person.getId(), person.getEmail() + String.format(ASSERT_FAIL_MSG, actualResult, expectedErrCode)));
//
//        }catch (AppException e) {
//            String actualErrCode = e.getErrorMessage();
//            Assert.assertEquals(actualErrCode, expectedErrCode, String.format(INPUTS_MSG, person.getId(), person.getEmail()));
//            flag = false;
//            connectionManager.release(connection, flag);
//        }
//    }
//
//    @Test (dataProvider = "dpRead_positive")
//    private void testRead_positive(int acutalPersonId, Person expectedResult, boolean whetherToReadAddress) throws AppException, SQLException {
//
//        Connection connection = connectionManager.getConnection();
//        PersonService personService = new PersonService();
//        Person actualResult = personService.read(connection, acutalPersonId, whetherToReadAddress);
//        Assert.assertEquals(actualResult, expectedResult, String.format(INPUTS_MSG, expectedResult.getEmail(), expectedResult.getAddress().getPostalCode()));
//        flag = true;
//        connectionManager.release(connection, flag);
//    }
//
//    @Test (dataProvider = "dpRead_negative")
//    private void testRead_negative(long actualPersonId, String expectedResult, boolean flag) throws SQLException {
//
//        Connection connection = connectionManager.getConnection();
//        try { 
//            Person actualResult = personService.read(connection, actualPersonId, flag);
//            Assert.fail(String.format(INPUTS_MSG, actualResult, expectedResult + String.format(ASSERT_FAIL_MSG, expectedResult, actualResult)));
//        }catch (Exception e) {
//            String actualErrCode = e.getMessage();
//            Assert.assertEquals(actualErrCode, expectedResult, String.format(INPUTS_MSG, expectedResult, actualPersonId));
//            flag = false;
//            connectionManager.release(connection, flag);
//        }
//    }
//
//
//    @Test (dataProvider = "dpUpdate_positive")
//    private void testUpdate_positive(Person updatePerson, Person expectedResult, boolean whetherToUpdateAddress) throws AppException, SQLException {
//
//        Connection connection = connectionManager.getConnection();
//            PersonService personService = new PersonService();
//            Person actualResult = personService.update(connection, updatePerson, whetherToUpdateAddress);
//            Assert.assertEquals(actualResult, expectedResult, String.format(INPUTS_MSG, updatePerson, expectedResult));
//            flag = true;
//            connectionManager.release(connection, flag);
//    }
//
//    @Test (dataProvider = "dpUpdate_negative")
//    private void testUpdate_negative(Person person, String expectedResult, boolean flag) throws SQLException {
//
//        Connection connection = connectionManager.getConnection();
//        try { 
//            PersonService personService = new PersonService();
//            Person actualResult = personService.update(connection, person, flag);
//            Assert.fail(String.format(INPUTS_MSG, actualResult, expectedResult + String.format(ASSERT_FAIL_MSG, expectedResult, actualResult)));
//        }catch (Exception e) {
//            String actualErrCode = e.getMessage();
//            Assert.assertEquals(actualErrCode, expectedResult, String.format(INPUTS_MSG, expectedResult, expectedResult));
//            flag = false;
//            connectionManager.release(connection, flag);
//           
//        }
//    }
//
//  @Test (dataProvider = "dpDelete_positive")
//  private void testDelete_positive(Person person, int expectedResult) throws AppException, SQLException {
//
//      Connection connection = connectionManager.getConnection();
//          PersonService personService = new PersonService();
//          int actualResult = personService.delete(connection, person);
//          Assert.assertEquals(actualResult, expectedResult, String.format(INPUTS_MSG, expectedResult, expectedResult));
//          flag = true;
//          connectionManager.release(connection, flag);
//  }
//
//    @Test (dataProvider = "dpDelete_negative")
//    private void testDelete_negative(Person person, String expectedResult) throws SQLException {
//
//        Connection connection = connectionManager.getConnection();
//        try {
//            PersonService personService = new PersonService();
//            int actualResult = personService.delete(connection, person);
//            Assert.fail(String.format(INPUTS_MSG, actualResult, expectedResult + String.format(ASSERT_FAIL_MSG, expectedResult, actualResult)));
//        }catch (Exception e) {
//            String actualErrCode = e.getMessage();
//            Assert.assertEquals(actualErrCode, expectedResult, String.format(INPUTS_MSG, expectedResult, expectedResult));
//            flag = false;
//            connectionManager.release(connection, flag);
//           
//        }
//    }

//    @Test (dataProvider = "dpReadAll_positive")
//    private void testReadAll_positive(Person expectedPerson) throws SQLException {
//        
//        Connection connection = connectionManager.getConnection();
//        List<Person> actualResult = this.personService.readAll(connection);
//        Assert.assertEquals(actualResult.toString(), expectedPerson.toString(), String.format(INPUTS_MSG, expectedPerson, expectedPerson));
//        flag = true;
//        connectionManager.release(connection, flag);
//    }

    @DataProvider (parallel = true)
    Object[][] dpCreate_positive() throws IOException, URISyntaxException {

        Object[][] personList = CsvParser.createPositiveParser();
        return personList;
    }

//    @DataProvider
//    Object[][] dpCreate_positiveOne() throws SQLException, ParseException, IOException {
//
//
//        Address address = new Address("ramapuram", "chennai", 600076);
//        Person actualPersonToBeInserted = new Person("as", "sn", "m.com", "27-02-1995", address);
//
//        Person expectedPerson = new Person("surya", "narayanan", "mc", "27-02-1995", address);
//
//
//        return new Object[][] { {actualPersonToBeInserted, expectedPerson} };
//    }

    @DataProvider
    Object[][] dpCreate_negative() throws SQLException, URISyntaxException, IOException {

        Object[][] personList = CsvParser.createNegativeParser();
        return personList;
    }

    @DataProvider
    Object[][] dpCreate_positiveOne() throws SQLException {

        Address addressWithoutFirstName = new Address("t.nagar", "chennai", 600001); 
        Person person = new Person("kar", "narayanasamy", "k@gmail.com", "12-12-1996", addressWithoutFirstName);
        Person expectedPerson = person;

        return new Object[][] { {person, expectedPerson} };
    }

    @DataProvider
    Object[][] dpRead_positive() throws SQLException {

        Person actualPersonToBeInserted = new Person();
        actualPersonToBeInserted.setId(12);
        long actualPersonId = actualPersonToBeInserted.getId();
        Address address = new Address(46, "ramapuram", "chennai", 600085);
        Person expectedPerson = new Person("miru", "miru@gmail.com", "27-02-1995", "sn", address);

        boolean whetherToReadAddress = true;
        return new Object[][] { {actualPersonId, expectedPerson, whetherToReadAddress} };
    }

    @DataProvider
    Object[][] dpRead_negative() throws SQLException {

        Person actualPersonToBeInserted = new Person();
        actualPersonToBeInserted.setId(145);
        long actualPersonId = actualPersonToBeInserted.getId();
        String expectedError = "id not found";

        return new Object[][] { {actualPersonId, expectedError, false}};
    }

    @DataProvider
    Object[][] dpReadAll_positive() throws SQLException {
    
        Address address = new Address();
        List<Person> expectedPerson = new ArrayList<>();
        Person expectedPersonDataOne = new Person();

        address.setStreet("ICF");
        address.setCity("chennai");
        address.setPostalCode(466456);
        address.setId(105);
        expectedPersonDataOne.setAddress(address);
        expectedPersonDataOne.setFirstName("ashwin");
        expectedPersonDataOne.setLastName("ashwin");
        expectedPersonDataOne.setEmail("ash@gmail.com");
        expectedPersonDataOne.setBirthDate("06-06-1996");

        expectedPerson.add(expectedPersonDataOne);
        
        Person expectedPersonDataTwo = new Person();

        address.setStreet("t.nagar");
        address.setCity("chennai");
        address.setPostalCode(600017);
//        expectedPerson.setId(80);
        expectedPersonDataTwo.setAddress(address);
        expectedPersonDataTwo.setFirstName("karthikn");
        expectedPersonDataTwo.setLastName("karthikn");
        expectedPersonDataTwo.setEmail("jaaaaa24@gmail.com");
        expectedPersonDataTwo.setBirthDate("06-06-1996");
        expectedPerson.add(expectedPersonDataTwo);

        return new Object[][] { {expectedPerson} };
    }

    @DataProvider
    Object[][] dpUpdate_positive() throws SQLException {

        Address address = new Address(45, "chrompet", "chennai", 600077);
        Person updatePerson = new Person(11, "priyanka", "priya", "priya261997.ps@gmail.com", "26-02-1997", address);
        Person expectedPerson = new Person(11, "priyanka", "priya", "priya261997.ps@gmail.com", "26-02-1997", address);

        boolean whetherToUpdateAddress = true;
        return new Object[][] { {updatePerson, expectedPerson, whetherToUpdateAddress} };
    }

    @DataProvider
    Object[][] dpUpdate_negative() throws SQLException {

        Address address = new Address(255, "chrompet", "chennai", 600077);
        Person updatePerson = new Person("priyanka", "priya261997.ps@gmail.com", "26-02-1997", "priya", address); 

        boolean whetherToUpdateAddress = true;
        String expectedPerson = "id not found"; 
        return new Object[][] { {updatePerson, expectedPerson, whetherToUpdateAddress}};
    }
    @DataProvider
    Object[][] dpDelete_positive() throws SQLException, URISyntaxException, IOException {

        Address address = new Address();
        Person person = new Person();
        int data = CsvParser.deleteParser();
        int expectedPerson = 1;

        address.setId(12);
        person.setId(46);
        person.setAddress(address);

        return new Object[][] { {person, expectedPerson} };
    }

    @DataProvider
    Object[][] dpDelete_negative() throws SQLException {

        Address address = new Address();
        Person person = new Person();

        person.setId(256);
        address.setId(257);
        person.setAddress(address);
        String expectedError = "id not found";

        return new Object[][] { {person, expectedError} };
    }
}

