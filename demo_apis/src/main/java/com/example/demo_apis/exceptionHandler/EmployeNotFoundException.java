package com.example.demo_apis.exceptionHandler;

public class EmployeNotFoundException extends RuntimeException {
    public EmployeNotFoundException(String s) {
        super(s);
    }
}
