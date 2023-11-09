package indi.mofan.matchers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mofan 2020/12/21
 */
public class ArgumentMatcherTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testBasic() {
        List<String> list = Mockito.mock(ArrayList.class);
        Mockito.when(list.get(0)).thenReturn("mofan");
        // 也能这么写
        Mockito.when(list.get(Mockito.eq(1))).thenReturn("默烦");

        Assertions.assertEquals(list.get(0), "mofan");
        Assertions.assertEquals(list.get(1), "默烦");
        Assertions.assertNull(list.get(2));
        // 还可以验证一下
        Mockito.verify(list).get(0);
        // 放开下面这段代码，测试不会通过
        // Mockito.verify(list).get(0);
    }

    /* isA, any */
    @Test
    public void testIsA_1() {
        Foo foo = Mockito.mock(Foo.class);
        Mockito.when(foo.function(Mockito.isA(Parent.class)))
                .thenReturn(100);
        Mockito.when(foo.function(Mockito.isA(Child1.class)))
                .thenReturn(200);
        int result_1 = foo.function(new Child1());
        int result_2 = foo.function(new Child2());

        Assertions.assertEquals(result_1, 200);
        Assertions.assertEquals(result_2, 100);
    }

    @Test
    public void testIsA_2() {
        Foo foo = Mockito.mock(Foo.class);
        Mockito.when(foo.function(Mockito.isA(Child1.class)))
                .thenReturn(200);

        int result_1 = foo.function(new Child1());
        int result_2 = foo.function(new Child2());

        Assertions.assertEquals(result_1, 200);
        // 没有指定 Child2, 因此返回 int 类型的默认值
        Assertions.assertEquals(result_2, 0);
    }

    @Test
    public void testAny() {
        Foo foo = Mockito.mock(Foo.class);
        Mockito.when(foo.function(Mockito.any(Child1.class)))
                .thenReturn(100);

        Assertions.assertEquals(foo.function(new Child1()), 100);
        Assertions.assertNotEquals(foo.function(new Child2()), 100);
    }

    static class Foo {
        int function(Parent p) {
            return p.work();
        }
    }

    interface Parent {
        int work();
    }

    static class Child1 implements Parent {

        @Override
        public int work() {
           throw new RuntimeException();
        }
    }

    static class Child2 implements Parent {

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
}
