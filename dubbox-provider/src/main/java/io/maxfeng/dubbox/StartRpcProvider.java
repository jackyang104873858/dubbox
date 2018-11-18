package io.maxfeng.dubbox;

import com.alibaba.fastjson.JSON;
import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.model.RModel;
import io.maxfeng.dubbox.parse.Parsing;

import java.util.List;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class StartRpcProvider {


    public static void main(String[] args) throws ConfigRpcException {
        List<RModel> rModels = Parsing.obtainRegistryInfo(StartRpcProvider.class);
        String jsonString = JSON.toJSONString(rModels);
        System.out.println(jsonString);
    }
}
