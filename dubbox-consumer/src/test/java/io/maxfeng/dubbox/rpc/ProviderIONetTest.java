package io.maxfeng.dubbox.rpc;

import io.maxfeng.dubbox.biz.DubboxDependOnInterface;
import org.junit.Test;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class ProviderIONetTest {

    @Test
    public void testRpcNetByAIO() {
        DubboxDependOnInterface depend = new DubboxDependOnInterface();

        depend.print();
    }
}
