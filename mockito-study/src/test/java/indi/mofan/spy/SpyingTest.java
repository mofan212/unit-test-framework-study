package indi.mofan.spy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mofan 2020/12/19
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = Mockito.spy(realList);

        list.add("mofan");
        list.add("默烦");

        Assert.assertEquals(list.get(0), "mofan");
        Assert.assertEquals(list.get(1), "默烦");
        Assert.assertEquals(list.size(), 2);

        Mockito.when(list.size()).thenReturn(100);
        Assert.assertTrue(list.size() != 2);
        Assert.assertEquals(list.size(), 100);
    }

    @Test
    public void testSpyAndDoReturn() {
        List<String> realList = new ArrayList<>();
        List<String> list = Mockito.spy(realList);

        // 下述代码会在编译器中直接报错
        // Mockito.when(list.get(0)).thenReturn(100);

        Mockito.doReturn("mofan").when(list).get(0);
        Assert.assertEquals(list.get(0), "mofan");
    }


    @Test
    @SuppressWarnings("unchecked")
    public void testDistinguishMockAndSpy() {
        List<String> realList = new ArrayList<>();
        List<String> spyList = Mockito.spy(realList);
        List<String> mockList = Mockito.mock(ArrayList.class);

        boolean isSpy = Mockito.mockingDetails(spyList).isSpy();
        boolean notMock = Mockito.mockingDetails(spyList).isMock();
        boolean isMock = Mockito.mockingDetails(mockList).isMock();
        boolean notSpy = Mockito.mockingDetails(mockList).isSpy();

        Assert.assertTrue(isSpy);
        Assert.assertTrue(notMock);
        Assert.assertTrue(isMock);
        Assert.assertFalse(notSpy);
    }
}
