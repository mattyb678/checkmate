package org.mattyb.checkmate.checker;

public interface Checker<T> {

    boolean test(T object);

    String getExceptionMessage(T object);

}
