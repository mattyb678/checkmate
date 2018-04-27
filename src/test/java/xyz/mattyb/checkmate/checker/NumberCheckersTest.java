package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;
import xyz.mattyb.checkmate.Range;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class NumberCheckersTest {

    @Test
    public void testIntRange_InRange() {
        final Range<Integer> range = new Range<>(15, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(false));
    }

    @Test
    public void testLongRange_InRange() {
        final Range<Long> range = new Range<>(15000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range), is(false));
    }

    @Test
    public void testIntRange_OutRangeLower() {
        final Range<Integer> range = new Range<>(5, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(true));
    }

    @Test
    public void testLongRange_OutRangeLower() {
        final Range<Long> range = new Range<>(5000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range), is(true));
    }

    @Test
    public void testIntRange_OutRangeHigher() {
        final Range<Integer> range = new Range<>(25, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(true));
    }

    @Test
    public void testLongRange_OutRangeHigher() {
        final Range<Long> range = new Range<>(25000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range), is(true));
    }

    @Test
    public void testIntRange_Exclusive() {
        final Range<Integer> range = new Range<>(20, 10, 20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(true));
    }

    @Test
    public void testLongRange_Exclusive() {
        final Range<Long> range = new Range<>(20000L, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range), is(true));
    }

    @Test
    public void testIntRange_Inclusive() {
        final Range<Integer> range = new Range<>(20, 10, 20);
        range.setInclusive(true);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(false));
    }

    @Test
    public void testLongRange_Inclusive() {
        final Range<Long> range = new Range<>(20000L, 10000L, 20000L);
        range.setInclusive(true);
        assertThat(NumberCheckers.longOutOfRange.test(range), is(false));
    }

    @Test
    public void testIntRange_NullValue() {
        final Range<Integer> range = new Range<>();
        range.setStart(10);
        range.setEnd(20);
        range.setInclusive(true);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(true));
    }

    @Test
    public void testLongRange_NullValue() {
        final Range<Long> range = new Range<>(null, 10000L, 20000L);
        assertThat(NumberCheckers.longOutOfRange.test(range), is(true));
    }

    @Test
    public void testIntRange_NullRange() {
        assertThat(NumberCheckers.intOutOfRange.test(null), is(true));
    }

    @Test
    public void testLongRange_NullRange() {
        assertThat(NumberCheckers.longOutOfRange.test(null), is(true));
    }

    @Test
    public void testIntRange_MessageNull() {
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(null), is("Not in range"));
    }

    @Test
    public void testLongRange_MessageNull() {
        assertThat(NumberCheckers.longOutOfRange.getExceptionMessage(null), is("Not in range"));
    }

    @Test
    public void testIntRange_MessageInclusive() {
        final Range<Integer> range = new Range<>(25, 10, 20);
        range.setInclusive(true);
        final String message = "25 is not between 10 and 20, inclusive";
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(range), is(message));
    }

    @Test
    public void testLongRange_MessageInclusive() {
        final Range<Long> range = new Range<>(25000L, 10000L, 20000L);
        range.setInclusive(true);
        final String message = "25000 is not between 10000 and 20000, inclusive";
        assertThat(NumberCheckers.longOutOfRange.getExceptionMessage(range), is(message));
    }

    @Test
    public void testIntRange_MessageExclusive() {
        final Range<Integer> range = new Range<>(25, 10, 20);
        final String message = "25 is not between 10 and 20, exclusive";
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(range), is(message));
    }

    @Test
    public void testLongRange_MessageExclusive() {
        final Range<Long> range = new Range<>(25000L, 10000L, 20000L);
        final String message = "25000 is not between 10000 and 20000, exclusive";
        assertThat(NumberCheckers.longOutOfRange.getExceptionMessage(range), is(message));
    }
}