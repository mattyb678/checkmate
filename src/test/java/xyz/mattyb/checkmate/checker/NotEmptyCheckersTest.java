package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

class NotEmptyCheckersTest {

    @Test
    public void testCollection_Null() {
        assertThat(NotEmptyCheckers.collection.test(null), is(true));
    }

    @Test
    public void testCollection_ListEmpty() {
        assertThat(NotEmptyCheckers.collection.test(Collections.emptyList()), is(true));
    }

    @Test
    public void testCollection_SetSingle() {
        assertThat(NotEmptyCheckers.collection.test(Collections.singleton("Hey")), is(false));
    }

    @Test
    public void testCollection_Message() {
        assertThat(NotEmptyCheckers.collection.getExceptionMessage(Collections.emptyList()), is("The validated collection is empty"));
    }

    @Test
    public void testCharSequence_Null() {
        assertThat(NotEmptyCheckers.charSequence.test(null), is(true));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test(null), is(true));
    }

    @Test
    public void testCharSequence_Empty() {
        assertThat(NotEmptyCheckers.charSequence.test(""), is(true));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test(""), is(true));
    }

    @Test
    public void testCharSequence_Blank() {
        assertThat(NotEmptyCheckers.charSequence.test("        "), is(false));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test("        "), is(true));
    }

    @Test
    public void testCharSequence_NotBlank() {
        assertThat(NotEmptyCheckers.charSequence.test("matt"), is(false));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test("matt"), is(false));
    }

    @Test
    public void testCharSequence_Message() {
        assertThat(NotEmptyCheckers.charSequence.getExceptionMessage("matt"), is("The validated character sequence is empty"));
    }

    @Test
    public void testArray_Null() {
        assertThat(NotEmptyCheckers.array.test(null), is(true));
    }

    @Test
    public void testArray_Empty() {
        assertThat(NotEmptyCheckers.array.test(new String[]{}), is(true));
    }

    @Test
    public void testArray_NotEmpty() {
        assertThat(NotEmptyCheckers.array.test(new String[]{"hello", "world"}), is(false));
    }

    @Test
    public void testArray_Message() {
        assertThat(NotEmptyCheckers.array.getExceptionMessage(new String[]{}), is("The validated array is empty"));
    }

    @Test
    public void testMap_Null() {
        assertThat(NotEmptyCheckers.map.test(null), is(true));
    }

    @Test
    public void testMap_Empty() {
        assertThat(NotEmptyCheckers.map.test(Collections.emptyMap()), is(true));
    }

    @Test
    public void testMap_NotEmpty() {
        assertThat(NotEmptyCheckers.map.test(Collections.singletonMap("hello", "world")), is(false));
    }

    @Test
    public void testMap_Message() {
        assertThat(NotEmptyCheckers.map.getExceptionMessage(Collections.emptyMap()), is("The validated map is empty"));
    }
}