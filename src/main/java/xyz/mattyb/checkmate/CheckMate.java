package xyz.mattyb.checkmate;

import xyz.mattyb.checkmate.checker.*;
import xyz.mattyb.checkmate.checker.context.CheckerContext;
import xyz.mattyb.checkmate.checker.context.DefaultCheckerContext;
import xyz.mattyb.checkmate.checker.context.NoOpCheckerContext;
import xyz.mattyb.checkmate.checkmate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;

public class CheckMate implements NotEmptyCheckMate, IntRangeCheckMate, LongRangeCheckMate, DoubleRangeCheckMate,
        IndexCheckMate, BooleanCheckMate, InstanceCheckMate {

    private static final Logger log = LoggerFactory.getLogger(CheckMate.class);
    private static final CheckerContext NO_OP = new NoOpCheckerContext();
    private final List<Check<?>> checks;
    private final Class<? extends RuntimeException> defaultException;

    private CheckMate(final Class<? extends RuntimeException> defaultException) {
        checks = new ArrayList<>();
        this.defaultException = defaultException;
    }

    public static CheckMate check() {
        return new CheckMate(IllegalArgumentException.class);
    }

    public static CheckMate checkWithDefault(final Class<? extends RuntimeException> defaultException) {
        return new CheckMate(defaultException);
    }

    @SuppressWarnings("unchecked")
    public void validate() {
        for (Check<?> check : checks) {
            final CheckerContext ctx = new DefaultCheckerContext();
            if (check.getChecker().test(check.getToCheck(), ctx)) {
                final String message = check.getMessage() == null ?
                        check.getChecker().getExceptionMessage(check.getToCheck(), ctx) :
                        check.getMessage();
                throw Objects.requireNonNull(create(check.getThrowableClass(), message));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public boolean anyInvalid() {
        return checks.stream().anyMatch(check -> check.getChecker().test(check.getToCheck(), NO_OP));
    }

    @SuppressWarnings("unchecked")
    public boolean allInvalid() {
        return checks.stream().allMatch(check -> check.getChecker().test(check.getToCheck(), NO_OP));
    }

    public CheckMate withException(final Class<? extends RuntimeException> throwableClass) {
        last().setThrowableClass(throwableClass);
        return this;
    }

    public CheckMate withMessage(final String message, final Object... values) {
        final String formatted = String.format(message, values);
        last().setMessage(formatted);
        return  this;
    }

    public <T> CheckMate notNull(final T object) {
        checks.add(new Check<>(object, NullCheckers.notNull));
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
    public <T extends CharSequence> CheckMate notBlank(T chars) {
        checks.add(new Check<>(chars, NotEmptyCheckers.charSequenceNotBlank));
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

    public <T> CheckMate noNullElements(T[] array) {
        checks.add(new Check<>(array, NullCheckers.noNullElementsArray));
        return this;
    }

    public <T extends Iterable<?>> CheckMate noNullElements(T collection) {
        checks.add(new Check<>(collection, NullCheckers.noNullElementsIterable));
        return this;
    }

    @Override
    public BooleanCheckMate is(boolean expression) {
        BooleanCheck booleanCheck = new BooleanCheck();
        booleanCheck.setSupplier(() -> expression);
        checks.add(new Check<>(booleanCheck, BooleanCheckers.isBoolean));
        return this;
    }

    @Override
    public BooleanCheckMate is(Supplier<Boolean> supplier) {
        BooleanCheck booleanCheck = new BooleanCheck();
        booleanCheck.setSupplier(supplier);
        checks.add(new Check<>(booleanCheck, BooleanCheckers.isBoolean));
        return this;
    }

    @Override
    public CheckMate truthy() {
        ((BooleanCheck) last().getToCheck()).setExpected(true);
        return this;
    }

    @Override
    public CheckMate falsy() {
        ((BooleanCheck) last().getToCheck()).setExpected(false);
        return this;
    }

    public IndexCheckMate isIndex(final int index) {
        final Index checkIndex = new Index();
        checkIndex.setIndex(index);
        checks.add(new Check<>(checkIndex, IndexCheckers.indexChecker));
        return this;
    }

    @Override
    public <T> CheckMate validIn(T[] array) {
        ((Index) last().getToCheck()).calcSize(array);
        return this;
    }

    @Override
    public <T extends Collection<?>> CheckMate validIn(T collection) {
        ((Index) last().getToCheck()).calcSize(collection);
        return this;
    }

    @Override
    public <T extends CharSequence> CheckMate validIn(T chars) {
        ((Index) last().getToCheck()).calcSize(chars);
        return this;
    }

    @Override
    public <T> InstanceCheckMate object(T obj) {
        InstanceCheck check = new InstanceCheck(obj);
        checks.add(new Check<>(check, TypeCheckers.instanceOf));
        return this;
    }

    @Override
    public CheckMate isInstanceOf(Class<?> clazz) {
        ((InstanceCheck) last().getToCheck()).setClazz(clazz);
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
        ((Range<Integer>) last().getToCheck()).setStart(start);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public CheckMate and(Integer endExclusive) {
        ((Range<Integer>) last().getToCheck()).setEnd(endExclusive);
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
        ((Range<Long>) last().getToCheck()).setStart(start);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CheckMate and(Long endExclusive) {
        ((Range<Long>) last().getToCheck()).setEnd(endExclusive);
        return this;
    }

    @Override
    public DoubleRangeCheckMate doubleValue(Comparable<Double> value) {
        final Range<Double> range = new Range<>();
        range.setValue(value);
        checks.add(new Check<>(range, NumberCheckers.doubleOutOfRange));
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DoubleRangeCheckMate between(Double start) {
        ((Range<Double>) last().getToCheck()).setStart(start);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CheckMate and(Double endExclusive) {
        ((Range<Double>) last().getToCheck()).setEnd(endExclusive);
        return this;
    }

    public CheckMate inclusive() {
        final Object object = last().getToCheck();
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
            if (throwableClass == null) {
                return defaultException.getConstructor(String.class).newInstance(message);
            } else {
                return throwableClass.getConstructor(String.class).newInstance(message);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
                | InstantiationException e) {
            log.error("Error instantiating exception", e);
        }
        return null;
    }

    private Check<?> last() {
        return checks.get(checks.size() - 1);
    }
}
