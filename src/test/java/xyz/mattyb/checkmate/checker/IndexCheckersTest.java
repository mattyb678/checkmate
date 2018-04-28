package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.mattyb.checkmate.Index;
import xyz.mattyb.checkmate.checker.context.CheckerContext;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

class IndexCheckersTest {

    private final CheckerContext ctx = mock(CheckerContext.class);

    @BeforeEach
    public void setUp() {
        reset(ctx);
    }

    @Test
    public void testIndex_NullIndex() {
        assertThat(IndexCheckers.indexChecker.test(null, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(null, ctx), is("The index is null, that shouldn't happen"));
    }

    @Test
    public void testIndex_ArrayNull() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize((Object[]) null);

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated array index is invalid: 5"));
    }

    @Test
    public void testIndex_StringNull() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize((String) null);

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated character sequence index is invalid: 5"));
    }

    @Test
    public void testIndex_CollectionNull() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize((Collection<?>) null);

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated collection index is invalid: 5"));
    }

    @Test
    public void testIndex_ArrayInvalid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(new String[]{"Small", "Array"});

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated array index is invalid: 5"));

        index.setIndex(-1);
        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated array index is invalid: -1"));
    }

    @Test
    public void testIndex_StringInvalid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize("hi");

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated character sequence index is invalid: 5"));

        index.setIndex(-1);
        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated character sequence index is invalid: -1"));
    }

    @Test
    public void testIndex_CollectionInvalid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(Arrays.asList("Small", "list"));

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated collection index is invalid: 5"));

        index.setIndex(-1);
        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index, ctx), is("The validated collection index is invalid: -1"));
    }

    @Test
    public void testIndex_ArrayValid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(new String[]{"bigger", "Array", "three", "four", "five", "six"});

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(false));
    }

    @Test
    public void testIndex_StringValid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize("Hello World");

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(false));
    }

    @Test
    public void testIndexCollectionValid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(Arrays.asList("bigger", "list", "three", "four", "five", "six"));

        assertThat(IndexCheckers.indexChecker.test(index, ctx), is(false));
    }
}