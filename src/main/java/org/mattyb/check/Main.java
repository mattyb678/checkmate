package org.mattyb.check;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        final String test = null;
        Check.check()
            .notNull(test).withMessage("I've changed")
            .notEmpty(Collections.singletonList("yoyo"))
            .verify();
    }
}
