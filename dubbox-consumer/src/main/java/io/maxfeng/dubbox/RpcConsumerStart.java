package io.maxfeng.dubbox;

import io.maxfeng.dubbox.context.ConsumerContext;
import io.maxfeng.dubbox.exception.ConsumeParseException;

import java.lang.reflect.InvocationTargetException;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class RpcConsumerStart {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ConsumeParseException {
        ConsumerContext.run(RpcConsumerStart.class);
    }
}
