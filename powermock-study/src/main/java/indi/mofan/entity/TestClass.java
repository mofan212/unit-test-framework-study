package indi.mofan.entity;

/**
 * @author mofan
 * @date 2021/4/12 15:13
 */
public class TestClass {
    private static class InnerTestClass {
        private String name;

        public InnerTestClass(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void run() {
            System.out.println("执行了run...");
            throw new UnsupportedOperationException();
        }
    }
}
