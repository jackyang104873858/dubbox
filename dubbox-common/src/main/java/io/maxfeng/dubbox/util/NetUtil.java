package io.maxfeng.dubbox.util;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MaXueFeng
 */
public class NetUtil {


    public static String obtainIp() throws SocketException {

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {

            NetworkInterface netInterface = networkInterfaces.nextElement();

            List<InterfaceAddress> tmp = netInterface.getInterfaceAddresses();

            for (InterfaceAddress v : tmp) {
                String address = v.getAddress().getHostAddress().replace("/", "");

                if (address.startsWith("127.0") || address.startsWith("0:") || address.startsWith("0.")) continue;

                // address[0] is not a Number  continue
                if (!Character.isDigit(address.charAt(0))) continue;

                //注意 点运算符是Java中的特殊字符需要转义
                String[] split = address.split("\\.");

                if (split.length != 0) {
                    int n = 0;
                    for (String var : split) {
                        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
                        Matcher isNum = pattern.matcher(var);
                        if (isNum.matches()) {
                            n++;
                        }
                    }
                    if (n == 3) {
                        return address;
                    }
                }
            }
        }
        return null;
    }
}
