package cn.mycommons.bifrost.rcp;

import com.google.gson.annotations.SerializedName;

/**
 * RpcError <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class RpcError {

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;

    @Override
    public String toString() {
        return "RpcError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}