package io.maxfeng.dubbox.registry.zookeeper;

import io.maxfeng.dubbox.boot.ScanningRpc;
import io.maxfeng.dubbox.util.NetUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;


/**
 * @author MaXueFeng
 * @since 1.0
 */
public class ZKRegistry {

    private static final String BASE_NAMESPACE = "dubbox";

    public static CuratorFramework client() {

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

    public static void registry() {
        CuratorFramework client = client();

        ScanningRpc.classMap.forEach((k, v) -> {
            try {
                client.create().withMode(CreateMode.PERSISTENT).forPath("/" + k);
                client.create().withMode(CreateMode.PERSISTENT).forPath("/" + k + "/provider");
                client.create().withMode(CreateMode.PERSISTENT).forPath("/" + k + "/provider/" + NetUtil.obtainIp() + ":80");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
