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

    /**
     * exclude not registry
     * this suitable for a class implements two or more than two interface
     *
     * @return
     */
    public Class<?>[] exclude() default {Object.class};


}
