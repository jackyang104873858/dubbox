package io.maxfeng.dubbox.net;

import org.junit.Test;

import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class NetIpTest {

    //下面测试出来的IP地址:127.0.0.1  测试代码存在问题 TODO
    @Test
    public void testIpConfig() throws UnknownHostException {

        InetAddress host = InetAddress.getLocalHost();

        String ip = Arrays.toString(host.getAddress());

        System.out.println(ip);

        String hostName = host.getHostName();

        System.out.println(hostName);
    }

    //测试结果仍然位127.0.0.1 TODO   存在集群的话每台机器设备的IP都会成为127.0.0.1
    @Test
    public void testIpConfig1() throws UnknownHostException, SocketException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostAddress);

        InetAddress address = Inet4Address.getLocalHost();

        System.out.println(address);
        String var = address.getHostAddress();
        System.out.println(var);

        String var2 = InetAddress.getLoopbackAddress().getHostAddress();

        System.out.println(var2);
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            System.out.println("name : " + networkInterface.getName());
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();

                String ip = inetAddress.getHostAddress();

                System.out.println("address:" + ip);
            }
        }
    }
}
