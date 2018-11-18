package io.maxfeng.dubbox.registry.zookeeper;

import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.model.RModel;
import io.maxfeng.dubbox.parse.Parsing;
import io.maxfeng.dubbox.registry.ServiceRegistry;

import java.util.List;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class ZookeeperServiceRegistry implements ServiceRegistry {

    //服务注册
    @Override
    public void execute(Class<?> clazz) throws ConfigRpcException {

        //注册表
        List<RModel> rModels = Parsing.obtainRegistryInfo(clazz);

        //此处不需要参数安全检查   在注册表解析阶段已经检查完了
        for (RModel v : rModels) {

        }
    }
}
