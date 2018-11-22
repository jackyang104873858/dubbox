package io.maxfeng.dubbox.context;

import com.google.common.collect.Maps;
import io.maxfeng.dubbox.annotation.Component;
import io.maxfeng.dubbox.annotation.Consumer;
import io.maxfeng.dubbox.exception.ConsumeParseException;
import io.maxfeng.dubbox.proxy.JdkInvocationProxy;
import io.maxfeng.dubbox.util.ClassUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class ConsumerContext {

    public static final Map<String, Object> beans = Maps.newHashMap();

    /**
     * 启动注解扫描
     */
    public static void run(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Class<?>> classList = ClassUtil.getClasses(clazz.getPackage().getName());

        bean(classList);

        for (Class<?> var : classList) {

        }
    }

    /**
     * 解析成员
     *
     * @param var
     */
    private static void parseField(Class<?> var) throws ConsumeParseException, IllegalAccessException {
        //改变类型对象  如果适合Spring整合直接从容器中取对象就行,但这个地方需要我们自己创建对象
        String simpleName = var.getSimpleName();

        Object instance = beans.get(simpleName);

        Field[] fields = var.getDeclaredFields();

        for (Field field : fields) {

            if (field.getAnnotationsByType(Consumer.class) != null) {

                if (!field.getType().isInterface())
                    throw new ConsumeParseException("Consume annotation only apply on interface;Please check you config!");

                if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers()))
                    throw new ConsumeParseException("The interface value can not is final or static type!");

                //防止访问不了指定对象
                field.setAccessible(true);

                //value  TODO create proxy Object
                field.set(instance, JdkInvocationProxy.getProxy(var));
            }
        }
    }

    /**
     * @param classList
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @see ConsumerContext#beans
     * <p>
     * <p>
     * 简单模仿Spring创建对象
     */
    @Deprecated
    private static void bean(List<Class<?>> classList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Class<?> aClass : classList) {

            if (aClass.getAnnotation(Component.class) != null) {
                Object instance = aClass.getConstructor().newInstance();
                beans.put(aClass.getSimpleName(), instance);
            }
        }
    }
}
