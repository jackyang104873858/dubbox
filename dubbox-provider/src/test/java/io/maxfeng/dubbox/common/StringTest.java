package io.maxfeng.dubbox.common;

import org.junit.Test;

public class StringTest {


    @Test
    public void testStringSplit() {
        String ip = "192.168.130.82";
        String[] split = ip.split("\\.");
        System.out.println(split.length);
    }
}
