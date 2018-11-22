package io.maxfeng.dubbox.annotation;

import java.lang.annotation.*;

/**
 * 测试版本模仿Spring组装对象
 *
 * @author MaXueFeng
 * @since 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface Component {
}
