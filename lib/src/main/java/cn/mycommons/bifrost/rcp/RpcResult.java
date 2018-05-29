package cn.mycommons.bifrost.rcp;

import com.google.gson.annotations.SerializedName;

/**
 * RpcResult <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class RpcResult {

    @SerializedName("jsonRpc")
    public String jsonRpc = "1.0";

    @SerializedName("id")
    public String id;

    @SerializedName("result")
    public String result;

    @SerializedName("error")
    public RpcError error;

    @Override
    public String toString() {
        return "RpcResult{" +
                "jsonRpc='" + jsonRpc + '\'' +
                ", id='" + id + '\'' +
                ", result='" + result + '\'' +
                ", error=" + error +
                '}';
    }
}