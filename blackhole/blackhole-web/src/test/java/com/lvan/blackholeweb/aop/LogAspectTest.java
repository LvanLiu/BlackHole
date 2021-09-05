package com.lvan.blackholeweb.aop;

import com.lvan.blackholeweb.log.LogAop;
import com.lvan.blackholeweb.log.aspect.LogAspect;
import com.lvan.blackholeweb.log.aspect.LogRecordStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Lvan
 * @since 2021/9/5
 */
@ExtendWith({MockitoExtension.class})
public class LogAspectTest {

    @Spy
    private LogRecordStrategy logRecordStrategy;
    private UserService userService;

    @BeforeEach
    void setup() {

        LogAspect logAspect = new LogAspect(logRecordStrategy);

        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(new UserService());
        aspectJProxyFactory.addAspect(logAspect);

        userService = aspectJProxyFactory.getProxy();
    }

    @Test
    void recordLogBeforeMethod_whenNoneOfExceptionThrows_thenRecordLogBeforeAndAfterReturning() {

        userService.getUserName("test");

        verify(logRecordStrategy, times(1)).before();
        verify(logRecordStrategy, times(1)).afterReturning(any());
    }

    @Test
    void recordLogBeforeMethod_whenThrowsException_thenRecordLogBeforeAndAfterThrows() {

        assertThrows(IllegalStateException.class, () -> {
            userService.getUserPhone();
        });

        verify(logRecordStrategy, times(1)).before();
        verify(logRecordStrategy, times(1)).afterExceptionThrow(any());
    }

    public static class UserService {

        @LogAop
        public String getUserName(String userId) {
            return "lvan";
        }

        @LogAop
        public void getUserPhone() {
            throw new IllegalStateException();
        }
    }
}
