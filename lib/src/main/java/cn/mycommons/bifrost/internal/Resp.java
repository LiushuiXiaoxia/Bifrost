package cn.mycommons.bifrost.internal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Resp <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class Resp implements Parcelable {

    private String uuid;

    private String payload;

    public Resp() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Resp{" +
                "uuid='" + uuid + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }

    public static final Creator<Resp> CREATOR = new Creator<Resp>() {
        @Override
        public Resp createFromParcel(Parcel in) {
            return new Resp(in);
        }

        @Override
        public Resp[] newArray(int size) {
            return new Resp[size];
        }
    };

    protected Resp(Parcel in) {
        uuid = in.readString();
        payload = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(payload);
    }
}