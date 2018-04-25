package org.mattyb.check;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        final String test = "";
        Check.check()
            .value(4).between(0).and(4).inclusive()
            .notNull(test).withMessage("I've changed")
            .notEmpty(Collections.singletonList("yoyo"))
            .verify();
    }
}
