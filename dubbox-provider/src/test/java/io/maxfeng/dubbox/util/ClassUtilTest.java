package io.maxfeng.dubbox.util;

import io.maxfeng.dubbox.common.util.ClassUtil;
import org.junit.Test;

import java.util.List;

public class ClassUtilTest {


    @Test
    public void testChildrenClass() {
        List<Class<?>> list = ClassUtil.getClasses("io.maxfeng.dubbox");

        list.forEach(v -> System.out.println(v.getName()));
    }

}
