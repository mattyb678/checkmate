package xyz.mattyb.checkmate.checker;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class NotEmptyCheckersTest {

    @Test
    public void testCollection_Null() {
        MatcherAssert.assertThat(NotEmptyCheckers.collection.test(null), is(true));
    }

    @Test
    public void testCollection_ListEmpty() {
        MatcherAssert.assertThat(NotEmptyCheckers.collection.test(Collections.emptyList()), is(true));
    }

    @Test
    public void testCollection_SetSingle() {
        MatcherAssert.assertThat(NotEmptyCheckers.collection.test(Collections.singleton("Hey")), is(false));
    }

    @Test
    public void testCollection_Message() {
        MatcherAssert.assertThat(NotEmptyCheckers.collection.getExceptionMessage(Collections.emptyList()), is("The validated collection is empty"));
    }

    @Test
    public void testCharSequence_Null() {
        MatcherAssert.assertThat(NotEmptyCheckers.charSequence.test(null), is(true));
    }

    @Test
    public void testCharSequence_Empty() {
        MatcherAssert.assertThat(NotEmptyCheckers.charSequence.test(""), is(true));
    }

    @Test
    public void testCharSequence_Blank() {
        MatcherAssert.assertThat(NotEmptyCheckers.charSequence.test("        "), is(false));
    }

    @Test
    public void testCharSequence_NotBlank() {
        MatcherAssert.assertThat(NotEmptyCheckers.charSequence.test("matt"), is(false));
    }

    @Test
    public void testCharSequence_Message() {
        MatcherAssert.assertThat(NotEmptyCheckers.charSequence.getExceptionMessage("matt"), is("The validated character sequence is empty"));
    }
}