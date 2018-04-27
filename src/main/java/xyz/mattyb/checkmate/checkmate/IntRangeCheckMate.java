package xyz.mattyb.checkmate.checkmate;

import xyz.mattyb.checkmate.CheckMate;

public interface IntRangeCheckMate {

    /**
     * The intValue to compare.
     *
     * @param value a {@link Comparable} of an {@link Integer}.
     * @return An instance of {@link IntRangeCheckMate}.
     */
    IntRangeCheckMate intValue(Comparable<Integer> value);

    /**
     * The start of the range check, inclusive.
     *
     * @param start Integer that represents the start of the range, inclusive.
     * @return An instance of {@link IntRangeCheckMate}.
     */
    IntRangeCheckMate between(Integer start);

    /**
     * The end of the range check, exclusive.
     *
     * @param endExclusive Integer that represents the end of the range, exclusive.
     * @return An instance of {@link CheckMate}.
     */
    CheckMate and(Integer endExclusive);
}
