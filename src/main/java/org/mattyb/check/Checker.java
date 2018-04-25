package org.mattyb.check;

public interface Checker<T> {

    boolean test(T object);

    String getExceptionMessage(T object);

}
