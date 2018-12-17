package com.objectfrontier.training.jdbc.testing;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.jdbc.AddressService;
import com.objectfrontier.training.jdbc.dbconnection.ConnectionManager;
import com.objectfrontier.training.jdbc.exception.AppException;
import com.objectfrontier.training.jdbc.pojo.Address;


    @Test
    public class AddressServiceTest {
        private AddressService addressService;
        private Connection connection;
        private ConnectionManager connectionManager;
        private boolean flag;


    @BeforeTest
    private void init() throws SQLException {
        this.addressService = new AddressService();
        this.connection = connectionManager.get();
    }

//    @AfterTest
//    private void close() throws SQLException {
//       connection.close();
//    }

    private static final String INPUTS_MSG = "Id:  = %s, Name = %s.";
    private static final String ASSERT_FAIL_MSG = " expected:<%s> \n but was:<%s>";

    @Test (dataProvider = "dpCreate_positive")
    private void testCreate_positive(Address address, Address expectedResult) throws SQLException {

        Connection connection = connectionManager.get();

            Address actualResult = this.addressService.create(connection, address);
            Assert.assertEquals(actualResult, expectedResult, String.format(INPUTS_MSG, address.getId(), address.getCity()));
            flag = true;
            connectionManager.close(connection, flag);

    }

  @Test (dataProvider = "dpCreate_negative_postalCode")
  private void testCreate_negative_postalCode(Address address, String expectedResult) throws SQLException {

      Connection connection = connectionManager.get();

      try { 
          this.addressService.create(connection, address);
          Assert.fail(String.format(INPUTS_MSG, address, expectedResult + String.format(ASSERT_FAIL_MSG, expectedResult, address), expectedResult));
          flag = false;
          connectionManager.close(connection, flag);
      }catch (AppException e) {
          String actualResult = e.getErrorMessage();
          Assert.assertEquals(actualResult, expectedResult, String.format(INPUTS_MSG, address.getId(), address.getCity()));
      }
  }

    @Test (dataProvider = "dpRead_positive")
    private void testRead_positive(long acutalAddressId, Address expectedAddress) throws AppException, SQLException {

        Connection connection = connectionManager.get();

            Address actualResult = this.addressService.read(connection, acutalAddressId);
            Assert.assertEquals(actualResult.toString(), expectedAddress.toString(), String.format(INPUTS_MSG, expectedAddress.getCity(), expectedAddress.getStreet()));
            flag = true;
            connectionManager.close(connection, flag);
    }

    @Test (dataProvider = "dpRead_negative")
    private void testRead_negative(long actualPersonId, String expectedResult) throws SQLException {
        Connection connection = connectionManager.get();

     try {
            Address actualResult = addressService.read(connection, actualPersonId);
            Assert.fail(String.format(INPUTS_MSG, actualResult, expectedResult + String.format(ASSERT_FAIL_MSG, expectedResult, actualResult)));
            flag = false;
            connectionManager.close(connection, flag);
        }catch (Exception e) {
            String actualErrCode = e.getMessage();
            Assert.assertEquals(actualErrCode, expectedResult, String.format(INPUTS_MSG, expectedResult, actualPersonId));
        }
    }
//
////    @Test (dataProvider = "dpReadAll_positive")
////    private void testReadAll_positive(List<Address> expectedAddress) {
////    
////        try { 
////            List<Address> actualResult = this.addressService.readAll(connection);
////            Assert.assertEquals(actualResult.toString(), expectedAddress.toString(), String.format(INPUTS_MSG, "hii", ""));
////        }catch (Exception e) {
////            Assert.fail(String.format(INPUTS_MSG, "", "") + String.format(ASSERT_FAIL_MSG, "", e.getMessage()), e);
////        }
////    }

    @Test (dataProvider = "dpUpdate_positive")
    private void testUpdate_positive(Address updateAddress, Address expectedResult) throws AppException, SQLException {

        Connection connection = connectionManager.get();

        Address actualResult = this.addressService.update(connection, updateAddress);
        Assert.assertEquals(actualResult.toString(), expectedResult.toString(), String.format(INPUTS_MSG, updateAddress.getId(), updateAddress.getStreet()));
        flag = true;
        connectionManager.close(connection, flag);

    }

    @Test (dataProvider = "dpUpdate_negative")
    private void testUpdate_negative(Address address, String expectedResult) throws SQLException {

        Connection connection = connectionManager.get();

        try { 
            Address actualResult = addressService.update(connection, address);
            Assert.fail(String.format(INPUTS_MSG, actualResult, expectedResult + String.format(ASSERT_FAIL_MSG, expectedResult, actualResult)));
            flag = false;
            connectionManager.close(connection, flag);
        }catch (Exception e) {
            String actualErrCode = e.getMessage();
            Assert.assertEquals(actualErrCode, expectedResult, String.format(INPUTS_MSG, expectedResult, expectedResult));
        }
    }

    @Test (dataProvider = "dpDelete_positive")
    private void testDelete_positive(long deleteAddressId, int expectedResult) throws AppException, SQLException {

        Connection connection = connectionManager.get();

        int actualResult = this.addressService.delete(connection, deleteAddressId);
        Assert.assertEquals(actualResult, expectedResult, String.format(INPUTS_MSG, deleteAddressId, deleteAddressId));
        flag = true;
        connectionManager.close(connection, flag);
    }

    @Test (dataProvider = "dpDelete_negative")
    private void testDelete_negative(long addressId, String expectedResult) throws SQLException {

        Connection connection = connectionManager.get();

        try { 
            int actualResult = this.addressService.delete(connection, addressId);
            Assert.fail(String.format(INPUTS_MSG, actualResult, expectedResult + String.format(ASSERT_FAIL_MSG, expectedResult, actualResult)));
            flag = false;
            connectionManager.close(connection, flag);
        }catch (Exception e) {
            String actualErrCode = e.getMessage();
            AssertJUnit.assertEquals(actualErrCode, expectedResult, String.format(INPUTS_MSG, expectedResult, expectedResult));
        }
    }

    @Test (dataProvider = "dpReadAll_negative")
    private void testReadAll_negative(List<Address> expectedAddresses) throws SQLException {

        try {
            List<Address> actualAddresses = addressService.readAll(connection);
            Assert.assertEquals(actualAddresses, expectedAddresses);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test (dataProvider = "dpSearch_positive")
    private void testSearch_positive(String[] fieldName, String searchText, List<Address> expectedAddress) throws SQLException {

        List<Address> actualResult = this.addressService.search(connection, fieldName, searchText);
        Assert.assertEquals(actualResult.toString(), expectedAddress.toString(), String.format(INPUTS_MSG, expectedAddress, expectedAddress));

    }

    @DataProvider
    Object[][] dpCreate_positive() throws SQLException {

        Address addressToBeInserted = new Address("t.nagar", "chennai", 600011);
        Address expectedAddress = new Address("t.nagar", "chennai", 600011);

        return new Object[][] { {addressToBeInserted , expectedAddress } };
    }

    @DataProvider
    Object[][] dpCreate_negative_postalCode() throws SQLException {

        Address addressToBeInserted = new Address("t.nagar", "chennai", 6000);
        String expectedResult = "pin code is too small";

        return new Object[][] { {addressToBeInserted , expectedResult } };
    }

    @DataProvider
    Object[][] dpCreate_negative() throws SQLException {

        Address addressWithoutStreet = new Address("", "chennai", 6000);
        Address addressWithoutCity = new Address("t.nagar", "", 6000);
        Address addressWithoutPostalCode = new Address("t.nagar", "chennai", 0);
        String expectedResultForStrret = "street is empty"; 
        String expectedResultForCity = "city is empty";
        String expectedResultForPostalCode = "pin code is empty";
                

        return new Object[][] { {addressWithoutStreet , expectedResultForStrret },
                                {addressWithoutCity, expectedResultForCity},
                                {addressWithoutPostalCode, expectedResultForPostalCode}
                              };
    }

    @DataProvider
    Object[][] dpRead_positive() throws SQLException {

        long actualAddressId = 136;
        Address expectedAddress = new Address(136, "t.nagar", "chennai", 600017);

        return new Object[][] { {actualAddressId, expectedAddress} };
    }

    @DataProvider
    Object[][] dpRead_negative() throws SQLException {

//        Connection connection = get.get();

        long actualAddressId = 256;
        String expectedError = "ID not found";

        return new Object[][] { {actualAddressId, expectedError} };
    }

    @DataProvider
    Object[][] dpReadAll_positive() throws SQLException {

        long actualAddressId = 136;
        List<Address> expectedAddress = new ArrayList<>();
        Address expectedAddressOne = new Address();
        expectedAddressOne.setId(actualAddressId);
        expectedAddressOne.setStreet("t.nagar");
        expectedAddressOne.setCity("chennai");
        expectedAddressOne.setPostalCode(600017);
        expectedAddress.add(expectedAddressOne);

        Address expectedAddressTwo = new Address();
        expectedAddressTwo.setId(137);
        expectedAddressTwo.setStreet("t.nagar");
        expectedAddressTwo.setCity("chennai");
        expectedAddressTwo.setPostalCode(600017);
        expectedAddress.add(expectedAddressTwo);

        return new Object[][] { {expectedAddressTwo} };
    }

    @DataProvider
    Object[][] dpUpdate_positive() throws SQLException {

        Address addressTOBeUpdated = new Address(25, "royapettah", "chennai", 600067);
        Address expectedAddress = new Address(25, "royapettah", "chennai", 600067);

        return new Object[][] { {addressTOBeUpdated, expectedAddress} };
    }

    @DataProvider
    Object[][] dpUpdate_negative() throws SQLException {

        Address addressTOBeUpdated = new Address(254, "royapettah", "chennai", 600067);
        String expectedAddress = "ID not found";

        return new Object[][] { {addressTOBeUpdated, expectedAddress} };
    }

    @DataProvider
    Object[][] dpDelete_positive() throws SQLException {

        Address address = new Address();
        address.setId(11);
        long addressId = address.getId();
        int addressRowExpected = 1;

        return new Object[][] { {addressId, addressRowExpected} };
    }

    @DataProvider
    Object[][] dpDelete_negative() throws SQLException {

        Address address = new Address();

        address.setId(256);
        long addressId = address.getId();
        String expectedError = "ID not found";

        return new Object[][] { {addressId, expectedError}};
    }

    @DataProvider
    Object[][] dpSearch_positive() throws SQLException {

      String[] fieldName = {"Street", "City", "postal_code"};
      String searchText = "34";
      List<Address> address = new ArrayList<>();
      Address expectedAddress = new Address(17, "fds", "dfss", 34);
      address.add(expectedAddress);

      return new Object[][] { {fieldName, searchText, address}};
    }
}

