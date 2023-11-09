package indi.mofan.matchers;

import indi.mofan.helloworld.service.SimpleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author mofan 2020/12/21
 */
@ExtendWith(MockitoExtension.class)
public class WildcardArgumentMatcherTest {
    @Mock
    private SimpleService simpleService;

    @AfterEach
    public void destroy() {
        Mockito.reset(simpleService);
    }

    /**
     * 有返回值的方法与通配符匹配器
     */
    @Test
    public void testWildcardMethod1() {
        when(simpleService.method1(anyInt(), anyString(),
                anyCollection(), Mockito.isA(Serializable.class))).thenReturn(100);
        int result_1 = simpleService.method1(666, "mofan", Collections.emptyList(), "默烦");
        Assertions.assertEquals(result_1, 100);
        int result_2 = simpleService.method1(888, "默烦", Collections.emptySet(), "mofan");
        Assertions.assertEquals(result_2, 100);
    }

    @Test
    public void testWildcardMethod1WithSpec() {
        /*
         * 注意 Stubbing 的顺序
         * 如果将第一句 Stubbing 移动到第三句，那么就会报错
         */
        when(simpleService.method1(anyInt(), anyString(),
                anyCollection(), Mockito.isA(Serializable.class))).thenReturn(-1);
        when(simpleService.method1(anyInt(), eq("mofan"),
                anyCollection(), Mockito.isA(Serializable.class))).thenReturn(100);
        when(simpleService.method1(anyInt(), eq("默烦"),
                anyCollection(), Mockito.isA(Serializable.class))).thenReturn(200);

        int result_1 = simpleService.method1(111, "mofan", Collections.emptyList(), "mofan");
        int result_2 = simpleService.method1(111, "默烦", Collections.emptyList(), "默烦");
        int result_3 = simpleService.method1(111, "qwer", Collections.emptyList(), "默烦");

        Assertions.assertEquals(result_1, 100);
        Assertions.assertEquals(result_2, 200);
        Assertions.assertEquals(result_3, -1);
    }

    /**
     * 无返回值的方法与通配符匹配器
     */
    @Test
    public void testWildcardMethod2() {
        // 使 Collections.emptyList() 是同一个实例
        List<Object> emptyList = Collections.emptyList();
        Mockito.doNothing().when(simpleService).method2(anyInt(), anyString(),
                anyCollection(), Mockito.isA(Serializable.class));

        simpleService.method2(666, "mofan", emptyList, "默烦");

        Mockito.verify(simpleService, Mockito.times(1))
                .method2(666, "mofan", emptyList, "默烦");
        Mockito.verify(simpleService, Mockito.times(1))
                .method2(anyInt(), eq("mofan"), anyCollection(),
                        Mockito.isA(Serializable.class));
    }
}
