package io.maxfeng.dubbox.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class Tab {

    //维护一个全局requestID
    public static AtomicLong log_id = new AtomicLong(0L);
}
