package indi.mofan.captor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author mofan 2020/12/22
 */
@ExtendWith(MockitoExtension.class)
public class ArgumentCaptorAnnotationTest {

    @Captor
    ArgumentCaptor<List<String>> captor;

    @Test
    @SuppressWarnings("unchecked")
    public void testCaptureArgument() {
        List<String> list = List.of("1", "2");
        List<String> mockedList = mock(ArrayList.class);
        mockedList.addAll(list);
        // 参数的捕获
        verify(mockedList).addAll(captor.capture());
        // 验证捕获的参数
        Assertions.assertEquals(2, captor.getValue().size());
        Assertions.assertEquals(list, captor.getValue());
    }
}
