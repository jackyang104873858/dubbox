package io.maxfeng.dubbox.registry.zookeeper;

import com.google.common.base.Strings;
import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.exception.ZKRegistryException;
import io.maxfeng.dubbox.model.RModel;
import io.maxfeng.dubbox.parse.Parsing;
import io.maxfeng.dubbox.registry.ServiceRegistry;
import io.maxfeng.dubbox.util.NetUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * policy: 节点注册策略: (namespace + 接口名称) -持久节点
 * <p>
 * 接口名称注册:io.maxfeng.dubbox.biz.DubboxInterface?implement=io.maxfeng.dubbox.biz.DubboxInterfaceImpl_group="group1"
 *
 * @author MaXueFeng
 * @since 1.0
 */
public class ZookeeperServiceRegistry implements ServiceRegistry {

    private static CuratorFramework client;

    static {
        client = ServiceRegistry.zkClient();
    }

    //接口服务分析
    @Override
    public void execute(Class<?> clazz) throws ConfigRpcException, ZKRegistryException {

        //注册表
        List<RModel> rModels = Parsing.obtainRegistryInfo(clazz);

        //此处不需要参数安全检查   在注册表解析阶段已经检查完了
        for (RModel v : rModels) {
            registryInterfaceURL(v);
        }
    }


    private void registryInterfaceURL(RModel v) throws ZKRegistryException {
        assert client != null;
        try {
            //注册当前机器的信息
            String serviceInfo = "/" + v.getClassPathName();

            if (Strings.isNullOrEmpty(v.getGroup())) {
                doCreate(serviceInfo, true);

            } else {
                serviceInfo = serviceInfo + "?group=" + v.getGroup();
                doCreate(serviceInfo, true);
            }
            serviceInfo += "/provider";
            doCreate(serviceInfo, true);
            serviceInfo += "/" + NetUtil.obtainIp() + ":80";
            //临时节点
            doCreate(serviceInfo, false);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ZKRegistryException("zkClient zookeeper create mode is failed!");
        }
    }


    //namespace不算  直接检测后面的数据节点
    public void doCreate(String path, boolean persistent) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        if (stat == null) {
            if (persistent) {
                client.create().withMode(CreateMode.PERSISTENT).forPath(path);
            } else {
                client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
            }
        }
    }

}
