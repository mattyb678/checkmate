package org.mattyb.check;

import java.util.Collection;
import java.util.Objects;

public class Checkers {

    private Checkers() {
        // empty on purpose
    }

    static Checker<Object> notNull = new Checker<Object>() {
        @Override
        public boolean test(Object object) {
            return Objects.isNull(object);
        }

        @Override
        public String getExceptionMessage() {
            return "The validated object is null";
        }
    };

    static Checker<Collection<?>> notEmptyCollection = new Checker<Collection<?>>() {
        @Override
        public boolean test(Collection<?> coll) {
            return coll != null && coll.isEmpty();
        }

        @Override
        public String getExceptionMessage() {
            return "The validated collection is empty";
        }
    };
}
