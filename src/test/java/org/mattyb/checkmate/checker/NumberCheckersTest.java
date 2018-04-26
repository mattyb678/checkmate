package org.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;
import org.mattyb.checkmate.Range;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class NumberCheckersTest {

    @Test
    public void testIntRange_InRange() {
        final Range<Integer> range = new Range<>();
        range.setValue(15);
        range.setStart(10);
        range.setEnd(20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(false));
    }

    @Test
    public void testIntRange_OutRangeLower() {
        final Range<Integer> range = new Range<>();
        range.setValue(5);
        range.setStart(10);
        range.setEnd(20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(true));
    }

    @Test
    public void testIntRange_OutRangeHigher() {
        final Range<Integer> range = new Range<>();
        range.setValue(25);
        range.setStart(10);
        range.setEnd(20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(true));
    }

    @Test
    public void testIntRange_Exclusive() {
        final Range<Integer> range = new Range<>();
        range.setValue(20);
        range.setStart(10);
        range.setEnd(20);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(true));
    }

    @Test
    public void testIntRange_Inclusive() {
        final Range<Integer> range = new Range<>();
        range.setValue(20);
        range.setStart(10);
        range.setEnd(20);
        range.setInclusive(true);
        assertThat(NumberCheckers.intOutOfRange.test(range), is(false));
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
    public void testIntRange_NullRange() {
        assertThat(NumberCheckers.intOutOfRange.test(null), is(true));
    }

    @Test
    public void testIntRange_MessageNull() {
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(null), is("Not in range"));
    }

    @Test
    public void testIntRange_MessageInclusive() {
        final Range<Integer> range = new Range<>();
        range.setValue(25);
        range.setStart(10);
        range.setEnd(20);
        range.setInclusive(true);
        final String message = "25 is not between 10 and 20, inclusive";
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(range), is(message));
    }

    @Test
    public void testIntRange_MessageExclusive() {
        final Range<Integer> range = new Range<>();
        range.setValue(25);
        range.setStart(10);
        range.setEnd(20);
        final String message = "25 is not between 10 and 20, exclusive";
        assertThat(NumberCheckers.intOutOfRange.getExceptionMessage(range), is(message));
    }
}