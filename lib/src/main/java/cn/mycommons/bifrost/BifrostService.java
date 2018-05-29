package cn.mycommons.bifrost;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.mycommons.bifrost.internal.BifrostAidlImpl;
import timber.log.Timber;

/**
 * BifrostService <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class BifrostService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.i("%s-->onCreate", this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BifrostAidlImpl();
    }
}