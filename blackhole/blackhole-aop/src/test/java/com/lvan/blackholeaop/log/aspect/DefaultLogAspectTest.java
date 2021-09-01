package com.lvan.blackholeaop.log.aspect;

import com.lvan.blackholeaop.log.LogAop;
import com.lvan.blackholeaop.log.support.DefaultLogRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

        doNothing().when(logRecord).recordBeforeAdvice(any(LogAspectContext.class));

        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(new UserService());
        DefaultLogAspect logAspect = new DefaultLogAspect(logRecord);
        aspectJProxyFactory.addAspect(logAspect);

        userService = aspectJProxyFactory.getProxy();
    }

    @Test
    void testDefaultLogAspect() {

        String userName = userService.getUserName(1);

        assertThat(userName).isNotEmpty();
        verify(logRecord, times(1)).recordBeforeAdvice(any(LogAspectContext.class));
    }

    public static class UserService {

        @LogAop
        public String getUserName(Integer userId) {
            return "lvan";
        }
    }
}