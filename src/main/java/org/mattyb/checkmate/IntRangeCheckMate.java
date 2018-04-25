package org.mattyb.checkmate;

public interface IntRangeCheckMate {

    IntRangeCheckMate value(Comparable<Integer> value);

    IntRangeCheckMate between(Integer start);

    CheckMate and(Integer endExclusive);

    CheckMate inclusive();
}
