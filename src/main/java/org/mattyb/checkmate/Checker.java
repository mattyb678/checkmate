package org.mattyb.checkmate;

public interface Checker<T> {

    boolean test(T object);

    String getExceptionMessage(T object);

}
