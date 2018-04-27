package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CheckersTest {

    @Test
    public void testNull_Null() {
        assertThat(Checkers.notNull.test(null), is(true));
    }

    @Test
    public void testNull_NotNull() {
        assertThat(Checkers.notNull.test("NotNull"), is(false));
    }

    @Test
    public void testNull_Message() {
        assertThat(Checkers.notNull.getExceptionMessage(null), is("The validated object is null"));
    }
}