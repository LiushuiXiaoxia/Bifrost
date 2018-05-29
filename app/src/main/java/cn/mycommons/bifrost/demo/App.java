package cn.mycommons.bifrost.demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;

import java.util.List;

import cn.mycommons.bifrost.Bifrost;
import cn.mycommons.bifrost.demo.api.INumberApi;
import cn.mycommons.bifrost.demo.api.IUserApi;
import cn.mycommons.bifrost.demo.api.impl.NumberApiImpl;
import cn.mycommons.bifrost.demo.api.impl.UserApiImpl;
import timber.log.Timber;

/**
 * App <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class App extends Application {

    private static App app;

    public static App getApp() {
        return app;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    int pid;
    String pName;
    String logPrefix;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        app = this;

        pid = Process.myPid();
        ActivityManager am = (ActivityManager) base.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
            if (list != null) {
                for (ActivityManager.RunningAppProcessInfo info : list) {
                    if (info.pid == pid) {
                        pName = info.processName;
                        break;
                    }
                }
            }
        }
        logPrefix = pName + "(" + pid + ")";
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Timber.d("[%s]: %s--->onCreate", logPrefix, this.getClass().getSimpleName());

        Bifrost.getInstance().init(this);
        Bifrost.getInstance().register(IUserApi.class, new UserApiImpl());
        Bifrost.getInstance().register(INumberApi.class, NumberApiImpl.class);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Timber.d("%s--->onActivityCreated", activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Timber.d("%s--->onActivityStarted", activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Timber.d("%s--->onActivityResumed", activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Timber.d("%s--->onActivityPaused", activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Timber.d("%s--->onActivityStopped", activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Timber.d("%s--->onActivitySaveInstanceState", activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Timber.d("%s--->onActivityDestroyed", activity);
            }
        });
    }

    public String getLogPrefix() {
        return logPrefix;
    }
}