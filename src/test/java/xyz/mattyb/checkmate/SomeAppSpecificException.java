package xyz.mattyb.checkmate;

public class SomeAppSpecificException extends RuntimeException {

    public SomeAppSpecificException(String message) {
        super(message);
    }
}
