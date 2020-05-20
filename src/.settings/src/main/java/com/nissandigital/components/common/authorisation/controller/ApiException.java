package com.nissandigital.components.common.authorisation.controller;

@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
