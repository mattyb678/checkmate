package org.mattyb.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Check implements IntRangeCheck {

    private static final Logger log = LoggerFactory.getLogger(Check.class);
    private final List<CheckPair<?>> checks;

    private Check() {
        checks = new ArrayList<>();
    }

    public static Check check() {
        return new Check();
    }

    @SuppressWarnings("unchecked")
    public void verify() {
        for (CheckPair<?> check : checks) {
            if (check.getChecker().test(check.getToCheck())) {
                final String message = check.getMessage() == null ?
                        check.getChecker().getExceptionMessage(check.getToCheck()) :
                        check.getMessage();
                throw Objects.requireNonNull(create(check.getThrowableClass(), message));
            }
        }
    }

    public Check withException(final Class<? extends RuntimeException> throwableClass) {
        checks.get(checks.size() - 1).setThrowableClass(throwableClass);
        return this;
    }

    public Check withMessage(final String message, final Object... values) {
        final String formatted = String.format(message, values);
        checks.get(checks.size() - 1).setMessage(formatted);
        return  this;
    }

    public <T> Check notNull(final T object) {
        checks.add(new CheckPair<>(object, Checkers.notNull));
        return this;
    }

    public <T extends Collection<?>> Check notEmpty(final T collection) {
        checks.add(new CheckPair<>(collection, Checkers.notEmptyCollection));
        return this;
    }

    @Override
    public IntRangeCheck value(Comparable<Integer> value) {
        final Range<Integer> range = new Range<>();
        range.setValue(value);
        checks.add(new CheckPair<>(range, Checkers.intOutOfRange));
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IntRangeCheck between(Integer start) {
        ((Range<Integer>) checks.get(checks.size() - 1).getToCheck()).setStart(start);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Check and(Integer endExclusive) {
        ((Range<Integer>) checks.get(checks.size() - 1).getToCheck()).setEnd(endExclusive);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Check inclusive() {
        final Range<Integer> range = ((Range<Integer>) checks.get(checks.size() - 1).getToCheck());
        range.setInclusive(true);
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
