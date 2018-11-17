package io.maxfeng.dubbox;

import io.maxfeng.dubbox.boot.ScanningRpc;
import org.tio.utils.jfinal.P;

import java.io.IOException;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class StartRpcProvider {


    //注册服务
    public static void main(String[] args) throws IOException {
        P.use("app.properties");
        ScanningRpc.run(StartRpcProvider.class);
    }
}
