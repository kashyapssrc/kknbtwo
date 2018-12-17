//package com.objectfrontier.training.jdbc;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.objectfrontier.training.jdbc.pojo.Address;
//
//public class Demo {
//    public Address search(Connection connection, Address address, String columnName) throws SQLException {
//
//        return searchBasedOnAddressType(connection, address, columnName);
//
//    }
//
//    private Address searchBasedOnAddressType(Connection connection, Address address, String columnName) throws SQLException {
//
//        switch (columnName) {
//
//        case "street" :
//            String searchStreet = statement()
//            .append(" WHERE street LIKE ? ").toString();
//
//            PreparedStatement forStreet = connection.prepareStatement(searchStreet);
//            forStreet.setString(1, address.getStreet() + "%");
//            ResultSet street = forStreet.executeQuery();
//
//            Address streetAddress = constructResulSet(street);
//            return streetAddress;
//
//        case "city" :
//            String searchCity = statement()
//            .append(" WHERE city LIKE ? ").toString();
//
//            PreparedStatement forCity = connection.prepareStatement(searchCity);
//            forCity.setString(1, address.getCity() + "%");
//            ResultSet city = forCity.executeQuery();
//
//            Address cityAddress = constructResulSet(city);
//            return cityAddress;
//
//        case "postalCode" :
//            String searchPostalCode = statement()
//            .append(" WHERE postal_code LIKE ? ").toString();
//
//            PreparedStatement forPostalCode = connection.prepareStatement(searchPostalCode);
//            forPostalCode.setString(1, address.getPostalCode() + "%");
//            ResultSet postalCode = forPostalCode.executeQuery();
//
//            Address postalCodeAddress = constructResulSet(postalCode);
//            return postalCodeAddress;
//
//        case "streetcity" :
//            String searchStreetCity = statement()
//            .append(" WHERE street LIKE ? AND city LIKE ?").toString();
//            
//
//            PreparedStatement forStreetCity = connection.prepareStatement(searchStreetCity);
//            forStreetCity.setString(1, address.getStreet() + "%");
//            forStreetCity.setString(2, address.getCity() + "%");
//            ResultSet streetCity = forStreetCity.executeQuery();
//
//            Address streetCityAddress = constructResulSet(streetCity);
//            return streetCityAddress;
//
//        case "streetpostalcode" :
//            String searchStreetPostalCode = statement()
//            .append(" WHERE street LIKE ? AND postal_code LIKE ? ").toString();
//
//            PreparedStatement forStreetPostalCode = connection.prepareStatement(searchStreetPostalCode);
//            forStreetPostalCode.setString(1, address.getStreet() + "%");
//            forStreetPostalCode.setString(2, address.getPostalCode() + "%");
//            ResultSet streetPostalCode = forStreetPostalCode.executeQuery();
//
//            Address streetPostalCodeAddress = constructResulSet(streetPostalCode);
//            return streetPostalCodeAddress;
//
//        case "citypostalcode" :
//            String searchCityPostalCode = statement()
//            .append(" WHERE city LIKE ? AND postal_code LIKE ? ").toString();
//
//            PreparedStatement forCityPostalCode = connection.prepareStatement(searchCityPostalCode);
//            forCityPostalCode.setString(1, address.getCity() + "%");
//            forCityPostalCode.setString(2, address.getPostalCode() + "%");
//            ResultSet cityPostalCode = forCityPostalCode.executeQuery();
//
//            Address cityPostalCodeAddress = constructResulSet(cityPostalCode);
//            return cityPostalCodeAddress;
//
//        default :
//            String searchStatement =  new StringBuilder()
//            .append("SELECT id, street, city, postal_code")
//            .append("  FROM address_service")
//            .append(" WHERE street LIKE ? OR city LIKE ? OR postal_code LIKE ? ").toString();
//
//
//            PreparedStatement preparedStatement = connection.prepareStatement(searchStatement);
//            preparedStatement.setString(1, address.getStreet());
//            preparedStatement.setString(2, address.getCity() + "%");
//            preparedStatement.setString(3, address.getPostalCode() + "%");
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            Address searchaddress = constructResulSet(resultSet);
//            return searchaddress;
//
//        }
//    }
//
//    private Address constructResulSet(ResultSet resultSet) throws SQLException {
//        Address searchAddress = null;
//        while (resultSet.next()) {
//            searchAddress= constructAddress(resultSet);
//        }
//        return searchAddress;
//    }
//
//    private StringBuilder statement() {
//        StringBuilder searchStatement =  new StringBuilder()
//                .append("SELECT id, street, city, postal_code")
//                .append("  FROM address_service");
//        return searchStatement;
//    }
//
//}
