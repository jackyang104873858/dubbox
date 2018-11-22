package io.maxfeng.dubbox.boot;

import com.google.common.collect.Maps;
import io.maxfeng.dubbox.annotation.Provider;
import io.maxfeng.dubbox.util.ClassUtil;
import io.maxfeng.dubbox.invoke.DelegateDynamicProxy;
import io.maxfeng.dubbox.registry.zookeeper.ZKRegistry;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Create By 2018/11/10
 * Description 扫描当前类下所有子类的注解
 *
 * @author MaXueFeng
 * @since 1.0
 */
public class ScanningRpc {

    public static Map<String, Class<?>> classMap = Maps.newHashMap();

    public static Map<String, Object> collection = Maps.newConcurrentMap();

    private static void scanningAnnotation(String packageName) {

        List<Class<?>> classes = ClassUtil.getClasses(packageName);

        classes.forEach(v -> {
            if (v.getAnnotation(Provider.class) != null) {

                Class<?>[] interfaces = v.getInterfaces();

                for (Class<?> var1 : interfaces) {
                    //接口注册
                    classMap.put(var1.getName(), var1);

                }
            }
        });

        classes.forEach(v -> {
            if (v.getAnnotation(Provider.class) != null) {
                Class<?>[] interfaces = v.getInterfaces();
            }
        });
    }

    public static void run(Class<?> clazz) throws IOException {

        scanningAnnotation(clazz.getPackage().getName());

        ZKRegistry.registry();

        DelegateDynamicProxy.RestServer.getInstance().start();
    }

}
