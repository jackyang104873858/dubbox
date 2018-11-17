package io.maxfeng.dubbox.zk_redis;

import io.maxfeng.dubbox.registry.zookeeper.ZKRegistry;
import io.maxfeng.dubbox.util.NetUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * @author MaXueFeng
 */
public class CreateModeTest {

    //TODO test failed

    /**
     * Error Info:KeeperErrorCode = NoNode for /dubbox/dubbox/192.168.1.106
     *
     * @throws Exception
     */
    @Test
    public void testCreateTempMode() throws Exception {

        CuratorFramework client = ZKRegistry.client();

        client.create().withMode(CreateMode.EPHEMERAL).forPath("/test/" + NetUtil.obtainIp(), "Hello World".getBytes());

    }

    // TODO Test Pass Right
    @Test
    public void testCreateTempMode1() throws Exception {

        CuratorFramework client = ZKRegistry.client();

        client.create().withMode(CreateMode.PERSISTENT).forPath("/" + NetUtil.obtainIp(), "this a producer ip Address".getBytes());
    }

    @Test
    public void testCreateModeUseNameSpace() {

        ZKRegistry.registry();
    }

    @Test
    public void testCreateModeAndValue() throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder().
                        connectString("127.0.0.1:2181").
                        sessionTimeoutMs(3000).
                        retryPolicy(policy).
                        build();
        client.start();
        client.create().forPath("/test", "hello world".getBytes());
        System.in.read();
    }

}
