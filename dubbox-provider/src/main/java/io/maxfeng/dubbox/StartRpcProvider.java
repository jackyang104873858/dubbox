package io.maxfeng.dubbox;

import com.alibaba.fastjson.JSON;
import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.parse.Parsing;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class StartRpcProvider {


    //注册服务
//    public static void main(String[] args) throws IOException {
//        P.use("app.properties");
//        ScanningRpc.run(StartRpcProvider.class);
//    }

    public static void main(String[] args) throws ConfigRpcException {
        Parsing.init(StartRpcProvider.class);
        String s = JSON.toJSONString(Parsing.rModels);
        System.out.println(s);
    }
}
