package xyz.mattyb.checkmate.checkmate;

import xyz.mattyb.checkmate.CheckMate;

import java.util.function.Supplier;

public interface BooleanCheckMate {

    BooleanCheckMate is(boolean expression);

    BooleanCheckMate is(Supplier<Boolean> supplier);

    CheckMate truthy();

    CheckMate falsy();
}
