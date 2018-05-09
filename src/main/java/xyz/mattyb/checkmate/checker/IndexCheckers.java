package xyz.mattyb.checkmate.checker;

import xyz.mattyb.checkmate.Index;
import xyz.mattyb.checkmate.checker.context.CheckerContext;

public class IndexCheckers {

    private IndexCheckers() {
        // empty on purpose
    }

    public static Checker<Index> indexChecker = new Checker<Index>() {
        @Override
        public boolean test(Index index, CheckerContext ctx) {
            if (index == null || index.isNull()) {
                ctx.setNpe(true);
                return true;
            }

            return index.getIndex() < 0 || index.getIndex() >= index.getSize();
        }

        @Override
        public String getExceptionMessage(Index index, CheckerContext ctx) {
            if (ctx.isNpe()) {
                // Should never happen
                return "The index is null, that shouldn't happen";
            }
            return String.format("The validated %s index is invalid: %d", index.getType(), index.getIndex());
        }
    };
}
