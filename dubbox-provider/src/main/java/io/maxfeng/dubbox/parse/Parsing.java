package io.maxfeng.dubbox.parse;

import com.google.common.collect.Lists;
import io.maxfeng.dubbox.annotation.Rpc;
import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.model.RModel;
import io.maxfeng.dubbox.util.ClassUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author MaXueFeng
 * @see io.maxfeng.dubbox.model.RModel
 * @since 1.0
 */
public class Parsing {

    public static List<RModel> rModels = Lists.newCopyOnWriteArrayList();

    /**
     * 接口存储数据结构设计
     * List<RModel>
     *
     * @param target
     */
    public static void init(Class<?> target) throws ConfigRpcException {

        //获取所有类
        List<Class<?>> classList = ClassUtil.getClasses(target.getPackage().getName());

        for (Class<?> aClass : classList) {
            Rpc annotation = aClass.getAnnotation(Rpc.class);
            if (annotation != null) {

                Class<?>[] excludes = annotation.exclude();

                //参数检查
                Class<?>[] superInterfaces = aClass.getInterfaces();
                checkExcludeClass(excludes, superInterfaces);

                if (superInterfaces.length > 0) {

                    if (superInterfaces.length == 1) {

                        if (excludes.length > 1) {
                            throw new ConfigRpcException("add interface " + aClass.getName() + " registry failed; Because of exclude");
                        }
                        //default condition Object class; add interface to collection
                        if (excludes.length == 1 && excludes[0].equals(Object.class)) {
                            RModel v = new RModel();
                            v.setClassPathName(aClass.getName());
                            v.setGroup(annotation.group());
                            v.setClazz(aClass);
                            rModels.add(v);
                        }

                    } else {
                        //当前类实现了2个或者2个以上的接口  需要排除的是实现的接口不是当前业务系统定义的接口
                        if (excludes.length == superInterfaces.length) continue;
                        List<Class<?>> var1 = Arrays.asList(excludes);
                        for (Class<?> var : superInterfaces) {
                            if (!var1.contains(var)) {
                                //不包含则是没有去掉接口
                                RModel v = new RModel();
                                v.setClazz(aClass);
                                v.setGroup(annotation.group());
                                v.setClassPathName(aClass.getName());
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * exclude参数检查
     *
     * @param excludes
     * @param superInterfaces
     */
    private static void checkExcludeClass(Class<?>[] excludes, Class<?>[] superInterfaces) throws ConfigRpcException {
        if (excludes.length == 1 && excludes[0].equals(Object.class)) return;
        List<Class<?>> tmp = Arrays.asList(superInterfaces);
        for (Class<?> exclude : excludes) {
            if (!tmp.contains(exclude)) {
                throw new ConfigRpcException("exclude interface is error,you only exclude current class implement interfaces");
            }
        }
    }
}
