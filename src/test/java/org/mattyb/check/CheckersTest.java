package org.mattyb.check;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CheckersTest {

    @Test
    public void test() {
        assertThat(true, is(true));
    }
}