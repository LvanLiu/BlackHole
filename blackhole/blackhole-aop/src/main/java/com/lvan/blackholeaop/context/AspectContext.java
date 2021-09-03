package com.lvan.blackholeaop.context;

/**
 * 此接口主要提供切面相关数据获取的相关服务接口。
 *
 * @author lvan
 * @date 2021/9/3
 */
public interface AspectContext {

    /**
     * the arguments at this join point
     */
    Object[] acquireArgs();
}
