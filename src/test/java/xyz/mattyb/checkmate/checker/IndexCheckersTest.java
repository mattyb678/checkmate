package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;
import xyz.mattyb.checkmate.Index;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class IndexCheckersTest {

    @Test
    public void testIndex_NullIndex() {
        assertThat(IndexCheckers.indexChecker.test(null), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(null), is("The index is null, that shouldn't happen"));
    }

    @Test
    public void testIndex_ArrayNull() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize((Object[]) null);

        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated array index is invalid: 5"));
    }

    @Test
    public void testIndex_StringNull() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize((String) null);

        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated character sequence index is invalid: 5"));
    }

    @Test
    public void testIndex_CollectionNull() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize((Collection<?>) null);

        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated collection index is invalid: 5"));
    }

    @Test
    public void testIndex_ArrayInvalid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(new String[]{"Small", "Array"});

        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated array index is invalid: 5"));

        index.setIndex(-1);
        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated array index is invalid: -1"));
    }

    @Test
    public void testIndex_StringInvalid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize("hi");

        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated character sequence index is invalid: 5"));

        index.setIndex(-1);
        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated character sequence index is invalid: -1"));
    }

    @Test
    public void testIndex_CollectionInvalid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(Arrays.asList("Small", "list"));

        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated collection index is invalid: 5"));

        index.setIndex(-1);
        assertThat(IndexCheckers.indexChecker.test(index), is(true));
        assertThat(IndexCheckers.indexChecker.getExceptionMessage(index), is("The validated collection index is invalid: -1"));
    }

    @Test
    public void testIndex_ArrayValid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(new String[]{"bigger", "Array", "three", "four", "five", "six"});

        assertThat(IndexCheckers.indexChecker.test(index), is(false));
    }

    @Test
    public void testIndex_StringValid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize("Hello World");

        assertThat(IndexCheckers.indexChecker.test(index), is(false));
    }

    @Test
    public void testIndexCollectionValid() {
        final Index index = new Index();
        index.setIndex(5);
        index.calcSize(Arrays.asList("bigger", "list", "three", "four", "five", "six"));

        assertThat(IndexCheckers.indexChecker.test(index), is(false));
    }
}