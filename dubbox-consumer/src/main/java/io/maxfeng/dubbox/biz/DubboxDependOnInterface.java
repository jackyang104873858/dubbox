package io.maxfeng.dubbox.biz;

import io.maxfeng.dubbox.annotation.Component;
import io.maxfeng.dubbox.annotation.Consumer;

/**
 * @author MaXueFeng
 * @see Component 盗版Spring注解  用来辅助dubbox测试效果
 * @since 1.0
 */
@Component
public class DubboxDependOnInterface {

    @Consumer
    private DubboxInterface dubboxInterface;


    public void print() {
        dubboxInterface.producer();
    }


}
