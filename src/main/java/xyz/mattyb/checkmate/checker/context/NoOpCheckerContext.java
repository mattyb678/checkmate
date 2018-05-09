package xyz.mattyb.checkmate.checker.context;

public class NoOpCheckerContext extends CheckerContext {
    @Override
    public boolean isNpe() {
        return false;
    }
}
