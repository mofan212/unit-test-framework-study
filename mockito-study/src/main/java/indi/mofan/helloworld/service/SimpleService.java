package indi.mofan.helloworld.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author mofan 2020/12/21
 */
public class SimpleService {

    public int method1(int i, String s, Collection<?> c, Serializable ser) {
        throw new RuntimeException();
    }

    public void method2(int i, String s, Collection<?> c, Serializable ser) {
        throw new RuntimeException();
    }
}
