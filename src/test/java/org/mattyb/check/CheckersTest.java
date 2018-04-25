package org.mattyb.check;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CheckersTest {

    @Test
    void test() {
        assertThat(true, is(true));
    }
}