package indi.mofan.stubbing;

import indi.mofan.helloworld.service.StubbingService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

/**
 * @author mofan 2020/12/18
 */
@ExtendWith(MockitoExtension.class)
public class StubbingTest {
    private ArrayList<String> list;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void init() {
        this.list = Mockito.mock(ArrayList.class);
    }

    @AfterEach
    @SuppressWarnings("unchecked")
    public void destroy() {
        // 重置 Stubbing
        Mockito.reset(list);
    }

    @Test
    public void howToUseStubbing() {
        Mockito.when(list.get(0)).thenReturn("first");
        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo("first"));

        Mockito.when(list.get(Mockito.anyInt())).thenThrow(new RuntimeException());

        try {
            String s = list.get(0);
            Assertions.fail();
        } catch (Exception e) {
            // 断言抛出异常
            MatcherAssert.assertThat(e, CoreMatchers.instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void howToStubbingVoidMethod() {
        // 测试执行一次返回值类型是 void 的方法
        Mockito.doNothing().when(list).clear();
        list.clear();
        Mockito.verify(list, Mockito.times(1)).clear();

        // 测试执行返回值类型是 void 的方法抛出异常
        Mockito.doThrow(RuntimeException.class).when(list).clear();
        try {
            list.clear();
            Assertions.fail();
        } catch (Exception e) {
            MatcherAssert.assertThat(e, CoreMatchers.instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void testStubbingDoReturn() {
        Mockito.when(list.get(0)).thenReturn("first");
        Mockito.doReturn("second").when(list).get(1);

        MatcherAssert.assertThat(list.get(0), CoreMatchers.equalTo("first"));
        Assertions.assertEquals(list.get(1), "second");
    }

    @Test
    public void testIterateStubbing() {

        /*
         * 效果与这种一样:
         * Mockito.when(list.size()).thenReturn(1, 2, 3, 4);
         */
        Mockito.when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);

        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.size(), 2);
        Assertions.assertEquals(list.size(), 3);
        Assertions.assertEquals(list.size(), 4);
        // 第五次调用结果还是 4
        Assertions.assertEquals(list.size(), 4);
    }

    @Test
    public void testStubbingWithAnswer() {
        Mockito.when(list.get(Mockito.anyInt())).thenAnswer(invocation -> {
            // 指定 get() 方法的第一个参数是 Integer 类型，名为 index
            Integer index = invocation.getArgument(0, Integer.class);
            return String.valueOf(index * 10);
        });

        for (int i = 0; i < 10; i++) {
            int num = (int)(Math.random() * 100) + 1;
            Assertions.assertEquals(list.get(num), String.valueOf(num * 10));
        }
    }

    @Test
    public void testStubbingWithRealCall() {
        StubbingService service = Mockito.mock(StubbingService.class);

        Mockito.when(service.getS()).thenReturn("mofan");
        Assertions.assertEquals(service.getS(), "mofan");

        Mockito.when(service.getI()).thenCallRealMethod();
        Assertions.assertEquals(service.getI(), 10);
    }

}
