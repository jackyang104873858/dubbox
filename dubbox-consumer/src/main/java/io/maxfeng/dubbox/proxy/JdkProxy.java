package io.maxfeng.dubbox.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author MaXueFeng
 * @see Proxy
 * @since 1.0
 */
@SuppressWarnings("ALL")
public class JdkProxy implements InvocationHandler {

    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }


    public static Object getProxy(Object v) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), v.getClass().getInterfaces(), new JdkProxy(v));
    }

    /**
     * @param proxy  Final Proxy Object
     * @param method invoke method
     * @param args   method args
     * @return
     * @throws Throwable
     */
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//        //执行Rpc通信代码  目前只测试DubboxInterface的producer方法
//        OkHttpClient client = new OkHttpClient();
//
//        //封装请求体
//        RpcBody body = new RpcBody();
//
//        body.setInterfaceName("io.maxfeng.dubbox.biz.DubboxInterface");
//
//        //封装调用方法的引用
//        List<RpcBody.InvokeInfo> invokeInfos = Lists.newArrayList();
//        RpcBody.InvokeInfo invokeInfo = new RpcBody.InvokeInfo();
//        invokeInfo.setArgName("produce");
//        invokeInfo.setType(DubboxInterface.class);
//        body.setInvokeInfos(invokeInfos);
//
//        body.setTimeout(3000);
//
//        String jsonString = JSON.toJSONString(body);
//
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonString);
//
//        Request request = new Request.Builder().url("http://127.0.0.1:80/dubbox/invoke").method("POST", requestBody).build();
//
//        Response response = client.newCall(request).execute();
//
//        ResponseBody result = response.body();
//
//        boolean successful = response.isSuccessful();
//
//        if (successful) {
//
//            String jsonBody = new String(response.body().bytes(), "UTF-8");
//
//            RpcBody parseObject = JSON.parseObject(jsonBody, RpcBody.class);
//
//            System.out.println(parseObject);
//
//            return parseObject.getResponseBody();
//        }
//        return "No result";
//    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("io.maxfeng.dubbox.proxy.JdkProxy.invoke");
        return "hello world";
    }
}
