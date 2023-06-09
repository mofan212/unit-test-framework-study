package indi.mofan.helloworld.service;

/**
 * @author mofan 2020/12/18
 */
public class StubbingService {
    public int getI() {
        System.out.println("==== getI ====");
        return 10;
    }

    public String getS() {
        System.out.println("==== getS ====");
        throw new RuntimeException();
    }
}
