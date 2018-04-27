package xyz.mattyb.checkmate.checker;

import xyz.mattyb.checkmate.Range;

public class NumberCheckers {

    private NumberCheckers() {
        // empty on purpose
    }

    public static Checker<Range<Integer>> intOutOfRange = new Checker<Range<Integer>>() {
        @Override
        public boolean test(Range<Integer> range) {
            if (range == null) {
                return true;
            }

            final Comparable<Integer> val = range.getValue();
            if (val == null) {
                return true;
            }
            Integer end = range.isInclusive() ? 0 : -1;
            if (val.compareTo(range.getStart()) >= 0 &&
                    val.compareTo(range.getEnd()) <= end) {
                return false;
            }
            return true;
        }

        @Override
        public String getExceptionMessage(Range<Integer> range) {
            return buildMessage(range);
        }
    };

    public static Checker<Range<Long>> longOutOfRange = new Checker<Range<Long>>() {
        @Override
        public boolean test(Range<Long> range) {
            if (range == null) {
                return true;
            }
            final Comparable<Long> val = range.getValue();
            if (val == null) {
                return true;
            }
            Integer end = range.isInclusive() ? 0 : -1;
            if (val.compareTo(range.getStart()) >= 0 &&
                    val.compareTo(range.getEnd()) <= end) {
                return false;
            }
            return true;
        }

        @Override
        public String getExceptionMessage(Range<Long> range) {
            return buildMessage(range);
        }
    };

    private static String buildMessage(Range range) {
        if (range == null) {
            return "Not in range";
        }
        String inclusive = "exclusive";
        if (range.isInclusive()) {
            inclusive = "inclusive";
        }
        return String.format("%s is not between %s and %s, %s", range.getValue(), range.getStart(), range.getEnd(), inclusive);
    }
}
