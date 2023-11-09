package indi.mofan.verify;

import indi.mofan.helloworld.service.SimpleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Serializable;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author mofan 2020/12/21
 */
@ExtendWith(MockitoExtension.class)
public class VerifyArgumentMatcherTest {
    @Mock
    private SimpleService simpleService;

    @AfterEach
    public void destroy() {
        Mockito.reset(simpleService);
    }

    @Test
    public void testVerifyArgumentMatcher() {
        when(simpleService.method1(anyInt(), anyString(),
                anyCollection(), isA(Serializable.class))).thenReturn(100);

        simpleService.method1(666, "mofan", Collections.emptyList(), "默烦");
        // 别忘记如果使用参数匹配器,所有参数都必须由匹配器提供。
        verify(simpleService).method1(anyInt(), anyString(), anyCollection(), eq("默烦"));
    }
}
