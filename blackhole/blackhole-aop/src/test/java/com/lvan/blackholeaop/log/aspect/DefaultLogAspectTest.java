package com.lvan.blackholeaop.log.aspect;

import com.lvan.blackholeaop.log.LogAop;
import com.lvan.blackholeaop.log.support.DefaultLogRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author lvan
 * @date 2021/9/1
 */
@ExtendWith({MockitoExtension.class})
class DefaultLogAspectTest {

    @Mock
    private DefaultLogRecord logRecord;
    private UserService userService;

    @BeforeEach
    void setUp() {

        doNothing().when(logRecord).recordBeforeAdvice(any());

        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(new UserService());
        DefaultLogAspect logAspect = new DefaultLogAspect(logRecord);
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

    @Test
    void recordLogAroundMethod_whenThrowsException_thenRecordBeforeAndAfterException() {

        doNothing().when(logRecord).recordAfterThrowAdvice(any(), any());

        assertThrows(IllegalStateException.class, () -> {
            userService.getUserPhone();
        });

        verify(logRecord, times(1)).recordBeforeAdvice(any());
        verify(logRecord, times(1)).recordAfterThrowAdvice(any(), any());
    }

    public static class UserService {

        @LogAop
        public void getUserName() {
        }

        @LogAop
        public void getUserPhone() {
            throw new IllegalStateException();
        }
    }
}