package indi.mofan.verify;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author mofan 2020/12/21
 */
public class VerifyTest {
    @Test
    public void testBasicVerify() {
        List<String> list = mock(ArrayList.class);

        list.add("mofan");
        list.clear();

        // 验证是否添加了 mofan 字符串
        verify(list).add("mofan");
        // 验证是否调用了一次 clear() 方法
        verify(list).clear();
        // 等价于
        verify(list, times(1)).clear();
    }

    @Test
    public void testVerifyExecutionTimes() {
        List<String> list = mock(ArrayList.class);

        list.add("once");

        list.add("twice");
        list.add("twice");

        list.add("third");
        list.add("third");
        list.add("third");

        // 验证执行 add("once") 一次
        verify(list).add("once");
        // 验证执行 add("two") 两次
        verify(list, times(2)).add("twice");
        // 验证执行 add("third") 三次
        verify(list, times(3)).add("third");

        // 验证没有执行 add("mofan")
        verify(list, never()).add("mofan");

        // atLeast / atMost
        // 最少执行一次, 不足报错
        verify(list, atLeastOnce()).add("third");
        // 最少执行两次, 不足报错
        verify(list, atLeast(2)).add("twice");
        // 最多执行五次, 超过报错
        verify(list, atMost(5)).add("third");
    }

    @Test
    public void testVerifySingleOrder() {
        List<String> list = mock(ArrayList.class);

        list.add("was added first");
        list.add("was added first");
        list.add("was added first");
        list.add("was added second");

        // 为 mock 对象创建一个 InOrder 对象
        InOrder inOrder = inOrder(list);

        // 验证执行顺序
        inOrder.verify(list, calls(2)).add("was added first");
        inOrder.verify(list).add("was added second");
    }

    @Test
    public void testVerifyMultipleOrder() {
        List<String> firstList = mock(ArrayList.class);
        List<String> secondList = mock(ArrayList.class);

        firstList.add("was called first");
        secondList.add("was called second");

        // 为两个 mock 对象创建 InOrder 对象
        InOrder inOrder = inOrder(firstList, secondList);
        // 验证执行顺序
        inOrder.verify(firstList).add("was called first");
        inOrder.verify(secondList).add("was called second");
    }

    @Test
    public void testVerifyInteraction() {
        List<String> firstList = mock(ArrayList.class);
        List<String> secondList = mock(ArrayList.class);
        List<String> thirdList = mock(ArrayList.class);

        firstList.add("mofan");
        secondList.add("one");
        secondList.add("two");

        // 验证 firstList 调用了 add(),进行一次交互
        verify(firstList).add("mofan");
        // 验证某个交互没有执行
        verify(secondList, never()).add("默烦");
        // 验证某些 mock 对象没有交互过
        verifyNoInteractions(thirdList);
    }

    @Test
    @Disabled
    public void testNoMoreInteraction_1() {
        List<String> list = mock(ArrayList.class);

        // 调用 add(), 但未进行验证
        list.add("mofan");

        // 下述验证将不会通过
        verifyNoMoreInteractions(list);
    }

    @Test
    public void testNoMoreInteraction_2() {
        List<String> list = mock(ArrayList.class);

        // 调用 add(), 也进行验证
        list.add("mofan");
        verify(list).add("mofan");

        // 下述验证将会通过
        verifyNoMoreInteractions(list);
    }

    @Test
    @Disabled
    public void testNoMoreInteraction_3() {
        List<String> list = mock(ArrayList.class);

        list.add("one");
        list.add("two");
        list.add("three");

        verify(list).add("one");
        verify(list).add("two");

        // 下述验证将不会通过
        verifyNoMoreInteractions(list);
    }

    @Test
    public void testIgnoreStubbing() {
        List<Integer> firstList = mock(ArrayList.class);
        List<Integer> secondList = mock(ArrayList.class);

        when(firstList.get(0)).thenReturn(10);
        when(secondList.get(0)).thenReturn(20);

        MatcherAssert.assertThat(firstList.get(0), CoreMatchers.equalTo(10));
        MatcherAssert.assertThat(secondList.get(0), CoreMatchers.equalTo(20));
        /*
         * 下面的测试不会通过因为没有对 Stubbing 进行验证
         * verifyNoMoreInteractions(firstList, secondList);
         */
        // 由于忽略了 firstList secondList，即使 get 方法没有 verify 也通过
        verifyNoMoreInteractions(ignoreStubs(firstList, secondList));
    }

    @Test
    public void testIgnoreInOrder() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(0)).thenReturn(100);
        list.add(0);
        list.clear();
        System.out.println(list.get(0));

        InOrder inOrder = inOrder(ignoreStubs(list));
        inOrder.verify(list).add(0);
        inOrder.verify(list).clear();
        inOrder.verifyNoMoreInteractions();
    }
}
