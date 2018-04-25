package org.mattyb.check;

public interface IntRangeCheck {

    IntRangeCheck value(Comparable<Integer> value);

    IntRangeCheck between(Integer start);

    Check and(Integer endExclusive);

    Check andInclusive(Integer endInclusive);
}
