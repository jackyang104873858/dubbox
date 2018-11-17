package io.maxfeng.dubbox.biz;

import io.maxfeng.dubbox.annotation.RpcInvoke;
import io.maxfeng.dubbox.proxy.JdkInvocationProxy;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class DubboxDependOnInterface {

    @RpcInvoke
    private DubboxInterface dubboxInterface;


    public void print() {
        Object result = JdkInvocationProxy.getProxy(DubboxInterface.class);
        System.out.println(result);
    }


}
