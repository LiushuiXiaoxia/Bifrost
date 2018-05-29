package cn.mycommons.bifrost.internal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Req <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class Req implements Parcelable {

    private String uuid;

    private String payload;

    public Req() {
        uuid = UUID.randomUUID().toString();
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

    public static Creator<Req> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Req{" +
                "uuid='" + uuid + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }

    protected Req(Parcel in) {
        uuid = in.readString();
        payload = in.readString();
    }

    public static final Creator<Req> CREATOR = new Creator<Req>() {
        @Override
        public Req createFromParcel(Parcel in) {
            return new Req(in);
        }

        @Override
        public Req[] newArray(int size) {
            return new Req[size];
        }
    };

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