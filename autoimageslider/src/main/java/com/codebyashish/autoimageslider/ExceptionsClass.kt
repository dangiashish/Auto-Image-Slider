package com.codebyashish.autoimageslider;

public class ExceptionsClass extends Exception{

    public ExceptionsClass(String message) {
        super(message);
    }

    public ExceptionsClass(String message, Throwable cause) {
        super(message, cause);
    }

    private int errorCode;

    public ExceptionsClass(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }



    public int getErrorCode() {
        return errorCode;
    }
}
