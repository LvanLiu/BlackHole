package com.lvan.blackholeweb.aop;

import com.lvan.blackholeaop.log.support.DefaultLogRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author lvan
 * @date 2021/9/3
 */
@ExtendWith({MockitoExtension.class})
class WebLogAsepectTest {

    @Mock
    private DefaultLogRecord logRecord;
    private UserService userService;

    @BeforeEach
    void setUp() {

        doNothing().when(logRecord).recordBeforeAdvice(any());

        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(new UserService());
        WebLogAspect logAspect = new WebLogAspect(logRecord);
        aspectJProxyFactory.addAspect(logAspect);

        userService = aspectJProxyFactory.getProxy();
    }

    @Test
    void recordLogAroundMethod_whenNoneOfExceptionThrows_thenRecordBeforeAndAfterReturn() {

        doNothing().when(logRecord).recordAfterReturnAdvice(any(), any());

        userService.getUserName();

        verify(logRecord, times(1)).recordBeforeAdvice(any());
        verify(logRecord, times(1)).recordAfterReturnAdvice(any(), any());
    }

    public static class UserService {

        @WebLogAop
        public void getUserName() {
        }
    }
}
