package cn.mycommons.bifrost.internal;

import com.google.gson.Gson;

import cn.mycommons.bifrost.rcp.RpcResult;
import cn.mycommons.bifrost.rcp.RpcResultUtil;

/**
 * RespUtil <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class RespUtil {

    private static Gson gson = new Gson();

    public static Resp success(String uuid, String id, Object object) {
        RpcResult success = RpcResultUtil.success(id, object);

        Resp resp = new Resp();
        resp.setUuid(uuid);
        resp.setPayload(gson.toJson(success));

        return resp;
    }

    public static Resp fail(String id, String uuid, Exception e) {
        RpcResult rpcResult = RpcResultUtil.fail(id, e.getMessage());

        Resp resp = new Resp();
        resp.setUuid(uuid);
        resp.setPayload(gson.toJson(rpcResult));
        return resp;
    }
}