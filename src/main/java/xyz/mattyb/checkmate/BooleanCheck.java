package xyz.mattyb.checkmate;

import java.util.function.Supplier;

public class BooleanCheck {

    private Supplier<Boolean> supplier;
    private Boolean expected;

    public Supplier<Boolean> getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier<Boolean> supplier) {
        this.supplier = supplier;
    }

    public Boolean getExpected() {
        return expected;
    }

    public void setExpected(Boolean expected) {
        this.expected = expected;
    }
}
