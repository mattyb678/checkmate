package xyz.mattyb.checkmate.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.mattyb.checkmate.BooleanCheck;
import xyz.mattyb.checkmate.checker.context.CheckerContext;
import xyz.mattyb.checkmate.checker.context.NoOpCheckerContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class BooleanCheckersTest {

    private static final CheckerContext ctx = mock(CheckerContext.class);

    @BeforeEach
    public void setUp() {
        reset(ctx);
    }

    @Test
    public void testIsTrue_ExpectedTrue() {
        BooleanCheck check = new BooleanCheck();
        check.setSupplier(() -> true);
        check.setExpected(true);

        assertThat(BooleanCheckers.isBoolean.test(check, ctx), is(false));
        verifyZeroInteractions(ctx);
    }

    @Test
    public void testSupplierNull() {
        BooleanCheck check = new BooleanCheck();
        check.setSupplier(null);
        check.setExpected(true);

        assertThat(BooleanCheckers.isBoolean.test(check, ctx), is(true));
        verify(ctx).setNpe(true);
    }

    @Test
    public void testSupplierValueNull() {
        BooleanCheck check = new BooleanCheck();
        check.setSupplier(() -> null);
        check.setExpected(true);

        assertThat(BooleanCheckers.isBoolean.test(check, ctx), is(true));
        verify(ctx).setNpe(true);
    }

    @Test
    public void testExpectedNull() {
        BooleanCheck check = new BooleanCheck();
        check.setSupplier(() -> true);
        check.setExpected(null);

        assertThat(BooleanCheckers.isBoolean.test(check, ctx), is(true));
        verify(ctx).setNpe(true);
    }

    @Test
    public void testIsTrue_ExpectedFalse() {
        BooleanCheck check = new BooleanCheck();
        check.setSupplier(() -> true);
        check.setExpected(false);

        assertThat(BooleanCheckers.isBoolean.test(check, ctx), is(true));
        assertThat(BooleanCheckers.isBoolean.getExceptionMessage(check, ctx), is("Expected false, but was true"));
        verifyZeroInteractions(ctx);
    }

    @Test
    public void testIsFalse_ExpectedFalse() {
        BooleanCheck check = new BooleanCheck();
        check.setSupplier(() -> false);
        check.setExpected(false);

        assertThat(BooleanCheckers.isBoolean.test(check, ctx), is(false));
        verifyZeroInteractions(ctx);
    }

    @Test
    public void testIsFalse_ExpectedTrue() {
        BooleanCheck check = new BooleanCheck();
        check.setSupplier(() -> false);
        check.setExpected(true);

        assertThat(BooleanCheckers.isBoolean.test(check, ctx), is(true));
        assertThat(BooleanCheckers.isBoolean.getExceptionMessage(check, ctx), is("Expected true, but was false"));
        verifyZeroInteractions(ctx);
    }
}