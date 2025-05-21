package se.yrgo.exception;

public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException() {
    }

    public ReviewNotFoundException(String errorMessage, Throwable thrw) {
        super(errorMessage, thrw);
    }
}
