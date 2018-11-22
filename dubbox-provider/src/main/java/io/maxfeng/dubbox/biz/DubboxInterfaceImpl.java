package io.maxfeng.dubbox.biz;

import io.maxfeng.dubbox.annotation.Provider;

import java.io.Serializable;

/**
 * @author MaXueFeng
 * @since 1.0
 */
@Provider(alias = "dubboxInterfaceImpl1",group = "group1")
public class DubboxInterfaceImpl implements DubboxInterface, Serializable {

    public void producer() {
        for (int i = 0; i < 10; i++) {
            System.out.println("I Am Producer Please see your console");
        }
    }
}
