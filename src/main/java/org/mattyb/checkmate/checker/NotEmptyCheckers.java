package org.mattyb.checkmate.checker;

import java.util.Collection;
import java.util.Map;

public class NotEmptyCheckers {

    private NotEmptyCheckers() {
        // empty on purpose
    }

    public static Checker<Collection<?>> collection = new Checker<Collection<?>>() {
        @Override
        public boolean test(Collection<?> coll) {
            return coll == null ||  coll.isEmpty();
        }

        @Override
        public String getExceptionMessage(Collection<?> coll) {
            return "The validated collection is empty";
        }
    };

    public static Checker<CharSequence> charSequence = new Checker<CharSequence>() {
        @Override
        public boolean test(CharSequence chars) {
            if (chars == null || chars.length() == 0) {
                return true;
            }
            for (int i = 0; i < chars.length(); i++) {
                if (!Character.isWhitespace(chars.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String getExceptionMessage(CharSequence object) {
            return "The validated character sequence is empty";
        }
    };

    public static Checker<Object[]> array = new Checker<Object[]>() {
        @Override
        public boolean test(Object[] array) {
            if (array == null) {
                return true;
            }
            if (array.length == 0) {
                return true;
            }
            return false;
        }

        @Override
        public String getExceptionMessage(Object[] object) {
            return "The validated array is empty";
        }
    };

    public static Checker<Map<?, ?>> map = new Checker<Map<?, ?>>() {
        @Override
        public boolean test(Map<?, ?> map) {
            if (map == null) {
                return true;
            }
            if (map.isEmpty()) {
                return true;
            }
            return false;
        }

        @Override
        public String getExceptionMessage(Map<?, ?> map) {
            return "The validated map is empty";
        }
    };
}
