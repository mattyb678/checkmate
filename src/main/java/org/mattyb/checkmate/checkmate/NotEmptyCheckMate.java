package org.mattyb.checkmate.checkmate;

import org.mattyb.checkmate.CheckMate;

import java.util.Collection;

public interface NotEmptyCheckMate {

    <T extends Collection<?>> CheckMate notEmpty(final T collection);

    <T extends CharSequence> CheckMate notEmpty(final T chars);

    <T> CheckMate notEmpty(final T[] array);
}
