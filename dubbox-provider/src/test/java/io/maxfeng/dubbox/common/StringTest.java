package io.maxfeng.dubbox.common;

import org.junit.Test;

public class StringTest {


    @Test
    public void testStringSplit() {
        String ip = "192.168.130.82";
        String[] split = ip.split("\\.");
        System.out.println(split.length);
    }

    @Test
    public void testToLower() {
        String className = "InvocationTest";
        //将第一个字符转换为小写
        char[] tmp = new char[1];
        tmp[0] = className.charAt(0);
        String var = new String(tmp);
        String r = var.toLowerCase();
        className = className.replaceFirst(var, r);
        System.out.println(className);
    }
}
