package io.maxfeng.dubbox.biz;


import java.lang.reflect.Proxy;


/**
 * @author MaXueFeng
 * @since 1.0
 */
public class ProxyFactory {

    public static Object newInstance(Object ob) {
        return Proxy.newProxyInstance(ob.getClass().getClassLoader(),
                new Class<?>[]{Task.class}, new MyInvocationHandler(ob));
    }
}
