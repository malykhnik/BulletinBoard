package com.malykhnik.bulletinboard.exception;

public class UserNotAuthenticated extends Exception{
    public UserNotAuthenticated(String message) {
        super(message);
    }
}
