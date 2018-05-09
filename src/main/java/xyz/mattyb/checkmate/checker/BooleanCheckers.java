package xyz.mattyb.checkmate.checker;

import xyz.mattyb.checkmate.BooleanCheck;
import xyz.mattyb.checkmate.checker.context.CheckerContext;

public class BooleanCheckers {

    private BooleanCheckers() {
        // empty on purpose
    }

    public static Checker<BooleanCheck> isBoolean = new Checker<BooleanCheck>() {
        @Override
        public boolean test(BooleanCheck expression, CheckerContext ctx) {
            if (expression.getExpected() == null || expression.getSupplier() == null
                    || expression.getSupplier().get() == null) {
                ctx.setNpe(true);
                return true;
            }
            return expression.getExpected() != expression.getSupplier().get();
        }

        @Override
        public String getExceptionMessage(BooleanCheck expression, CheckerContext ctx) {
            return String.format("Expected %s, but was %s", expression.getExpected(), expression.getSupplier().get());
        }
    };
}
