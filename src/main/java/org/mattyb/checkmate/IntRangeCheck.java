package org.mattyb.checkmate;

public interface IntRangeCheck {

    IntRangeCheck value(Comparable<Integer> value);

    IntRangeCheck between(Integer start);

    CheckMate and(Integer endExclusive);

    CheckMate inclusive();
}
