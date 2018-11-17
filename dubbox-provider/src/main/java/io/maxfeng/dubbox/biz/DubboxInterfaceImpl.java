package io.maxfeng.dubbox.biz;

import io.maxfeng.dubbox.annotation.Rpc;

/**
 * @author MaXueFeng
 * @since 1.0
 */
@Rpc
public class DubboxInterfaceImpl implements DubboxInterface {

    public void producer() {
        for (int i = 0; i < 10; i++) {
            System.out.println("I Am Producer Please see your console");
        }
    }
}
