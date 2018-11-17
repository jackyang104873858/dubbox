package io.maxfeng.dubbox.annotation;

import java.lang.annotation.*;

/**
 * @author MaXueFeng
 * @since 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Rpc {

    /**
     * interface group
     *
     * @return
     */
    public String group() default "";

    /**
     * instance alias
     *
     * @return
     */
    public String alias() default "";
}
