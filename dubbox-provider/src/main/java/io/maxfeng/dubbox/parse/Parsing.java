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

    private static List<RModel> rModels = Lists.newArrayList();

    /**
     * 接口存储数据结构设计
     * List<RModel>
     *
     * @param target
     * @see RModel#setGroup(java.lang.String)
     */
    private static void init(Class<?> target) throws ConfigRpcException {

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
                        v.setClazz(superInterfaces.get(0));
                        v.setCount();
                        //setGroup方法写在最后面  避免空指针
                        v.setGroup(annotation.group());
                        rModels.add(v);

                    } else {
                        //当前类实现了2个或者2个以上的接口  需要排除的是实现的接口不是当前业务系统定义的接口
                        if (excludes.length == superInterfaces.size()) continue;
                        for (Class<?> var : superInterfaces) {
                            if (classList.contains(var)) {
                                //不包含则是没有去掉接口
                                RModel v = new RModel();
                                v.setClazz(var);
                                v.setClassPathName(var.getName());
                                v.setCount();
                                //setGroup方法写在最后面  避免空指针
                                v.setGroup(annotation.group());
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

    //计算处理接口注册  group 一定不等于 "" || null
//    @Deprecated
    private static void tableRModel() throws ConfigRpcException {

        int t = rModels.size();
        for (int i = 0; i < t; i++) {

            for (int j = 0; j < t; j++) {
                if (i != j) {

                    if (rModels.get(i).getClazz().equals(rModels.get(j).getClazz())) {
                        //不能自己和自己比
                        if (rModels.get(i).getGroup().equals(rModels.get(j).getGroup())) {
                            throw new ConfigRpcException(rModels.get(i).getClassPathName() + "  By two or more than two class implement," +
                                    "@Rpc annotation field 'group',required group is not same;");
                        }
//                        else {
//                            //本质是一维数据   所以不设置j索引出的count计数器  目前得出结论统计计数器对于注册表没有太大的作用  所以放弃此方案
//                            rModels.get(i).setCount();
//                        }
                    }
                }
            }
        }
    }

    public static List<RModel> obtainRegistryInfo(Class<?> clazz) throws ConfigRpcException {
        init(clazz);
        return rModels;
    }
}
