package io.maxfeng.dubbox.zk_redis;

import io.maxfeng.dubbox.common.util.NetUtil;
import io.maxfeng.dubbox.registry.zookeeper.ZKRegistry;
import org.apache.curator.framework.CuratorFramework;
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

        client.create().withMode(CreateMode.EPHEMERAL).forPath("/dubbox/" + NetUtil.obtainIp());
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
}
