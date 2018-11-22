package io.maxfeng.dubbox.biz;

import io.maxfeng.dubbox.annotation.Provider;

import java.io.Serializable;

/**
 * @author MaXueFeng
 * @since 1.0
 */
@Provider
public class TestInterfaceImpl implements TestInterface, Serializable {
    @Override
    public void producer() {

    }
}
