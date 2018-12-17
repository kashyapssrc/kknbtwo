package com.objectfrontier.training.jdbc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.objectfrontier.training.jdbc.pojo.Address;
import com.objectfrontier.training.jdbc.pojo.Person;

public class CsvParser {

    private static final String COMMA_DELIMITER = ",";

    public static Object[][] createPositiveParser() throws IOException, URISyntaxException {

        Path fileName = Paths.get(ClassLoader.getSystemResource("com\\objectfrontier\\training\\csv\\person.csv").toURI());
        Stream<String> st = Files.lines(fileName);
        List<String[]> listStream = st.map(action -> action.split(COMMA_DELIMITER))
                                                 .collect(Collectors.toList());
        List<Object> personList = new ArrayList<>();
        List<Object> expectedPersonList = new ArrayList<>();
        st.close();

        for (String[] strings : listStream) {
            Person actualPerson = constructPerson(strings);
            Person expectedPerson = actualPerson;
            personList.add(actualPerson);
            expectedPersonList.add(expectedPerson);
        }

        Object[][] twoDArray = covertListToArray(personList, expectedPersonList);
        return twoDArray;
    }

    private static Object[][] covertListToArray(List<Object> personList, List<Object> expectedPersonList) {
        Object[][] twoDArray = new Object[personList.size()][2];

        for (int i = 0; i < personList.size(); i++) {
            twoDArray[i][0] = personList.get(i);
            twoDArray[i][1] = expectedPersonList.get(i);
        }
        return twoDArray;
    }

    private static Person constructPerson(String[] strings) {
        Person actualPerson = new Person(strings[0], 
                                         strings[1], 
                                         strings[2], 
                                         strings[3], 
                             new Address(strings[4], 
                                         strings[5], 
                                         Integer.parseInt(strings[6])));
        return actualPerson;
    }

    public static Object[][] createNegativeParser() throws URISyntaxException, IOException {

        Path fileName = Paths.get(ClassLoader.getSystemResource("com\\objectfrontier\\training\\csv\\personNegativeCase.csv")
                                             .toURI());
        Stream<String> st = Files.lines(fileName);
        List<String[]> listStream = st.map(a -> a.split(COMMA_DELIMITER))
                                                 .collect(Collectors.toList());
        List<Object> personList = new ArrayList<>();
        List<Object> expectedPersonList = new ArrayList<>();
        
        for (String[] strings : listStream) {
            Person actualPerson = constructPerson(strings);
            personList.add(actualPerson);
            expectedPersonList.add(strings[7]);
        }
        Object[][] twoDArray = covertListToArray(personList, expectedPersonList);
        return twoDArray;
    }

    public static Object[][] readAllParser() throws URISyntaxException, IOException {

        Path fileName = Paths.get(ClassLoader.getSystemResource("com\\objectfrontier\\training\\csv\\person.csv")
                                             .toURI());
        Stream<String> st = Files.lines(fileName);
        List<String[]> listStream = st.map(a -> a.split(COMMA_DELIMITER))
                                                 .collect(Collectors.toList());
        List<Object> personList = new ArrayList<>();
        List<Object> expectedPersonList = new ArrayList<>();

        for (String[] strings : listStream) {
            Person actualPerson = constructPerson(strings);
            personList.add(actualPerson);
            expectedPersonList.add(strings[7]);
        }
        Object[][] twoDArray = covertListToArray(personList, expectedPersonList);
        return twoDArray;
    }
    public static int deleteParser() throws URISyntaxException, IOException {
        
        Path fileName = Paths.get(ClassLoader.getSystemResource("com\\objectfrontier\\training\\csv\\person.csv")
                                             .toURI());
        Stream<String> st = Files.lines(fileName);
        List<String> countRow = st.collect(Collectors.toList());
        int totalCount = countRow.size();
        st.close();
        return totalCount;
    }

}

