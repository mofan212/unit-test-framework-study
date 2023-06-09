package indi.mofan.utils;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * @author mofan 2020/12/21
 */
public class CompareNumberMatcher<T extends Number> extends BaseMatcher<T> {

    private final T value;
    private final Compare<T> COMPARE;

    public CompareNumberMatcher(T value, boolean greater) {
        this.COMPARE = new DefaultNumberCompare<>(greater);
        this.value = value;
    }

    @Override
    public boolean matches(Object actual) {
        return this.COMPARE.compare(value, (T) actual);
    }

    public static <T extends Number> CompareNumberMatcher<T> gt(T value) {
        return new CompareNumberMatcher<>(value, true);
    }

    public static <T extends Number> CompareNumberMatcher<T> lt(T value) {
        return new CompareNumberMatcher<>(value, false);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compare two number failed.");
    }
}
