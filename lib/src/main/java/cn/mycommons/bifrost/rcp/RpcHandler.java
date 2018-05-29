package cn.mycommons.bifrost.rcp;

import com.google.gson.Gson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.mycommons.bifrost.BifrostAidl;
import cn.mycommons.bifrost.internal.Req;
import cn.mycommons.bifrost.internal.Resp;
import timber.log.Timber;

/**
 * RpcHandler <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class RpcHandler implements InvocationHandler {

    public static <T> T newProxyInstance(Class<T> tClass, BifrostAidl bifrostAidl) {
        Class<?>[] interfaces = new Class[]{tClass};
        RpcHandler handler = new RpcHandler(tClass, bifrostAidl);
        Object proxy = Proxy.newProxyInstance(tClass.getClassLoader(), interfaces, handler);
        return (T) proxy;
    }

    private Class<?> clazz;
    private BifrostAidl bifrostAidl;
    private Gson gson = new Gson();

    private RpcHandler(Class<?> clazz, BifrostAidl bifrostAidl) {
        this.clazz = clazz;
        this.bifrostAidl = bifrostAidl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String clazzName = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        String name = clazzName + "." + methodName;
        Timber.e(">>> %s", name);

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                Timber.i("%s.%s -> %s", name, i, args[i]);
            }
        }

        if ("Object".equals(clazzName)) {
            return null;
        }
        try {
            Object result = null;
            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.clazz = clazz.getName();
            rpcRequest.method = methodName;
            rpcRequest.params = args == null ? null : gson.toJson(args);
            Req req = new Req();
            req.setPayload(gson.toJson(rpcRequest));
            Resp resp = bifrostAidl.exec(req);
            if (resp != null) {
                String data = resp.getPayload();
                RpcResult rpcResult = gson.fromJson(data, RpcResult.class);
                result = gson.fromJson(rpcResult.result, method.getReturnType());
            }
            Timber.e("<<< %s.result = %s", name, result);
            return result;
        } catch (Throwable e) {
            Timber.e("<<< %s.e = %s", name, e.getMessage());
            Throwable cause = e.getCause();
            if (cause != null) {
                throw cause;
            } else {
                throw e;
            }
        }
    }
}