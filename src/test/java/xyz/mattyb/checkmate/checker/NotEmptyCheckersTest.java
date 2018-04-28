package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.mattyb.checkmate.checker.context.CheckerContext;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

class NotEmptyCheckersTest {

    private final CheckerContext ctx = mock(CheckerContext.class);

    @BeforeEach
    public void setUp() {
        reset(ctx);
    }

    @Test
    public void testCollection_Null() {
        assertThat(NotEmptyCheckers.collection.test(null, ctx), is(true));
    }

    @Test
    public void testCollection_ListEmpty() {
        assertThat(NotEmptyCheckers.collection.test(Collections.emptyList(), ctx), is(true));
    }

    @Test
    public void testCollection_SetSingle() {
        assertThat(NotEmptyCheckers.collection.test(Collections.singleton("Hey"), ctx), is(false));
    }

    @Test
    public void testCollection_Message() {
        assertThat(NotEmptyCheckers.collection.getExceptionMessage(Collections.emptyList(), ctx), is("The validated collection is empty"));
    }

    @Test
    public void testCharSequence_Null() {
        assertThat(NotEmptyCheckers.charSequence.test(null, ctx), is(true));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test(null, ctx), is(true));
    }

    @Test
    public void testCharSequence_Empty() {
        assertThat(NotEmptyCheckers.charSequence.test("", ctx), is(true));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test("", ctx), is(true));
    }

    @Test
    public void testCharSequence_Blank() {
        assertThat(NotEmptyCheckers.charSequence.test("        ", ctx), is(false));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test("        ", ctx), is(true));
    }

    @Test
    public void testCharSequence_NotBlank() {
        assertThat(NotEmptyCheckers.charSequence.test("matt", ctx), is(false));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.test("matt", ctx), is(false));
    }

    @Test
    public void testCharSequence_Message() {
        assertThat(NotEmptyCheckers.charSequence.getExceptionMessage("", ctx), is("The validated character sequence is empty"));
        assertThat(NotEmptyCheckers.charSequenceNotBlank.getExceptionMessage("   ", ctx), is("The validated character sequence is blank"));
    }

    @Test
    public void testArray_Null() {
        assertThat(NotEmptyCheckers.array.test(null, ctx), is(true));
    }

    @Test
    public void testArray_Empty() {
        assertThat(NotEmptyCheckers.array.test(new String[]{}, ctx), is(true));
    }

    @Test
    public void testArray_NotEmpty() {
        assertThat(NotEmptyCheckers.array.test(new String[]{"hello", "world"}, ctx), is(false));
    }

    @Test
    public void testArray_Message() {
        assertThat(NotEmptyCheckers.array.getExceptionMessage(new String[]{}, ctx), is("The validated array is empty"));
    }

    @Test
    public void testMap_Null() {
        assertThat(NotEmptyCheckers.map.test(null, ctx), is(true));
    }

    @Test
    public void testMap_Empty() {
        assertThat(NotEmptyCheckers.map.test(Collections.emptyMap(), ctx), is(true));
    }

    @Test
    public void testMap_NotEmpty() {
        assertThat(NotEmptyCheckers.map.test(Collections.singletonMap("hello", "world"), ctx), is(false));
    }

    @Test
    public void testMap_Message() {
        assertThat(NotEmptyCheckers.map.getExceptionMessage(Collections.emptyMap(), ctx), is("The validated map is empty"));
    }
}