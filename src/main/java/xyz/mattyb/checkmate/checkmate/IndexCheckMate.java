package xyz.mattyb.checkmate.checkmate;

import xyz.mattyb.checkmate.CheckMate;

import java.util.Collection;

public interface IndexCheckMate {

    <T> CheckMate validIn(T[] array);

    <T extends Collection<?>> CheckMate validIn(T collection);

    <T extends CharSequence> CheckMate validIn(T chars);
}
