package io.maxfeng.dubbox.proxy;

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
//        JdkInvocationProxy handler = new JdkInvocationProxy();
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
//        JdkInvocationProxy handler = new JdkInvocationProxy(subject);
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
//    @Test
//    public void testProxyObjectInvokeMethod1() {
//        DubboxInterface proxy = (DubboxInterface)
//                new JdkInvocationProxy(DubboxInterface.class).getProxy();
//        String r = proxy.producer();
//
//        System.out.println(r);
//    }
}
