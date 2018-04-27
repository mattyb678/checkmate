package xyz.mattyb.checkmate;

import xyz.mattyb.checkmate.checker.Checkers;
import xyz.mattyb.checkmate.checker.NotEmptyCheckers;
import xyz.mattyb.checkmate.checker.NumberCheckers;
import xyz.mattyb.checkmate.checkmate.IntRangeCheckMate;
import xyz.mattyb.checkmate.checkmate.LongRangeCheckMate;
import xyz.mattyb.checkmate.checkmate.NotEmptyCheckMate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CheckMate implements NotEmptyCheckMate, IntRangeCheckMate, LongRangeCheckMate {

    private static final Logger log = LoggerFactory.getLogger(CheckMate.class);
    private final List<Check<?>> checks;

    private CheckMate() {
        checks = new ArrayList<>();
    }

    public static CheckMate check() {
        return new CheckMate();
    }

    @SuppressWarnings("unchecked")
    public void validate() {
        for (Check<?> check : checks) {
            if (check.getChecker().test(check.getToCheck())) {
                final String message = check.getMessage() == null ?
                        check.getChecker().getExceptionMessage(check.getToCheck()) :
                        check.getMessage();
                throw Objects.requireNonNull(create(check.getThrowableClass(), message));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public boolean anyInvalid() {
        return checks.stream().anyMatch(check -> check.getChecker().test(check.getToCheck()));
    }

    @SuppressWarnings("unchecked")
    public boolean allInvalid() {
        return checks.stream().allMatch(check -> check.getChecker().test(check.getToCheck()));
    }

    public CheckMate withException(final Class<? extends RuntimeException> throwableClass) {
        checks.get(checks.size() - 1).setThrowableClass(throwableClass);
        return this;
    }

    public CheckMate withMessage(final String message, final Object... values) {
        final String formatted = String.format(message, values);
        checks.get(checks.size() - 1).setMessage(formatted);
        return  this;
    }

    public <T> CheckMate notNull(final T object) {
        checks.add(new Check<>(object, Checkers.notNull));
        return this;
    }

    @Override
    public <T extends Collection<?>> CheckMate notEmpty(final T collection) {
        checks.add(new Check<>(collection, NotEmptyCheckers.collection));
        return this;
    }

    @Override
    public <T extends CharSequence> CheckMate notEmpty(T chars) {
        checks.add(new Check<>(chars, NotEmptyCheckers.charSequence));
        return this;
    }

    @Override
    public <T> CheckMate notEmpty(T[] array) {
        checks.add(new Check<>(array, NotEmptyCheckers.array));
        return this;
    }

    @Override
    public <T extends Map<?, ?>> CheckMate notEmpty(T map) {
        checks.add(new Check<>(map, NotEmptyCheckers.map));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntRangeCheckMate intValue(Comparable<Integer> value) {
        final Range<Integer> range = new Range<>();
        range.setValue(value);
        checks.add(new Check<>(range, NumberCheckers.intOutOfRange));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public IntRangeCheckMate between(Integer start) {
        ((Range<Integer>) checks.get(checks.size() - 1).getToCheck()).setStart(start);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public CheckMate and(Integer endExclusive) {
        ((Range<Integer>) checks.get(checks.size() - 1).getToCheck()).setEnd(endExclusive);
        return this;
    }

    @Override
    public LongRangeCheckMate longValue(Comparable<Long> value) {
        final Range<Long> range = new Range<>();
        range.setValue(value);
        checks.add(new Check<>(range, NumberCheckers.longOutOfRange));
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LongRangeCheckMate between(Long start) {
        ((Range<Long>) checks.get(checks.size() - 1).getToCheck()).setStart(start);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CheckMate and(Long endExclusive) {
        ((Range<Long>) checks.get(checks.size() - 1).getToCheck()).setEnd(endExclusive);
        return this;
    }

    public CheckMate inclusive() {
        final Object object = checks.get(checks.size() - 1).getToCheck();
        if (!(object instanceof Range)) {
            log.error("Object {} is not a Range", object);
            return this;
        }
        ((Range) object).setInclusive(true);
        return this;
    }

    private RuntimeException create(final Class<? extends RuntimeException> throwableClass,
                                    final String message) {
        try {
            return throwableClass.getConstructor(String.class).newInstance(message);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
                | InstantiationException e) {
            log.error("Error instantiating exception", e);
        }
        return null;
    }
}
