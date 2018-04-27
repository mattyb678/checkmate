package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
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
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(array),
                is("The validated array contains no null elements, throwing an exception is a mistake"));
    }

    @Test
    public void testNullElementsArray_NullElements() {
        final String[] array = new String[]{"Hello", "Not", null, "fourth"};
        assertThat(NullCheckers.noNullElementsArray.test(array), is(true));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(array),
                is("The validated array contains null element at index: 2"));
    }

    @Test
    public void testNullElementsColl_Null() {
        assertThat(NullCheckers.noNullElementsIterable.test(null), is(true));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(null), is("The validated collection is null"));
    }

    @Test
    public void testNullElementsColl_Empty() {
        assertThat(NullCheckers.noNullElementsIterable.test(emptyList()), is(false));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(emptyList()),
                is("The validated collection has no null elements, throwing an exception is a mistake"));
    }

    @Test
    public void testNullElementsColl_NoNullElements() {
        assertThat(NullCheckers.noNullElementsIterable.test(asList("One", "Two", "Three")), is(false));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(asList("One", "Two", "Three")),
                is("The validated collection has no null elements, throwing an exception is a mistake"));
    }

    @Test
    public void testNullElementsColl_NullElements() {
        assertThat(NullCheckers.noNullElementsIterable.test(asList("One", null, "Two", "Three")), is(true));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(asList("One", null, "Two", "Three")),
                is("The validated collection contains a null element at index: 1"));
    }
}