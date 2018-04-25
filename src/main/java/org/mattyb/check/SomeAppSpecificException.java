package org.mattyb.check;

public class SomeAppSpecificException extends RuntimeException {

    public SomeAppSpecificException(String message) {
        super(message);
    }
}
