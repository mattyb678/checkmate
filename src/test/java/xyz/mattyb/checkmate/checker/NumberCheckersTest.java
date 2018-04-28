package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.mattyb.checkmate.Range;
import xyz.mattyb.checkmate.checker.context.CheckerContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

class NumberCheckersTest {

    private final CheckerContext ctx = mock(CheckerContext.class);

    @BeforeEach
    public void setUp() {
        reset(ctx);
    }

    @Test
    public void testIntRange_InRange() {
        final Range<Integer> range = new Range<>(15, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range, ctx), is(false));
    }

    @Test
    public void testLongRange_InRange() {
        final Range<Long> range = new Range<>(15000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range, ctx), is(false));
    }

    @Test
    public void testDoubleRange_InRange() {
        final Range<Double> range = new Range<>(15.0, 10.0, 20.0);
        assertThat(NumberCheckers.doubleOutOfRange.test(range, ctx), is(false));
    }

    @Test
    public void testIntRange_OutRangeLower() {
        final Range<Integer> range = new Range<>(5, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testLongRange_OutRangeLower() {
        final Range<Long> range = new Range<>(5000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testDoubleRange_OutRangeLower() {
        final Range<Double> range = new Range<>(5.0, 10.0, 20.0);
        assertThat(NumberCheckers.doubleOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testIntRange_OutRangeHigher() {
        final Range<Integer> range = new Range<>(25, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testLongRange_OutRangeHigher() {
        final Range<Long> range = new Range<>(25000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testDoubleRange_OutRangeHigher() {
        final Range<Double> range = new Range<>(35.0, 10.0, 20.0);
        assertThat(NumberCheckers.doubleOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testIntRange_Exclusive() {
        final Range<Integer> range = new Range<>(20, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testLongRange_Exclusive() {
        final Range<Long> range = new Range<>(20000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testDoubleRange_Exclusive() {
        final Range<Double> range = new Range<>(20.0, 10.0, 20.0);
        assertThat(NumberCheckers.doubleOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testIntRange_Inclusive() {
        final Range<Integer> range = new Range<>(20, 10, 20);
        range.setInclusive(true);
        assertThat(NumberCheckers.intOutOfRange.test(range, ctx), is(false));
    }

    @Test
    public void testLongRange_Inclusive() {
        final Range<Long> range = new Range<>(20000L, 10000L, 20000L);
        range.setInclusive(true);
        assertThat(NumberCheckers.longOutOfRange.test(range, ctx), is(false));
    }

    @Test
    public void testDoubleRange_Inclusive() {
        final Range<Double> range = new Range<>(20.0, 10.0, 20.0);
        range.setInclusive(true);
        assertThat(NumberCheckers.doubleOutOfRange.test(range, ctx), is(false));
    }

    @Test
    public void testIntRange_NullValue() {
        final Range<Integer> range = new Range<>();
        range.setStart(10);
        range.setEnd(20);
        range.setInclusive(true);
        assertThat(NumberCheckers.intOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testLongRange_NullValue() {
        final Range<Long> range = new Range<>(null, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testDoubleRange_NullValue() {
        final Range<Double> range = new Range<>(null, 10.0, 20.0);
        assertThat(NumberCheckers.doubleOutOfRange.test(range, ctx), is(true));
    }

    @Test
    public void testIntRange_NullRange() {
        assertThat(NumberCheckers.intOutOfRange.test(null, ctx), is(true));
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(null, ctx), is("Not in range"));
    }

    @Test
    public void testLongRange_NullRange() {
        assertThat(NumberCheckers.longOutOfRange.test(null, ctx), is(true));
        assertThat(NumberCheckers.longOutOfRange.getExceptionMessage(null, ctx), is("Not in range"));
    }

    @Test
    public void testDoubleRange_NullRange() {
        assertThat(NumberCheckers.doubleOutOfRange.test(null, ctx), is(true));
        assertThat(NumberCheckers.doubleOutOfRange.getExceptionMessage(null, ctx), is("Not in range"));
    }

    @Test
    public void testIntRange_MessageInclusive() {
        final Range<Integer> range = new Range<>(25, 10, 20);
        range.setInclusive(true);
        final String message = "25 is not between 10 and 20, inclusive";
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(range, ctx), is(message));
    }

    @Test
    public void testLongRange_MessageInclusive() {
        final Range<Long> range = new Range<>(25000L, 10000L, 20000L);
        range.setInclusive(true);
        final String message = "25000 is not between 10000 and 20000, inclusive";
        assertThat(NumberCheckers.longOutOfRange.getExceptionMessage(range, ctx), is(message));
    }

    @Test
    public void testDoubleRange_MessageInclusive() {
        final Range<Double> range = new Range<>(25.0, 10.0, 20.0);
        range.setInclusive(true);
        final String message = "25.0 is not between 10.0 and 20.0, inclusive";
        assertThat(NumberCheckers.doubleOutOfRange.getExceptionMessage(range, ctx), is(message));
    }

    @Test
    public void testIntRange_MessageExclusive() {
        final Range<Integer> range = new Range<>(25, 10, 20);
        final String message = "25 is not between 10 and 20, exclusive";
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(range, ctx), is(message));
    }

    @Test
    public void testLongRange_MessageExclusive() {
        final Range<Long> range = new Range<>(25000L, 10000L, 20000L);
        final String message = "25000 is not between 10000 and 20000, exclusive";
        assertThat(NumberCheckers.longOutOfRange.getExceptionMessage(range, ctx), is(message));
    }

    @Test
    public void testDoubleRange_MessageExclusive() {
        final Range<Double> range = new Range<>(25.0, 10.0, 20.0);
        final String message = "25.0 is not between 10.0 and 20.0, exclusive";
        assertThat(NumberCheckers.doubleOutOfRange.getExceptionMessage(range, ctx), is(message));
    }
}