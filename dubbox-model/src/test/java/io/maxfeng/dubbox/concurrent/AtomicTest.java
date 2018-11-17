package io.maxfeng.dubbox.concurrent;

import io.maxfeng.dubbox.model.Tab;
import org.junit.Test;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class AtomicTest {

    @Test
    public void testAtomicLong() {
        long var = Tab.log_id.incrementAndGet();
        System.out.println(var);
        long var1 = Tab.log_id.incrementAndGet();
        System.out.println(var1);
    }
}
