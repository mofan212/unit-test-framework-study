package indi.mofan.utils;

/**
 * @author mofan 2020/12/21
 */
public class DefaultNumberCompare<T extends Number> implements Compare<T> {

    private final boolean greater;

    public DefaultNumberCompare(boolean greater) {
        this.greater = greater;
    }

    @Override
    public boolean compare(T expected, T actual) {
        Class<?> clazz = actual.getClass();
        if (clazz == Integer.class) {
            return greater ? ((Integer) actual) > ((Integer) expected) : ((Integer) actual) < ((Integer) expected);
        } else if (clazz == Short.class) {
            return greater ? ((Short) actual) > ((Short) expected) : ((Short) actual) < ((Short) expected);
        } else if (clazz == Byte.class) {
            return greater ? ((Byte) actual) > ((Byte) expected) : ((Byte) actual) < ((Byte) expected);
        } else if (clazz == Double.class) {
            return greater ? ((Double) actual) > ((Double) expected) : ((Double) actual) < ((Double) expected);
        } else if (clazz == Float.class) {
            return greater ? ((Float) actual) > ((Float) expected) : ((Float) actual) < ((Float) expected);
        } else if (clazz == Long.class) {
            return greater ? ((Long) actual) > ((Long) expected) : ((Long) actual) < ((Long) expected);
        } else {
            throw new AssertionError("The number type" + clazz + "not supported");
        }
    }
}
