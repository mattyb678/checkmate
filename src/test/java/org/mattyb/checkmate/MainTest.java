package org.mattyb.checkmate;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class MainTest {

    @Test
    public void test() {
        final String test = "";
        final String[] array = new String[]{"hey"};
        final List<String> list = Collections.singletonList("yoyo"); //Collections.emptyList();
        CheckMate.check()
                .notEmpty(array)
                .notEmpty("  s   ").withMessage("I'm empty").withException(SomeAppSpecificException.class)
                .value(4).between(0).and(4).inclusive()
                .notNull(test).withMessage("I've changed")
                .notEmpty(list).withException(SomeAppSpecificException.class)
                .validate();
    }
}
