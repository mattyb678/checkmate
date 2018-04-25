package org.mattyb.checkmate.checker;

import java.util.Collection;
import java.util.stream.Stream;

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
            return null;
        }
    };
}
