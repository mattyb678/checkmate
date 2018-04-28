package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.mattyb.checkmate.checker.context.CheckerContext;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

class NullCheckersTest {

    private final CheckerContext ctx = mock(CheckerContext.class);

    @BeforeEach
    public void setUp() {
        reset(ctx);
    }

    @Test
    public void testNull_Null() {
        assertThat(NullCheckers.notNull.test(null, ctx), is(true));
    }

    @Test
    public void testNull_NotNull() {
        assertThat(NullCheckers.notNull.test("NotNull", ctx), is(false));
    }

    @Test
    public void testNull_Message() {
        assertThat(NullCheckers.notNull.getExceptionMessage(null, ctx), is("The validated object is null"));
    }

    @Test
    public void testNullElementsArray_Null() {
        assertThat(NullCheckers.noNullElementsArray.test(null, ctx), is(true));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(null, ctx), is("Array is null"));
    }

    @Test
    public void testNullElementsArray_Empty() {
        assertThat(NullCheckers.noNullElementsArray.test(new String[]{}, ctx), is(true));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(new String[]{}, ctx), is("Array is empty"));
    }

    @Test
    public void testNullElementsArray_NoNullElements() {
        final String[] array = new String[]{"Hello", "Not", "Null"};
        assertThat(NullCheckers.noNullElementsArray.test(array, ctx), is(false));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(array, ctx),
                is("The validated array contains no null elements, throwing an exception is a mistake"));
    }

    @Test
    public void testNullElementsArray_NullElements() {
        final String[] array = new String[]{"Hello", "Not", null, "fourth"};
        assertThat(NullCheckers.noNullElementsArray.test(array, ctx), is(true));
        assertThat(NullCheckers.noNullElementsArray.getExceptionMessage(array, ctx),
                is("The validated array contains null element at index: 2"));
    }

    @Test
    public void testNullElementsColl_Null() {
        assertThat(NullCheckers.noNullElementsIterable.test(null, ctx), is(true));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(null, ctx), is("The validated collection is null"));
    }

    @Test
    public void testNullElementsColl_Empty() {
        assertThat(NullCheckers.noNullElementsIterable.test(emptyList(), ctx), is(false));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(emptyList(), ctx),
                is("The validated collection has no null elements, throwing an exception is a mistake"));
    }

    @Test
    public void testNullElementsColl_NoNullElements() {
        assertThat(NullCheckers.noNullElementsIterable.test(asList("One", "Two", "Three"), ctx), is(false));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(asList("One", "Two", "Three"), ctx),
                is("The validated collection has no null elements, throwing an exception is a mistake"));
    }

    @Test
    public void testNullElementsColl_NullElements() {
        assertThat(NullCheckers.noNullElementsIterable.test(asList("One", null, "Two", "Three"), ctx), is(true));
        assertThat(NullCheckers.noNullElementsIterable.getExceptionMessage(asList("One", null, "Two", "Three"), ctx),
                is("The validated collection contains a null element at index: 1"));
    }
}