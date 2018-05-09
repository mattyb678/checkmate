package xyz.mattyb.checkmate.checker.context;

public abstract class CheckerContext {

    protected boolean npe = false;

    public void setNpe(boolean npe) {
        this.npe = npe;
    }

    public boolean isNpe() {
        return npe;
    }
}
