package xyz.mattyb.checkmate.checkmate;

import xyz.mattyb.checkmate.CheckMate;

public interface DoubleRangeCheckMate {

    DoubleRangeCheckMate doubleValue(Comparable<Double> value);

    DoubleRangeCheckMate between(Double start);

    CheckMate and(Double endExclusive);
}
