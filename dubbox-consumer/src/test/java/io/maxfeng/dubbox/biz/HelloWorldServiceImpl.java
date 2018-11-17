package io.maxfeng.dubbox.biz;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String msg) {
        String result = "hello world " + msg;
        System.out.println(result);
        return result;
    }
}
