package cn.mycommons.bifrost.rcp;

import com.google.gson.Gson;

/**
 * RpcResultUtil <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class RpcResultUtil {

    public static RpcResult success(String id, Object result) {
        RpcResult rpcResult = new RpcResult();
        rpcResult.id = id;
        rpcResult.result = new Gson().toJson(result);
        return rpcResult;
    }

    public static RpcResult fail(String id, String msg) {
        RpcError error = new RpcError();
        error.code = 500;
        error.message = msg;

        RpcResult rpcResult = new RpcResult();
        rpcResult.id = id;
        rpcResult.error = error;
        return rpcResult;
    }
}