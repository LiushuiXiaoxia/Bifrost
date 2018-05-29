package cn.mycommons.bifrost.rcp;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * RpcRequest <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class RpcRequest {

    @SerializedName("jsonRpc")
    public String jsonRpc = "1.0";

    @SerializedName("id")
    public String id = UUID.randomUUID().toString();

    @SerializedName("clazz")
    public String clazz;

    @SerializedName("method")
    public String method;

    @SerializedName("params")
    public String params;

    @Override
    public String toString() {
        return "RpcRequest{" +
                "jsonRpc='" + jsonRpc + '\'' +
                ", id='" + id + '\'' +
                ", clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}