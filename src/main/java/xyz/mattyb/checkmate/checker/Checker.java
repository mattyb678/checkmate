package xyz.mattyb.checkmate.checker;

import xyz.mattyb.checkmate.checker.context.CheckerContext;

public interface Checker<T> {

    boolean test(T object, CheckerContext ctx);

    String getExceptionMessage(T object, CheckerContext ctx);

}
