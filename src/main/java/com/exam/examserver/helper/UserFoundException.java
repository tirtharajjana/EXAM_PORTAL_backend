package com.exam.examserver.helper;

public class UserFoundException extends Exception {
    public UserFoundException(String message) {
        super(message);
    }

    public UserFoundException() {
        super("User already exists in db");
    }

}
