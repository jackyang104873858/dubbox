package io.maxfeng.dubbox.annotation;

import java.lang.annotation.*;

/**
 * @author MaXueFeng
 * @since 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcInvoke {
}
