package io.maxfeng.dubbox.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class RpcBody implements Serializable {

    /**
     * requestID   promise NIO no cause BUG
     */
    private long requestID = Tab.log_id.incrementAndGet();

    /**
     * interface package path
     */
    private String interfaceName;

    /**
     * method name
     */
    private String methodName;

    /**
     * invokeInfos class type and value
     */
    private List<InvokeInfo> invokeInfos;

    /**
     * response code
     */
    private int code;

    /**
     * request time out
     */
    private int timeout;

    private Object responseBody;

    public Object getResponseBody() {
        return responseBody;
    }

    public List<InvokeInfo> getInvokeInfos() {
        return invokeInfos;
    }

    public void setInvokeInfos(List<InvokeInfo> invokeInfos) {
        this.invokeInfos = invokeInfos;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public long getRequestID() {
        return requestID;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "RpcBody{" +
                "requestID=" + requestID +
                ", interfaceName='" + interfaceName + '\'' +
                ", argName='" + methodName + '\'' +
                ", invokeInfos=" + invokeInfos +
                ", code=" + code +
                ", timeout=" + timeout +
                ", responseBody=" + responseBody +
                '}';
    }

    public static class InvokeInfo implements Serializable {

        private Class type;

        private String argName;

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public String getArgName() {
            return argName;
        }

        public void setArgName(String argName) {
            this.argName = argName;
        }

        @Override
        public String toString() {
            return "InvokeInfo{" +
                    "type=" + type +
                    ", argName='" + argName + '\'' +
                    '}';
        }
    }
}
