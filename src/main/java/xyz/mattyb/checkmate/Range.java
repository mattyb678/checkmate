package xyz.mattyb.checkmate;

public class Range<T extends Number> {

    private Comparable<T> value;
    private T start;
    private T end;
    private boolean inclusive = false;

    public Comparable<T> getValue() {
        return value;
    }

    public void setValue(Comparable<T> value) {
        this.value = value;
    }

    public T getStart() {
        return start;
    }

    public void setStart(T start) {
        this.start = start;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    public boolean isInclusive() {
        return inclusive;
    }

    public void setInclusive(boolean inclusive) {
        this.inclusive = inclusive;
    }
}
