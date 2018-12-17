package com.objectfrontier.training.jdbc.exception;

public enum ErrorCode {

    DUPLICATE_PERSON_NAME   ("name already exists"),
    INVALID_ID              ("id not found"),
    EMPTY_DATA              ("no data is provided"),
    COLUMN_MISSING          ("miss match in column"),
    DUPLICATE_MAIL_ENTRY         ("Duplicate email id"),
    BIRTH_DATE_FORMAT       ("Unparseable date: \"31/10/1996\""),
    FIRST_NAME_NULL         ("first name is empty"),
    LAST_NAME_NULL          ("last name is empty"),
    BIRTH_DATE_NULL         ("birth date is empty"),
    STREET_NULL             ("street is empty"),
    CITY_NULL               ("city is empty"),
    POSTAL_CODE             ("pin code is empty"),
    EMAIL_NULL              ("email is null"),
    POSTAL_CODE_FORMAT      ("pin code is too small");
    

    String errorMessage;
    private ErrorCode(String error) {
        this.errorMessage = error;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
