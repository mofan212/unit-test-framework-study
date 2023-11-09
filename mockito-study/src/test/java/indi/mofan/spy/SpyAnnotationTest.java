package indi.mofan.spy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void destroy() throws Exception {
        closeable.close();
    }
    
    @Test
    public void testSpyByAnnotation() {
        list.add("one");
        list.add("two");

        Assertions.assertEquals(list.get(0), "one");
        Assertions.assertEquals(list.get(1), "two");

        Mockito.when(list.size()).thenReturn(100);
        Assertions.assertEquals(list.size(), 100);
    }
}
