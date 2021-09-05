package com.lvan.blackholeaop.strategy;

import lombok.Getter;
import org.aspectj.lang.JoinPoint;

/**
 * @author Lvan
 * @since 2021/9/5
 */
public abstract class AbstractAspectStrategy implements AspectHandleStrategy {

    @Getter
    private JoinPoint joinPoint;

    @Override
    public void setJoinPoint(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
    }
}
