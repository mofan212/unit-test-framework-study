package indi.mofan.utils;

/**
 * @author mofan 2020/12/21
 */
public interface Compare<T extends Number> {
    boolean compare(T expected, T actual);
}
