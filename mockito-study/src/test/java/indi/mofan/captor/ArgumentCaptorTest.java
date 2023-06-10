package indi.mofan.captor;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author mofan 2020/12/22
 */
public class ArgumentCaptorTest {

    @Test
    @SuppressWarnings("unchecked, rawtypes")
    public void testCaptureArgument() {
        List<String> list = List.of("1", "2");
        List<String> mockedList = mock(ArrayList.class);
        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        mockedList.addAll(list);
        // 参数的捕获
        verify(mockedList).addAll(argument.capture());
        // 验证捕获的参数
        Assert.assertEquals(2, argument.getValue().size());
        Assert.assertEquals(list, argument.getValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        List<Integer> list = mock(ArrayList.class);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        list.add(1);
        int temp = ThreadLocalRandom.current().nextInt(1000);
        list.add(temp);
        // argument 只有 verify 之后才有值
        verify(list, times(2)).add(argument.capture());
        // getValue 是最后一次的参数值
        Assert.assertEquals(temp, argument.getValue().intValue());
        // getAllValues() 包含所有调用的参数值
        Assert.assertTrue(argument.getAllValues().contains(temp));
        Assert.assertTrue(argument.getAllValues().contains(1));
    }
}