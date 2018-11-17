package io.maxfeng.dubbox.proxy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.maxfeng.dubbox.biz.DubboxInterface;
import io.maxfeng.dubbox.model.MsgBody;
import okhttp3.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author MaXueFeng
 * @see Proxy
 * @since 1.0
 */
@SuppressWarnings("ALL")
public class JdkInvocationProxy implements InvocationHandler {

    private Object target;

    public JdkInvocationProxy(Object target) {
        this.target = target;
    }


    public static Object getProxy(Object v) {
        return (Object)
                Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                        v.getClass().getInterfaces(), new JdkInvocationProxy(v));
    }

    /**
     * @param proxy  Final Proxy Object
     * @param method invoke method
     * @param args   method args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        //执行Rpc通信代码  目前只测试DubboxInterface的producer方法
        OkHttpClient client = new OkHttpClient();

        //封装请求体
        MsgBody body = new MsgBody();

        body.setInterfaceName("io.maxfeng.dubbox.biz.DubboxInterface");

        //封装调用方法的引用
        List<MsgBody.TypeAndMethodName> typeAndMethodNames = Lists.newArrayList();
        MsgBody.TypeAndMethodName typeAndMethodName = new MsgBody.TypeAndMethodName();
        typeAndMethodName.setArgName("produce");
        typeAndMethodName.setType(DubboxInterface.class);
        body.setTypeAndMethodNames(typeAndMethodNames);

        body.setTimeout(3000);

        String jsonString = JSON.toJSONString(body);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonString);

        Request request = new Request.Builder().url("http://127.0.0.1:80/dubbox/invoke").method("POST", requestBody).build();

        Response response = client.newCall(request).execute();

        ResponseBody result = response.body();

        boolean successful = response.isSuccessful();

        if (successful) {
            String jsonBody = new String(response.body().bytes(), "UTF-8");
            MsgBody parseObject = JSON.parseObject(jsonBody, MsgBody.class);
            System.out.println(parseObject);
            return parseObject.getResponseBody();
        }
        return "No result";
    }
}
