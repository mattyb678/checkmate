package xyz.mattyb.checkmate;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CheckMateTest {

    @Test
    public void testAnyInvalid_NoChecks() {
        boolean anyInvalid = CheckMate.check().anyInvalid();
        assertThat(anyInvalid, is(false));
    }

    @Test
    public void testAnyInvalid_SingleCheckValid() {
        boolean anyInvalid = CheckMate.check()
            .notEmpty("Valid")
            .anyInvalid();
        assertThat(anyInvalid, is(false));
    }

    @Test
    public void testAnyInvalid_SingleCheckInvalid() {
        boolean anyInvalid = CheckMate.check()
                .notEmpty(Collections.emptyList())
                .anyInvalid();
        assertThat(anyInvalid, is(true));
    }

    @Test
    public void testAnyInvalid_MultipleChecksValid() {
        boolean anyInvalid = CheckMate.check()
                .notEmpty("Valid")
                .notEmpty(Collections.singletonMap("hello", "world"))
                .value(7).between(0).and(10).inclusive()
                .anyInvalid();
        assertThat(anyInvalid, is(false));
    }

    @Test
    public void testAnyInvalid_MultipleChecksSingleInvalid() {
        boolean anyInvalid = CheckMate.check()
                .notEmpty(Collections.singletonMap("hello", "world"))
                .notEmpty("")
                .value(7).between(0).and(10).inclusive()
                .anyInvalid();
        assertThat(anyInvalid, is(true));
    }

    @Test
    public void testAnyInvalid_MultipleChecksInvalid() {
        boolean anyInvalid = CheckMate.check()
                .notEmpty(Collections.emptyMap())
                .notEmpty("")
                .value(-7).between(0).and(10).inclusive()
                .anyInvalid();
        assertThat(anyInvalid, is(true));
    }

    @Test
    public void testAllInvalid_NoChecks() {
        boolean allInvalid = CheckMate.check().allInvalid();
        assertThat(allInvalid, is(true));
    }

    @Test
    public void testAllInvalid_SingleValid() {
        boolean allInvalid = CheckMate.check()
                .notEmpty("Not Empty")
                .allInvalid();
        assertThat(allInvalid, is(false));
    }

    @Test
    public void testAllInvalid_SingleInvalid() {
        boolean allInvalid = CheckMate.check()
                .notEmpty("")
                .allInvalid();
        assertThat(allInvalid, is(true));
    }

    @Test
    public void testAllInvalid_MultipleValid() {
        boolean allInvalid = CheckMate.check()
                .notEmpty("Not Empty")
                .value(7).between(0).and(10).inclusive()
                .notEmpty(Collections.singletonMap("hello", "world"))
                .allInvalid();
        assertThat(allInvalid, is(false));
    }

    @Test
    public void testAllInvalid_MultipleInvalid() {
        boolean allInvalid = CheckMate.check()
                .notEmpty("")
                .notEmpty(Collections.emptyMap())
                .value(-7).between(0).and(10).inclusive()
                .allInvalid();
        assertThat(allInvalid, is(true));
    }

    @Test
    public void testAllInvalid_Mix() {
        boolean allInvalid = CheckMate.check()
                .notEmpty("")
                .notEmpty(Collections.emptyMap())
                .notEmpty("Not Empty")
                .value(-7).between(0).and(10).inclusive()
                .allInvalid();
        assertThat(allInvalid, is(false));
    }
}