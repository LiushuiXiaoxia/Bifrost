package cn.mycommons.bifrost.demo.api.impl;

import java.util.ArrayList;
import java.util.List;

import cn.mycommons.bifrost.demo.App;
import cn.mycommons.bifrost.demo.api.IUserApi;
import cn.mycommons.bifrost.demo.bean.Message;
import cn.mycommons.bifrost.demo.bean.User;
import timber.log.Timber;

/**
 * UserApiImpl <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class UserApiImpl implements IUserApi {

    @Override
    public User login(String name, String password) {
        Timber.i("[%s] %s.login name = %s, password = %s", App.getApp().getLogPrefix(), this, name, password);

        List<Message> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Message message = new Message();
            message.setId(200 + i);
            message.setTitle("title" + i);
            message.setContent("content" + i);
            list.add(message);
        }
        User user = new User();
        user.setId(100);
        user.setName(name);
        user.setMessages(list);

        Timber.i("[%s] %s.login user = %s", App.getApp().getLogPrefix(), this, user);

        return user;
    }
}