package cn.mycommons.bifrost.internal;

import android.os.RemoteException;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.mycommons.bifrost.Bifrost;
import cn.mycommons.bifrost.BifrostAidl;
import cn.mycommons.bifrost.rcp.RpcRequest;
import timber.log.Timber;

/**
 * BifrostAidlImpl <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class BifrostAidlImpl extends BifrostAidl.Stub {

    private Gson gson = new Gson();

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public Resp exec(Req req) throws RemoteException {
        Timber.i("%s-->exec", this);
        Timber.i("req = %s", req);
        String data = req.getPayload();

        RpcRequest rpcRequest = gson.fromJson(data, RpcRequest.class);
        Timber.i("rpcRequest = %s", rpcRequest);

        try {
            Class<?> clazz = Class.forName(rpcRequest.clazz);
            Method method = null;
            for (Method m : clazz.getMethods()) {
                if (m.getName().equals(rpcRequest.method)) {
                    method = m;
                    break;
                }
            }
            if (method != null) {
                Class<?>[] types = method.getParameterTypes();
                List<Object> args = new ArrayList<>();
                if (!TextUtils.isEmpty(rpcRequest.params)) {
                    JSONArray array = new JSONArray(rpcRequest.params);
                    for (int i = 0; i < array.length(); i++) {
                        String o = array.getString(i);
                        args.add(gson.fromJson(o, types[i]));
                    }
                }
                Object instance = Bifrost.getInstance().getInstance(clazz);
                Timber.i("instance = %s", instance);
                Timber.i("method = %s", method);
                Timber.i("types = %s", Arrays.toString(types));
                Timber.i("params = %s", args);
                Object result = method.invoke(instance, args.toArray(new Object[0]));
                Timber.i("result = %s", result);

                return RespUtil.success(req.getUuid(), rpcRequest.id, result);
            }
            throw new RuntimeException("method " + rpcRequest.method + " cant not find");
        } catch (Exception e) {
            Timber.e(e);
            // e.printStackTrace();
            return RespUtil.fail(req.getUuid(), rpcRequest.id, e);
        }
    }
}