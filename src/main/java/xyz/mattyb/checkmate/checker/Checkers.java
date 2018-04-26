package xyz.mattyb.checkmate.checker;

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
}
