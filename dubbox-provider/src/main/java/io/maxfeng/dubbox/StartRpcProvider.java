package io.maxfeng.dubbox;

import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.exception.ZKRegistryException;
import io.maxfeng.dubbox.registry.ServiceRegistry;
import io.maxfeng.dubbox.registry.zookeeper.ZookeeperServiceRegistry;

import java.io.IOException;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class StartRpcProvider {


    public static void main(String[] args) throws ZKRegistryException, ConfigRpcException, IOException {
        ServiceRegistry registry = new ZookeeperServiceRegistry();
        registry.execute(StartRpcProvider.class);
        System.in.read();
    }
}
