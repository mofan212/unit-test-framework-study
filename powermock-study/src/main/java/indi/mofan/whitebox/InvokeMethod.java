package indi.mofan.whitebox;

/**
 * @author mofan
 * @date 2021/4/12 18:07
 */
public class InvokeMethod {

    private int sum(int a, int b) {
        return a + b;
    }

    private int method(int a) {
        return 2 * a;
    }

    private int method(Integer a) {
        return 3 * a;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private static int multiplyMethod(int a, int b) {
        return a * b;
    }
}
