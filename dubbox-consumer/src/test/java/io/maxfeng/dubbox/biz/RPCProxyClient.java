package io.maxfeng.dubbox.biz;

import java.lang.reflect.Method;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class RPCProxyClient implements java.lang.reflect.InvocationHandler {

    private Object obj;

    public RPCProxyClient(Object obj) {
        this.obj = obj;
    }

    /**
     * 得到被代理对象;
     */
    public static Object getProxy(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new RPCProxyClient(obj));
    }

    /**
     * 调用此方法执行
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        //结果参数;
        Object result = new Object();
        // ...执行通信相关逻辑
        // ...
        return "hello world";
    }
}
