package org.mattyb.checkmate;

public interface IntRangeCheckMate {

    /**
     * The value to compare.
     *
     * @param value a {@link Comparable} of an {@link Integer}.
     * @return An instance of {@link IntRangeCheckMate}.
     */
    IntRangeCheckMate value(Comparable<Integer> value);

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

    /**
     * Used to make the end of the range inclusive.
     *
     * @return An instance of {@link CheckMate}.
     */
    CheckMate inclusive();
}
