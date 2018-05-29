package cn.mycommons.bifrost;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.HashMap;
import java.util.Map;

import cn.mycommons.bifrost.rcp.RpcHandler;


/**
 * Bifrost <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class Bifrost {

    private static Bifrost instance = new Bifrost();

    public static Bifrost getInstance() {
        return instance;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Map<Class<?>, Class<?>> classConfig = new HashMap<>();
    private Map<Class<?>, Object> instanceConfig = new HashMap<>();
    private Map<Class<?>, Object> proxyCache = new HashMap<>();

    private BifrostAidl bifrostAidl;

    private Bifrost() {

    }

    public void init(Context context) {
        Intent intent = new Intent(context, BifrostService.class);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bifrostAidl = BifrostAidl.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    public void register(Class<?> face, Class<?> impl) {
        classConfig.put(face, impl);
    }

    public <T> void register(Class<T> face, T instance) {
        instanceConfig.put(face, instance);
    }

    public <T> T getInstance(Class<?> face) {
        if (instanceConfig.containsKey(face)) {
            return (T) instanceConfig.get(face);
        } else if (classConfig.containsKey(face)) {
            Class<?> aClass = classConfig.get(face);
            try {
                Object o = aClass.newInstance();
                if (face.isInstance(o)) {
                    instanceConfig.put(face, o);
                    return (T) o;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T> T getRemoteInstance(Class<T> face) {
        if (proxyCache.containsKey(face)) {
            return (T) proxyCache.get(face);
        }
        T proxy = RpcHandler.newProxyInstance(face, bifrostAidl);
        proxyCache.put(face, proxy);
        return proxy;
    }
}