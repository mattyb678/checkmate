package org.mattyb.checkmate.checker;

import org.mattyb.checkmate.Range;

import java.util.Collection;
import java.util.Objects;

public class Checkers {

    private Checkers() {
        // empty on purpose
    }

    public static Checker<Object> notNull = new Checker<Object>() {
        @Override
        public boolean test(Object object) {
            return Objects.isNull(object);
        }

        @Override
        public String getExceptionMessage(Object object) {
            return "The validated object is null";
        }
    };

    public static Checker<Range<Integer>> intOutOfRange = new Checker<Range<Integer>>() {
        @Override
        public boolean test(Range<Integer> range) {
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
            return "Not in range";
        }
    };
}
