package io.maxfeng.dubbox.biz;

import io.maxfeng.dubbox.annotation.Rpc;

import java.io.Serializable;

/**
 * @author MaXueFeng
 * @since 1.0
 */
@Rpc
public class TestInterfaceImpl implements TestInterface, Serializable {
    @Override
    public void producer() {

    }
}
