package indi.mofan.spy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mofan 2020/12/19
 */
public class SpyAnnotationTest {
    @Spy
    List<String> list = new ArrayList<>();

    private AutoCloseable closeable;

    @Before
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void destroy() throws Exception {
        closeable.close();
    }
    
    @Test
    public void testSpyByAnnotation() {
        list.add("one");
        list.add("two");

        Assert.assertEquals(list.get(0), "one");
        Assert.assertEquals(list.get(1), "two");

        Mockito.when(list.size()).thenReturn(100);
        Assert.assertEquals(list.size(), 100);
    }
}
