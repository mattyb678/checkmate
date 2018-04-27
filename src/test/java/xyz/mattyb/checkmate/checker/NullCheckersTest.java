package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class NullCheckersTest {

    @Test
    public void testNull_Null() {
        assertThat(NullCheckers.notNull.test(null), is(true));
    }

    @Test
    public void testNull_NotNull() {
        assertThat(NullCheckers.notNull.test("NotNull"), is(false));
    }

    @Test
    public void testNull_Message() {
        assertThat(NullCheckers.notNull.getExceptionMessage(null), is("The validated object is null"));
    }

    @Test
    public void testNullElementsArray_Null() {
        assertThat(NullCheckers.noNullElementsArray.test(null), is(true));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(null), is("Array is null"));
    }

    @Test
    public void testNullElementsArray_Empty() {
        assertThat(NullCheckers.noNullElementsArray.test(new String[]{}), is(true));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(new String[]{}), is("Array is empty"));
    }

    @Test
    public void testNullElementsArray_NoNullElements() {
        final String[] array = new String[]{"Hello", "Not", "Null"};
        assertThat(NullCheckers.noNullElementsArray.test(array), is(false));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(array), is("The validated array contains null elements"));
    }

    @Test
    public void testNullElementsArray_NullElements() {
        final String[] array = new String[]{"Hello", "Not", null, "fourth"};
        assertThat(NullCheckers.noNullElementsArray.test(array), is(true));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(array), is("The validated array contains null element at index: 2"));
    }
}