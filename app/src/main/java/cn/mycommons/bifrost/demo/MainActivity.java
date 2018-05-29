package cn.mycommons.bifrost.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.mycommons.bifrost.Bifrost;
import cn.mycommons.bifrost.demo.api.INumberApi;
import cn.mycommons.bifrost.demo.api.IUserApi;
import cn.mycommons.bifrost.demo.bean.User;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IUserApi userApi = Bifrost.getInstance().getRemoteInstance(IUserApi.class);
                User user = userApi.login("admin", "123456");

                Timber.i("user = %s", user);

                INumberApi numberApi = Bifrost.getInstance().getRemoteInstance(INumberApi.class);
                int ret = numberApi.add(1, 2);

                Toast.makeText(getApplicationContext(), "1 + 2 = " + ret, Toast.LENGTH_LONG).show();
            }
        });
    }
}