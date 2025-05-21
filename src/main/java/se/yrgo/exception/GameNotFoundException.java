package se.yrgo.exception;

public class GameNotFoundException extends Exception {
    public GameNotFoundException() {
    }

    public GameNotFoundException(String errorMessage, Throwable thrw) {
        super(errorMessage, thrw);
    }
}
