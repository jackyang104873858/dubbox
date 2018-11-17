package io.maxfeng.dubbox.common;

import io.maxfeng.dubbox.biz.DubboxInterface;
import io.maxfeng.dubbox.biz.DubboxInterfaceImpl;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class InvocationTest {
    @Test
    public void testClassCurrentPackage() {

        System.out.println(InvocationTest.class.getPackage().getName());

        Package p = InvocationTest.class.getPackage();
//        p.
        Annotation[] annotations = p.getAnnotations();

        System.out.println(annotations.length);
    }

    @Test
    public void testInvocation() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<DubboxInterfaceImpl> aClass = DubboxInterfaceImpl.class;

        Class<? super DubboxInterfaceImpl> superclass = aClass.getSuperclass();

        String name = superclass.getName();

        System.out.println(name);


        Method method = aClass.getMethod("producer");

        method.invoke(aClass.newInstance());


        Class<DubboxInterface> zClass = DubboxInterface.class;

        Class<? extends DubboxInterfaceImpl> aClass1 = zClass.asSubclass(DubboxInterfaceImpl.class);

        DubboxInterfaceImpl instance = aClass1.newInstance();

        instance.producer();
    }

    @Test
    public void testSuperClass() {

        Class<DubboxInterfaceImpl> aClass = DubboxInterfaceImpl.class;

        Class<? super DubboxInterfaceImpl> superclass = aClass.getSuperclass();

        System.out.println(superclass.getName());

        Type[] interfaces = aClass.getGenericInterfaces();

        for (Type anInterface : interfaces) {

            String typeName = anInterface.getTypeName();
            System.out.println(typeName);
        }
    }

    @Test
    public void testSuperInterface() {

        Class<DubboxInterfaceImpl> aClass = DubboxInterfaceImpl.class;

        Class<?>[] interfaces = aClass.getInterfaces();

        for (Class<?> var : interfaces) {
            System.out.println(var.getName());
        }
    }

    @Test
    public void testInterfaceSimpleName() {
        Class<DubboxInterface> interfaceClass = DubboxInterface.class;

        System.out.println(interfaceClass.getName());

        System.out.println(interfaceClass.getSimpleName());
    }

    //TODO NOT PASS
    @Test
    public void testInterfaceSubClass() {
        Class<DubboxInterface> clazz = DubboxInterface.class;
    }
}
