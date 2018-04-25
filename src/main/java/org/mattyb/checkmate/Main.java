package org.mattyb.checkmate;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String test = "";
        final List<String> list = Collections.emptyList(); //Collections.singletonList("yoyo");
        CheckMate.check()
            .value(4).between(0).and(4).inclusive()
            .notNull(test).withMessage("I've changed")
            .notEmpty(list).withException(SomeAppSpecificException.class)
            .verify();
    }
}
