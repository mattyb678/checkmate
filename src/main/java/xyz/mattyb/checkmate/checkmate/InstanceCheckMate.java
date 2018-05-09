package xyz.mattyb.checkmate.checkmate;

import xyz.mattyb.checkmate.CheckMate;

public interface InstanceCheckMate {

    <T> InstanceCheckMate object(T obj);

    CheckMate isInstanceOf(Class<?> clazz);

}
