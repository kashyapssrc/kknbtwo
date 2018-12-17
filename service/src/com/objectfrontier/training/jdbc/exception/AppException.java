package com.objectfrontier.training.jdbc.exception;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    String errorMessage;
    ErrorCode errorCode;
    
//    public AppException(errorCode) {
//        this.errorMessage = errorCode;
//    }
;
    public AppException(ErrorCode error) {

        this.errorMessage = error.getErrorMessage();
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
