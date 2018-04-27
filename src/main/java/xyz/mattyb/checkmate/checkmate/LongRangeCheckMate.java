package xyz.mattyb.checkmate.checkmate;

import xyz.mattyb.checkmate.CheckMate;

public interface LongRangeCheckMate {

    LongRangeCheckMate longValue(Comparable<Long> value);

    LongRangeCheckMate between(Long start);

    CheckMate and(Long endExclusive);
}
