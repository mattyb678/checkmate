package org.mattyb.checkmate;

class CheckInfo<T> {

    private final Checker checker;
    private final T toCheck;
    private Class<? extends RuntimeException> throwableClass;
    private String message;

    CheckInfo(final T toCheck, final Checker checker) {
        this.checker = checker;
        this.toCheck = toCheck;
        this.throwableClass = IllegalArgumentException.class;
    }

    void setThrowableClass(Class<? extends RuntimeException> throwableClass) {
        this.throwableClass = throwableClass;
    }

    void setMessage(final String message) {
        this.message = message;
    }

    Checker getChecker() {
        return checker;
    }

    T getToCheck() {
        return toCheck;
    }

    Class<? extends RuntimeException> getThrowableClass() {
        return throwableClass;
    }

    String getMessage() {
        return message;
    }
}


