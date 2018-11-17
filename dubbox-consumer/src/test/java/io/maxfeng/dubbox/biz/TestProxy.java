package io.maxfeng.dubbox.biz;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class TestProxy {

    public static void main(String[] args) {
        Object proxy = RPCProxyClient.getProxy(HelloWorldService.class);
        System.out.println(proxy);
    }
}
