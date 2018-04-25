package org.mattyb.check;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        final String test = "";
        Check.check()
            .value(3).between(0).andInclusive(4)
            .notNull(test).withMessage("I've changed")
            .notEmpty(Collections.singletonList("yoyo"))
            .verify();
    }
}
