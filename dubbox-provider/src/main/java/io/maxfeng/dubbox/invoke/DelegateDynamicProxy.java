package io.maxfeng.dubbox.invoke;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import io.maxfeng.dubbox.boot.ScanningRpc;
import io.maxfeng.dubbox.exception.InvokeException;
import io.maxfeng.dubbox.model.MsgBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.http.common.HttpConfig;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.common.handler.HttpRequestHandler;
import org.tio.http.server.HttpServerStarter;
import org.tio.http.server.annotation.RequestPath;
import org.tio.http.server.handler.DefaultHttpRequestHandler;
import org.tio.http.server.util.Resps;
import org.tio.server.ServerGroupContext;
import org.tio.utils.jfinal.P;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Create By 2018/11/10
 * Description   动态代理类
 *
 * @author MaXueFeng
 * @since 1.0
 */
@RequestPath(value = "/dubbox")
public class DelegateDynamicProxy {

    static Logger log = LoggerFactory.getLogger(DelegateDynamicProxy.class);

    @RequestPath(value = "/invoke")
    public HttpResponse proxyInvoke(HttpRequest request) {

        String jsonStr = request.getBodyString();

        MsgBody info = DynamicProxy.parseInvokeInfo(jsonStr);

        System.out.println(info);

        try {
            DynamicProxy.invokeMethod(info);
        } catch (InvokeException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return Resps.json(request, info);
    }


    static class DynamicProxy {

        static MsgBody parseInvokeInfo(String jsonStr) {
            assert jsonStr != null;
            return JSON.parseObject(jsonStr, MsgBody.class);
        }

        static void invokeMethod(MsgBody v) throws InvokeException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            assert v != null;
            String interfaceName = v.getInterfaceName();
            if (Strings.isNullOrEmpty(interfaceName)) {
                log.error("Biz Error Please Check You Object Interface is Null or Empty String");
                throw new InvokeException("Please Check You Object Interface is Null or Empty String");
            }
            Class<?>[] reference = null;
            Object[] args = null;

            List<MsgBody.TypeAndMethodName> tmp = v.getTypeAndMethodNames();
            if (tmp != null && tmp.size() > 0) {
                reference = new Class[tmp.size()];
                args = new Object[tmp.size()];
                for (int i = 0; i < tmp.size(); i++) {
                    MsgBody.TypeAndMethodName var1 = tmp.get(i);
                    reference[i] = var1.getType();
                    args[i] = var1.getArgName();
                }
            }

            Class<?> clazz = ScanningRpc.classMap.get(interfaceName);

            if (reference != null) {
                Method method = clazz.getMethod(v.getMethodName(), reference);
                //此处需要创建对象
                method.invoke(new Object(), args);
            } else {
                Method method = clazz.getMethod(v.getMethodName());
                //此处需要创建对象
                method.invoke(new Object());
            }
        }
    }


    public static class RestServer {

        private static RestServer server;

        public static RestServer getInstance() {
            if (server == null) {
                server = new RestServer();
            }
            return server;
        }

        private HttpConfig config;

        private HttpRequestHandler handler;

        private HttpServerStarter starter;

        private ServerGroupContext context;

        private void init() {

            int port = P.getInt("http.port");

            config = new HttpConfig(port, 3000L, null, null);

            config.setMaxLiveTimeOfStaticRes(P.getInt("http.maxLiveTimeOfStaticRes"));

            config.setPage404("http.404");

            config.setPage500("http.500");

            config.setUseSession(false);

            config.setCheckHost(false);

            handler = new DefaultHttpRequestHandler(config, RestServer.class);

            starter = new HttpServerStarter(config, handler);
            context = starter.getServerGroupContext();
        }

        public void start() throws IOException {
            init();
            starter.start();
        }
    }
}
