package org.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mattyb.checkmate.checker.NotEmptyCheckers.charSequence;
import static org.mattyb.checkmate.checker.NotEmptyCheckers.collection;

class NotEmptyCheckersTest {

    @Test
    public void testCollection_Null() {
        assertThat(collection.test(null), is(true));
    }

    @Test
    public void testCollection_ListEmpty() {
        assertThat(collection.test(Collections.emptyList()), is(true));
    }

    @Test
    public void testCollection_SetSingle() {
        assertThat(collection.test(Collections.singleton("Hey")), is(false));
    }

    @Test
    public void testCollection_Message() {
        assertThat(collection.getExceptionMessage(Collections.emptyList()), is("The validated collection is empty"));
    }

    @Test
    public void testCharSequence_Null() {
        assertThat(charSequence.test(null), is(true));
    }

    @Test
    public void testCharSequence_Empty() {
        assertThat(charSequence.test(""), is(true));
    }

    @Test
    public void testCharSequence_Blank() {
        assertThat(charSequence.test("        "), is(true));
    }

    @Test
    public void testCharSequence_NotBlank() {
        assertThat(charSequence.test("matt"), is(false));
    }

    @Test
    public void testCharSequence_Message() {
        assertThat(charSequence.getExceptionMessage("matt"), is("The validated character sequence is empty"));
    }
}