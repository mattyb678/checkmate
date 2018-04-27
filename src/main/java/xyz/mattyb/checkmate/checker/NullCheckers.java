package xyz.mattyb.checkmate.checker;

import java.util.Objects;

public class NullCheckers {

    private NullCheckers() {
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

    public static Checker<Object[]> noNullElementsArray = new Checker<Object[]>() {
        @Override
        public boolean test(Object[] array) {
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
        public String getExceptionMessage(Object[] array) {
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
            return "The validated array contains null elements";
        }
    };
}
