package io.maxfeng.dubbox.parse;

import com.google.common.collect.Lists;
import io.maxfeng.dubbox.annotation.Rpc;
import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.model.RModel;
import io.maxfeng.dubbox.util.ClassUtil;

import java.util.ArrayList;
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
                Class<?>[] temp = aClass.getInterfaces();

                List<Class<?>> superInterfaces = new ArrayList<>(Arrays.asList(temp));

                checkExcludeArg(excludes, superInterfaces);

                if (superInterfaces.size() > 0) {

                    if (superInterfaces.size() == 1 && excludes.length == 1 && excludes[0].equals(Object.class)) {

                        //default condition Object class; add interface to collection
                        RModel v = new RModel();
                        v.setClassPathName(superInterfaces.get(0).getName());
                        v.setGroup(annotation.group());
                        v.setClazz(superInterfaces.get(0));
                        rModels.add(v);

                    } else {
                        //当前类实现了2个或者2个以上的接口  需要排除的是实现的接口不是当前业务系统定义的接口
                        if (excludes.length == superInterfaces.size()) continue;
                        for (Class<?> var : superInterfaces) {
                            if (classList.contains(var)) {
                                //不包含则是没有去掉接口
                                RModel v = new RModel();
                                v.setClazz(var);
                                v.setGroup(annotation.group());
                                v.setClassPathName(var.getName());
                                rModels.add(v);
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
    private static void checkExcludeArg(Class<?>[] excludes, List<Class<?>> superInterfaces) throws ConfigRpcException {
        if (excludes.length == 1 && excludes[0].equals(Object.class)) return;
        for (Class<?> exclude : excludes) {
            if (!superInterfaces.contains(exclude)) {
                throw new ConfigRpcException("exclude interface is error,you only exclude current class implement interfaces");
            } else {
                superInterfaces.remove(exclude);
            }
        }
    }




}
