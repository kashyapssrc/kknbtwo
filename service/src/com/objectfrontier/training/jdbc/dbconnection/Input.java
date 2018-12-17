package com.objectfrontier.training.jdbc.dbconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;

public class Input {
    private static final String COMMA_DELIMITER = ",";
    public static Object[][] data() throws IOException {

        Object[][] value = null;
        Input i = new Input();
        Class c = i.getClass();
        InputStream inputPerson = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\insertperson.csv");
        BufferedReader person = new BufferedReader(new InputStreamReader(inputPerson));
        InputStream inputAddress = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\insertperso.csv");
        BufferedReader address = new BufferedReader(new InputStreamReader(inputAddress));
        Person actualPerson = null;
        Address actualAddress = null;
//        String path = "D:\\dev\\training\\karthik.n\\service\\src\\com\\objectfrontier\\training\\jdbc\\csv\\insertperso.csv";
//        FileReader inputStream = new FileReader(path);
//        BufferedReader address = new BufferedReader(inputStream);
//        String pathPerson = "D:\\dev\\training\\karthik.n\\service\\src\\com\\objectfrontier\\training\\jdbc\\csv\\insertperson.csv";
//        FileReader inputStreamPerson = new FileReader(pathPerson);
//        BufferedReader person = new BufferedReader(inputStreamPerson);
        String line;
        String lines;
        Person expectedPerson = null;
        List<Person> personList = new ArrayList<Person>();
        List<Person> personExpectedList = new ArrayList<Person>();
        address.readLine();
        person.readLine();
            while (((line = address.readLine()) != null) && ((lines = person.readLine()) != null)) {
                String[] addressDetails = line.split(COMMA_DELIMITER);
                String[] personDetails = lines.split(COMMA_DELIMITER);
            if ((addressDetails.length != 0) && (personDetails.length != 0)) {
                actualAddress = new Address(addressDetails[0], addressDetails[1], Integer.parseInt(addressDetails[2]));
                actualPerson = new Person(personDetails[0], personDetails[1], personDetails[2], personDetails[3], actualAddress);
                expectedPerson = actualPerson;
                value =  new Object[][] { {actualPerson, expectedPerson}};
//                personList.add(actualPerson);
//                personExpectedList.add(expectedPerson);
            }
                
            }
return value;
    }
    private static void log(String format, String vals) {
        System.out.format(format, vals);
    }
}
