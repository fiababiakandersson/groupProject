package se.yrgo.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String errorMessage, Throwable thrw) {
        super(errorMessage, thrw);
    }
}
