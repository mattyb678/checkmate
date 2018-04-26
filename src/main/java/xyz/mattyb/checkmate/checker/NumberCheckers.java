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
            if (range == null) {
                return "Not in range";
            }
            String inclusive = "exclusive";
            if (range.isInclusive()) {
                inclusive = "inclusive";
            }
            return String.format("%s is not between %s and %s, %s", range.getValue(), range.getStart(), range.getEnd(), inclusive);
        }
    };
}
