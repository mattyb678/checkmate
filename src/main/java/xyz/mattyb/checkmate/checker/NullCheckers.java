package xyz.mattyb.checkmate.checker;

import xyz.mattyb.checkmate.checker.context.CheckerContext;

import java.util.Iterator;
import java.util.Objects;

public class NullCheckers {

    private NullCheckers() {
        // empty on purpose
    }

    public static Checker<Object> notNull = new Checker<Object>() {
        @Override
        public boolean test(Object object, CheckerContext ctx) {
            return Objects.isNull(object);
        }

        @Override
        public String getExceptionMessage(Object object, CheckerContext ctx) {
            return "The validated object is null";
        }
    };

    public static Checker<Object[]> noNullElementsArray = new Checker<Object[]>() {
        @Override
        public boolean test(Object[] array, CheckerContext ctx) {
            if (array == null || array.length == 0) {
                return true;
            }
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getExceptionMessage(Object[] array, CheckerContext ctx) {
            if (array == null) {
                return "Array is null";
            }
            if (array.length == 0) {
                return "Array is empty";
            }
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    return String.format("The validated array contains null element at index: %d", i);
                }
            }
            // Should never get here
            return "The validated array contains no null elements, throwing an exception is a mistake";
        }
    };

    public static Checker<Iterable<?>> noNullElementsIterable = new Checker<Iterable<?>>() {
        @Override
        public boolean test(Iterable<?> iterable, CheckerContext ctx) {
            if (iterable == null) {
                return true;
            }
            for (final Iterator<?> it = iterable.iterator(); it.hasNext(); ) {
                if (it.next() == null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getExceptionMessage(Iterable<?> iterable, CheckerContext ctx) {
            if (iterable == null) {
                return "The validated collection is null";
            }
            int i = 0;
            for (final Iterator<?> it = iterable.iterator(); it.hasNext(); i++) {
                if (it.next() == null) {
                    return String.format("The validated collection contains a null element at index: %d", i);
                }
            }
            // Should never get here
            return "The validated collection has no null elements, throwing an exception is a mistake";
        }
    };
}
