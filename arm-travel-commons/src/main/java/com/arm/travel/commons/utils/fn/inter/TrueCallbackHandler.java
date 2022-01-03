package com.arm.travel.commons.utils.fn.inter;

/**
 * 分支处理接口
 **/
@FunctionalInterface
public interface TrueCallbackHandler<T> {

    /**
     * 分支操作
     *
     * @return void
     **/
    T handler();
}