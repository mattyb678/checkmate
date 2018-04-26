package xyz.mattyb.checkmate.checkmate;

import xyz.mattyb.checkmate.CheckMate;

import java.util.Collection;
import java.util.Map;

public interface NotEmptyCheckMate {

    <T extends Collection<?>> CheckMate notEmpty(final T collection);

    <T extends CharSequence> CheckMate notEmpty(final T chars);

    <T> CheckMate notEmpty(final T[] array);

    <T extends Map<?, ?>> CheckMate notEmpty(final T map);
}
