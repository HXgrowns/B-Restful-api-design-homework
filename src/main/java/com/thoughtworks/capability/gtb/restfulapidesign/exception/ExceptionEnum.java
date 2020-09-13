package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public enum ExceptionEnum {
    UNKNOWN_ERROR(1,"unknown error"),
    NOT_FIND_STUDENT(2,"not find student"),
    PARAM_IS_INVALID(3,"param is invalid");

    private final int code;
    private final String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
