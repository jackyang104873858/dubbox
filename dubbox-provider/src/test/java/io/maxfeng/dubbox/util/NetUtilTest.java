package io.maxfeng.dubbox.util;

import io.maxfeng.dubbox.common.util.NetUtil;
import org.junit.Test;

import java.net.SocketException;

public class NetUtilTest {

    @Test
    public void testRightIp() throws SocketException {
        String s = NetUtil.obtainIp();
        System.out.println(s);
    }
}
