package com.objectfrontier.training.jdbc.dbconnection;

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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;

public class Demo {

    private static final String COMMA_DELIMITER = ",";
    public static void main(String[] args) throws ParseException, IOException, URISyntaxException {

        
        Demo cm = new Demo();
        Class<? extends Demo> c = cm.getClass();
//        InputStream inputPerson = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\insertperson.csv");
//        BufferedReader person = new BufferedReader(new InputStreamReader(inputPerson));
//        InputStream inputAddress = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\insertperso.csv");
        // File file = getFile();
//        String path = "D:\\dev\\training\\karthik.n\\service\\src\\com\\objectfrontier\\training\\jdbc\\csv\\insertperson.csv";
//        FileReader inputStream = new FileReader(path);
//        BufferedReader address = new BufferedReader(inputStream);
//        String path1 = "D:\\dev\\training\\karthik.n\\service\\src\\com\\objectfrontier\\training\\jdbc\\csv\\insertperso.csv";
//        FileReader inputStream1 = new FileReader(path1);
//        BufferedReader address1 = new BufferedReader(inputStream1);

//        InputStream inputPerson = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\insertperson.csv");
//        BufferedReader person = new BufferedReader(new InputStreamReader(inputPerson));
//        InputStream inputAddress = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\insertperso.csv");
//        BufferedReader address = new BufferedReader(new InputStreamReader(inputAddress));
        Path pathName = Paths.get(ClassLoader.getSystemResource("com\\objectfrontier\\training\\csv\\person.csv").toURI());
        Stream<String> personData = Files.lines(pathName);
        List<String[]> person1 = personData.map(a -> a.split(COMMA_DELIMITER)).collect(Collectors.toList());
        
        List<List<Object>> listOfLists = new ArrayList<List<Object>>();
        List<Object> personList = new ArrayList<>();
        List<Object> expectedPersonList = new ArrayList<>();
        Person actualPerson = null;
        Person expectedPerson = null;

        for (String[] strings : person1) {
            actualPerson = new Person(strings[0], 
                                      strings[1], 
                                      strings[2], 
                                      strings[3], 
                         new Address (strings[4], 
                                      strings[5], 
                                      Integer.parseInt(strings[6])));
            System.out.println(actualPerson);
//          System.out.println(personDetails[0]);
//          System.out.println(personDetails[1]);
//          System.out.println(personDetails[2]);
//          System.out.println(personDetails[3]);
//          System.out.println(addressDetails[0]);
//          System.out.println(addressDetails[1]);
//          System.out.println(addressDetails[2]);
            expectedPerson = actualPerson;
            personList.add(actualPerson);
            expectedPersonList.add(expectedPerson);
        }
        listOfLists.add(personList);
        listOfLists.add(expectedPersonList);
//        String addressLine;
//        String personLine;
//        Address actualAddress = null;
//        Person actualPerson = null;
//        Address actualaddress = null;
//        address.readLine();
//        person.readLine();
////        person.readLine();
//        while (((addressLine = address.readLine()) != null) && (personLine = person.readLine()) != null)  {
//            String[] personDetails = personLine.split(COMMA_DELIMITER);
//            String[] addressDetails = addressLine.split(COMMA_DELIMITER);
//            if ((personDetails.length != 0) && (addressDetails.length != 0)) {
//                actualAddress = new Address(addressDetails[0], addressDetails[1], Integer.parseInt(addressDetails[2]));
//                actualPerson = new Person(personDetails[0], personDetails[1], personDetails[2], personDetails[3], actualAddress);
//                System.out.println(actualPerson);
//                System.out.println(personDetails[0]);
//                System.out.println(personDetails[1]);
//                System.out.println(personDetails[2]);
//                System.out.println(personDetails[3]);
//                System.out.println(addressDetails[0]);
//                System.out.println(addressDetails[1]);
//                System.out.println(addressDetails[2]);
//            }
//        }
//            address.close();
//            person.close();
    }
}
        
//        Calendar cal = Calendar.getInstance();
//        String s = "06-06-1996";
//        String birthDate = "12/30/1996";
////        String name = "kar";
////        String fName = new String("kar");
////        String lName = new String("kar");
////        System.out.println(name == fName);
////        System.out.println(fName == lName);
////        System.out.println(name.equals(lName));
////        System.out.println(lName.equals(fName));
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//////        Date d = (Date) sdf.parse(birthDate);
//        System.out.println(sdf.parse(birthDate));
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate dob = LocalDate.parse("19-12-1998", formatter);
//        System.out.println(Date.valueOf(dob));
//        System.out.println(LocalDate.parse( "2011-01-01" )
//        .format( DateTimeFormatter.ofPattern( "dd-mm-yyyy" ))); 
//        Date date = Date.valueOf(birthDate);
//        System.out.println(DateTimeFormatter.ofPattern("dd-mm-yyyy").format( d));
//        Date df = DateTimeFormatter.ofPattern("dd-mm-yyy");
//         df = java.sql.Date.valueOf(LocalDate.parse(s, df))
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate d = LocalDate.parse(s, df);
//        System.out.println(Date.valueOf(d));
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        sdf.for
//        java.util.Date a = sdf.parse(s);
//        long birthDateInMilliSeconds = a.toInstant().toEpochMilli();
//        System.out.println(new Date(birthDateInMilliSeconds));
//        String bbb = sdf.format(a);
//        System.out.println(bbb);
//        String strDate = sdf.format(cal.getTime());
//        System.out.println("Current date in String Format: "+strDate);
//
//        SimpleDateFormat sdf1 = new SimpleDateFormat();
//        sdf1.applyPattern("dd/MM/yyyy");
//        Date date = sdf1.parse(strDate);
//        System.out.println("Current date in Date Format: "+date);


//private static boolean personNameValidation(Person personToCreate) {
//
//    if (personToCreate.getFirstName() == null) { throw new AppException("first name empty"); }
//    if (personToCreate.getLastName() == null) { throw new AppException("last name empty"); }
//    if (personToCreate.getBirthDate() == null) { throw new AppException("birth date empty"); }
//
//    return true;
//}
//
//private boolean checkPersonNameDuplication(Connection connection, Person personToCheck) throws SQLException {
//
//    StringBuilder nameValidationQuery = new StringBuilder()
//                            .append("SELECT first_name, last_name")
//                            .append("  FROM person1")
//                            .append(" WHERE first_name = ? AND last_name = ?");
//
//    PreparedStatement validationStatement = connection.prepareStatement(nameValidationQuery.toString());
//    validationStatement.setString(1, personToCheck.getFirstName());
//    validationStatement.setString(2, personToCheck.getLastName());
//
//    ResultSet validationResult = validationStatement.executeQuery();
//
//    if (validationResult.next()) {
//        throw new AppException("change the person name");
//    }
//
//    return true;
//}