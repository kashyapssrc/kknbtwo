package com.objectfrontier.training.jdbc.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;

public class Demo {
    private static final String COMMA_DELIMITER = ",";
    public static void main(String[] args) throws ParseException, IOException {

        
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

        InputStream inputPerson = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\book3.csv");
        BufferedReader person = new BufferedReader(new InputStreamReader(inputPerson));
        InputStream inputAddress = c.getClassLoader().getResourceAsStream("com\\objectfrontier\\training\\jdbc\\csv\\insertperso.csv");
        BufferedReader address = new BufferedReader(new InputStreamReader(inputAddress));
        String addressLine;
        String personLine;
        Address actualAddress = null;
        Person actualPerson = null;
        Address actualaddress = null;
        address.readLine();
        person.readLine();
//        person.readLine();
        while (((addressLine = address.readLine()) != null) && (personLine = person.readLine()) != null)  {
            String[] personDetails = personLine.split(COMMA_DELIMITER);
            String[] addressDetails = addressLine.split(COMMA_DELIMITER);
            if ((personDetails.length != 0) && (addressDetails.length != 0)) {
                actualAddress = new Address(addressDetails[0], addressDetails[1], Integer.parseInt(addressDetails[2]));
                actualPerson = new Person(personDetails[0], personDetails[1], personDetails[2], personDetails[3], actualAddress);
                System.out.println(actualPerson);
                System.out.println(personDetails[0]);
                System.out.println(personDetails[1]);
                System.out.println(personDetails[2]);
                System.out.println(personDetails[3]);
                System.out.println(addressDetails[0]);
                System.out.println(addressDetails[1]);
                System.out.println(addressDetails[2]);
            }
        }
            address.close();
            person.close();
    }
}

