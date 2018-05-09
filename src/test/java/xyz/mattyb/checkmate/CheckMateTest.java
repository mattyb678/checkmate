package xyz.mattyb.checkmate;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class CheckMateTest {

    @Test
    public void testWithDefaultException() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.checkWithDefault(SomeAppSpecificException.class)
                .notBlank("  ")
                .validate());
    }

    @Test
    public void testWithDefaultException_Override() {
        assertThrows(IllformedLocaleException.class, () -> CheckMate.checkWithDefault(SomeAppSpecificException.class)
                .notBlank("  ").withException(IllformedLocaleException.class)
                .validate());
    }

    @Test
    public void testWithDefaultException_OverrideNotFail() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.checkWithDefault(SomeAppSpecificException.class)
                .notEmpty(Collections.emptyMap())
                .notBlank(" s ").withException(IllformedLocaleException.class)
                .validate());
    }

    @Test
    public void testWithException() {
        try {
            CheckMate.check()
                    .notNull(null).withException(SomeAppSpecificException.class)
                    .validate();
            fail("Should not get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(SomeAppSpecificException.class))));
            assertThat(ex.getMessage(), is("The validated object is null"));
        }
    }

    @Test
    public void testThrowNpe_WithException() {
        try {
            CheckMate.check(CheckMate.Option.THROW_NPE)
                    .notNull(null).withException(SomeAppSpecificException.class)
                    .validate();
            fail("Should not get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(NullPointerException.class))));
            assertThat(ex.getMessage(), is("The validated object is null"));
        }
    }

    @Test
    public void testWithMessage() {
        try {
            CheckMate.check()
                    .notNull(null).withMessage("Changed the message")
                    .validate();
            fail("Should not get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(IllegalArgumentException.class))));
            assertThat(ex.getMessage(), is("Changed the message"));
        }
    }

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
                .intValue(7).between(0).and(10).inclusive()
                .anyInvalid();
        assertThat(anyInvalid, is(false));
    }

    @Test
    public void testAnyInvalid_MultipleChecksSingleInvalid() {
        boolean anyInvalid = CheckMate.check()
                .notEmpty(Collections.singletonMap("hello", "world"))
                .notEmpty("")
                .intValue(7).between(0).and(10).inclusive()
                .anyInvalid();
        assertThat(anyInvalid, is(true));
    }

    @Test
    public void testAnyInvalid_MultipleChecksInvalid() {
        boolean anyInvalid = CheckMate.check()
                .notEmpty(Collections.emptyMap())
                .notEmpty("")
                .intValue(-7).between(0).and(10).inclusive()
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
                .intValue(7).between(0).and(10).inclusive()
                .notEmpty(Collections.singletonMap("hello", "world"))
                .allInvalid();
        assertThat(allInvalid, is(false));
    }

    @Test
    public void testAllInvalid_MultipleInvalid() {
        boolean allInvalid = CheckMate.check()
                .notEmpty("")
                .notEmpty(Collections.emptyMap())
                .intValue(-7).between(0).and(10).inclusive()
                .allInvalid();
        assertThat(allInvalid, is(true));
    }

    @Test
    public void testAllInvalid_Mix() {
        boolean allInvalid = CheckMate.check()
                .notEmpty("")
                .notEmpty(Collections.emptyMap())
                .notEmpty("Not Empty")
                .intValue(-7).between(0).and(10).inclusive()
                .allInvalid();
        assertThat(allInvalid, is(false));
    }

    @Test
    public void testIntRange() {
        CheckMate.check()
                .intValue(7).between(5).and(100)
                .validate();
    }

    @Test
    public void testIntRange_OutOfRange() {
        try {
            CheckMate.check()
                    .intValue(2).between(5).and(100)
                    .validate();
            fail("Should never get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(IllegalArgumentException.class))));
            assertThat(ex.getMessage(), is("2 is not between 5 and 100, exclusive"));
        }
    }

    @Test
    public void testDoubleRange() {
        CheckMate.check()
                .doubleValue(20.0).between(10.0).and(40.0)
                .validate();
    }

    @Test
    public void testDoubleRange_OutOfRange() {
        try {
            CheckMate.check()
                    .doubleValue(2.0).between(5.0).and(100.0)
                    .validate();
            fail("Should never get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(IllegalArgumentException.class))));
            assertThat(ex.getMessage(), is("2.0 is not between 5.0 and 100.0, exclusive"));
        }
    }

    @Test
    public void testDoubleRange_Null() {
        try {
            CheckMate.check()
                    .doubleValue(2.0).between(null).and(100.0)
                    .validate();
            fail("Should never get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(IllegalArgumentException.class))));
            assertThat(ex.getMessage(), is("2.0 is not between null and 100.0, exclusive"));
        }
    }

    @Test
    public void testDoubleRange_NullThrowNpe() {
        try {
            CheckMate.check(CheckMate.Option.THROW_NPE)
                    .doubleValue(2.0).between(null).and(100.0)
                    .validate();
            fail("Should never get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(NullPointerException.class))));
            assertThat(ex.getMessage(), is("2.0 is not between null and 100.0, exclusive"));
        }
    }

    @Test
    public void testLongRange() {
        CheckMate.check()
                .longValue(1776L).between(711L).and(2000L)
                .validate();
    }

    @Test
    public void testLongRange_OutOfRange() {
        try {
            CheckMate.check()
                    .longValue(2018L).between(711L).and(2000L).inclusive()
                    .validate();
            fail("Should never get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(IllegalArgumentException.class))));
            assertThat(ex.getMessage(), is("2018 is not between 711 and 2000, inclusive"));
        }
    }

    @Test
    public void testLongRange_Null() {
        try {
            CheckMate.check()
                    .longValue(2018L).between(711L).and(null).inclusive()
                    .validate();
            fail("Should never get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(IllegalArgumentException.class))));
            assertThat(ex.getMessage(), is("2018 is not between 711 and null, inclusive"));
        }
    }

    @Test
    public void testLongRange_NullThrowNpe() {
        try {
            CheckMate.check(CheckMate.Option.THROW_NPE)
                    .longValue(2018L).between(711L).and(null).inclusive()
                    .validate();
            fail("Should never get here");
        } catch (Exception ex) {
            assertThat(ex, is((instanceOf(NullPointerException.class))));
            assertThat(ex.getMessage(), is("2018 is not between 711 and null, inclusive"));
        }
    }

    @Test
    public void testNoNullElementsArray() {
        final String[] array = new String[]{"Hello", "Not", null, "fourth"};
        assertThrows(IllegalArgumentException.class, () -> CheckMate.check()
                .noNullElements(array)
                .validate());
    }

    @Test
    public void testNoNullElementsCollection() {
        final List<String> coll = asList("One", null, "Two", "Three");
        assertThrows(IllegalArgumentException.class, () -> CheckMate.check().noNullElements(coll).validate());
    }

    @Test
    public void testNotEmptyArray_Null() {
        assertThrows(IllegalArgumentException.class, () -> CheckMate.check()
                .notEmpty((Object[]) null)
                .validate());
    }

    @Test
    public void testNotEmptyArray_NullThrowNpe() {
        assertThrows(NullPointerException.class, () -> CheckMate.check(CheckMate.Option.THROW_NPE)
                .notEmpty((Object[]) null)
                .validate());
    }

    @Test
    public void testNotEmptyArray_Empty() {
        assertThrows(IllegalArgumentException.class, () -> CheckMate.check()
                .notEmpty(new String[]{})
                .validate());
    }

    @Test
    public void testNotEmptyArray_NotEmpty() {
        CheckMate.check()
                .notEmpty(new String[]{"Hello", "World"})
                .validate();
    }

    @Test
    public void testValidIndex_Valid() {
        CheckMate.check()
                .isIndex(1).validIn("hello")
                .validate();

        CheckMate.check()
                .isIndex(3).validIn(Arrays.asList("bigger", "list", "three", "four", "five", "six"))
                .validate();
    }

    @Test
    public void testValidIndex_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> CheckMate.check().isIndex(10).validIn(new String[]{"hi"}).validate());
    }

    @Test
    public void testNotBlank_Empty() {
        assertThrows(IllegalArgumentException.class, () -> CheckMate.check().notBlank("").validate());
    }

    @Test
    public void testNotBlank_Blank() {
        assertThrows(IllegalArgumentException.class, () -> CheckMate.check().notBlank("        ").validate());
    }

    @Test
    public void testNotBlank_NotBlank() {
        CheckMate.check().notBlank("     not   ").validate();
    }

    @Test
    public void testIsTruthy_BooleanTrue() {
        CheckMate.check()
            .is(100 > 19).truthy().withException(SomeAppSpecificException.class)
            .validate();
    }

    @Test
    public void testIsTruthy_BooleanFalse() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.check()
            .is(10 > 19).truthy().withException(SomeAppSpecificException.class)
            .validate());
    }

    @Test
    public void testIsFalsy_BooleanTrue() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.check()
                .is(100 > 19).falsy().withException(SomeAppSpecificException.class)
                .validate());
    }

    @Test
    public void testIsFalsy_BooleanFalse() {
        CheckMate.check()
            .is(10 > 19).falsy().withException(SomeAppSpecificException.class)
            .validate();
    }

    @Test
    public void testIsTruthy_SupplierTrue() {
        CheckMate.check()
            .is(() -> "Matt".length() == 4).truthy().withException(SomeAppSpecificException.class)
            .validate();
    }

    @Test
    public void testIsTruthy_SupplierFalse() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.check()
            .is(() -> "Matt".length() == 5).truthy().withException(SomeAppSpecificException.class)
            .validate());
    }

    @Test
    public void testIsFalsy_SupplierTrue() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.check()
            .is(() -> "Matt".length() == 4).falsy().withException(SomeAppSpecificException.class)
            .validate());
    }

    @Test
    public void testIsFalsy_SupplierFalse() {
        CheckMate.check()
            .is(() -> "Matt".length() == 5).falsy().withException(SomeAppSpecificException.class)
            .validate();
    }

    @Test
    public void testInstanceOf_Exception() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.check()
                .object("yo").isInstanceOf(Number.class).withException(SomeAppSpecificException.class)
                .validate());
    }

    @Test
    public void testInstanceOf_NullException() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.check()
                .object(null).isInstanceOf(Number.class).withException(SomeAppSpecificException.class)
                .validate());
    }

    @Test
    public void testInstanceOf_NullExceptionThrowNpe() {
        assertThrows(NullPointerException.class, () -> CheckMate.check(CheckMate.Option.THROW_NPE)
                .object(null).isInstanceOf(Number.class).withException(SomeAppSpecificException.class)
                .validate());
    }

    @Test
    public void testInstanceOf_NullInstance() {
        assertThrows(SomeAppSpecificException.class, () -> CheckMate.check()
                .object("yo").isInstanceOf(null).withException(SomeAppSpecificException.class)
                .validate());
    }

    @Test
    public void testInstanceOf_NullInstanceThrowNpe() {
        assertThrows(NullPointerException.class, () -> CheckMate.check(CheckMate.Option.THROW_NPE)
                .object("yo").isInstanceOf(null).withException(SomeAppSpecificException.class)
                .validate());
    }

    @Test
    public void testInstanceOf() {
        CheckMate.check()
                .object("yo").isInstanceOf(String.class).withException(SomeAppSpecificException.class)
                .validate();
    }

    @Test
    public void testInstanceOf_ThrowNpe() {
        CheckMate.check(CheckMate.Option.THROW_NPE)
                .object("yo").isInstanceOf(String.class).withException(SomeAppSpecificException.class)
                .validate();
    }
}