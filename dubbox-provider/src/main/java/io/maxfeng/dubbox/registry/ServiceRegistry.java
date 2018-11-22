package io.maxfeng.dubbox.registry;

import io.maxfeng.dubbox.exception.ConfigRpcException;
import io.maxfeng.dubbox.exception.ZKRegistryException;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public interface ServiceRegistry {

    String BASE_NAMESPACE = "dubbox";


    static CuratorFramework zkClient() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder().
                        connectString("127.0.0.1:2181").
                        sessionTimeoutMs(3000).
                        namespace(BASE_NAMESPACE).
                        retryPolicy(policy).
                        build();
        client.start();

        return client;
    }

    static Object redisClient() {
        return null;
    }

    //服务注册
    void execute(Class<?> clazz) throws ConfigRpcException, ZKRegistryException;
}
