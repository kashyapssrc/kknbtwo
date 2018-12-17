package com.objectfrontier.training.jdbc;

public interface Statements {

    String PERSON_CREATE_STATEMENT = new StringBuffer()
            .append("INSERT INTO service_person (first_name ")
                                       .append(",last_name, ")
                                       .append(",email ")
                                       .append(",birth_date ")
                                       .append(",address_id ")
                                       .append(",create_date)")
            .append("VALUES(?,?,?,?,?,?) " ).toString();

    String PERSON_READ_STATEMENT = new StringBuffer()
            .append("SELECT id ") 
                  .append(",first_name ")
                  .append(",last_name")
                  .append(",email ")
                  .append(",address_id ") 
                  .append(",birth_date ")
                  .append(",create_date ")
            .append("  FROM service_person ")
            .append(" WHERE id = ?").toString();

    String PERSON_READALL_STATEMENT = new StringBuffer()
            .append("SELECT id ") 
            .append(",first_name ")
            .append(",last_name")
            .append(",email ")
            .append(",address_id ") 
            .append(",birth_date ")
            .append(",create_date ")
            .append("  FROM service_person ").toString();

    String PERSON_UPDATE_STATEMENT = new StringBuffer()
            .append("UPDATE service_person ")
            .append("   SET first_name = ? ")
                  .append(",last_name = ? ")
                  .append(",email = ? ")
                  .append(",birth_date = ?")
            .append(" WHERE id = ?").toString();

    String PERSON_DELETE_STATEMENT = new StringBuffer()
            .append("DELETE")
            .append("  FROM service_person ")
            .append(" WHERE id = ? ").toString();

    String PERSON_NAME_VALIDATION_STATEMENT = new StringBuffer()
            .append("SELECT first_name ")
                  .append(",last_name ")
            .append("  FROM service_person")
            .append(" WHERE first_name = ? AND last_name = ? ").toString();

    String PERSON_EMAIL_DUPLICATE_STATEMENT = new StringBuffer()
            .append("SELECT first_name ")
                  .append(",last_name ")
            .append("  FROM service_person")
            .append(" WHERE email = ?").toString();

    String ADDRESS_CREATE_STATEMENT = new StringBuffer()
            .append("INSERT INTO service_address (street ")
                                        .append(",city ")
                                        .append(",postal_code)")
            .append("VALUES(?,?,?)").toString();

    String ADDRESS_READ_STATEMENT = new StringBuffer()
            .append("SELECT id ")
                  .append(",street ")
                  .append(",city ")
                  .append(",postal_code")
            .append("  FROM service_address")
            .append(" WHERE id = ?").toString();

    String ADDRESS_READALL_STATEMENT = new StringBuffer()
            .append("SELECT id ")
                  .append(",street ")
                  .append(",city ")
                  .append(",postal_code")
            .append("  FROM service_address").toString();

    String ADDRESS_UPDATE_STATEMENT = new StringBuffer()
            .append("UPDATE service_address ")
            .append("   SET street = ? ")
                  .append(",city = ? ")
                  .append(",postal_code = ?")
            .append(" WHERE id = ?").toString();

    String ADDRESS_DELETE_STATEMENT = new StringBuffer()
            .append("DELETE")
            .append("  FROM service_address ")
            .append(" WHERE id = ? ").toString();

    String ADDRESS_SEARCH_STATEMENT = new StringBuffer()
            .append("SELECT id ")
                  .append(",street ")
                  .append(",city ")
                  .append(",postal_code")
            .append("  FROM service_address")
            .append(" WHERE ").toString();

    String ADDRESS_SEARCH_STREET_STATEMENT = new StringBuffer()
            .append(" street LIKE ? ").toString();

    String ADDRESS_SEARCH_CITY_STATEMENT = new StringBuffer()
            .append(" city LIKE ? ").toString();

    String ADDRESS_SEARCH_POSTAL_CODE_STATEMENT = new StringBuffer()
            .append(" postal_code LIKE ? ").toString();

    String ADDRESS_SEARCH_DEFAULT_STATEMENT = new StringBuffer()
            .append(" street LIKE ? OR city LIKE ? OR postal_code LIKE ? ").toString();

    String OR = new StringBuffer()
            .append("OR").toString();

    String RESET_TABLE = new StringBuffer()
            .append("DELETE FROM service_person")
            .append(";\n")
            .append("ALTER TABLE service_person AUTO_INCREMENT = 1")
            .append(";\n")
            .append("DELETE FROM service_address")
            .append(";\n")
            .append("ALTER TABLE service_address AUTO_INCREMENT = 1")
            .append(";\n").toString();
}
