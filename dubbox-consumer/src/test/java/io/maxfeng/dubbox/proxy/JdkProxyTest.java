package io.maxfeng.dubbox.proxy;

import io.maxfeng.dubbox.biz.DubboxInterface;
import org.junit.Test;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class JdkProxyTest {

    //TODO NOT PASS This is make cpu 100%  why?
//    @Test
//    public void testProxyObjectInvokeMethod() {
//
//        DubboxInterface subject = new DubboxInterfaceImpl();
//
//        JdkProxy handler = new JdkProxy();
//
//        ClassLoader loader = subject.getClass().getClassLoader();
//
//        Class<?>[] interfaces = subject.getClass().getInterfaces();
//
//        DubboxInterface var = (DubboxInterface) Proxy.newProxyInstance(loader, interfaces, handler);
//
//        var.producer();
//    }


    // JDK Simple Dynamic Proxy
//    @Test
//    public void testProxyObjectInvokeMethod() {
//
//        DubboxInterface subject = new DubboxInterfaceImpl();
//
//        JdkProxy handler = new JdkProxy(subject);
//
//        DubboxInterface var = (DubboxInterface) handler.getProxy();
//
//        if (var instanceof DubboxInterfaceImpl) {
//            System.out.println("I am DubboxInterfaceImpl variable");
//        }
//        String producer = var.producer();
//
//        System.out.println(producer);
//
//    }

    //No Pass
    @Test
    public void testProxyObjectInvokeMethod1() {
        Class<?>[] interfaces = DubboxInterface.class.getClass().getInterfaces();

    }
}
