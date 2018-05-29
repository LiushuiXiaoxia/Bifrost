package cn.mycommons.bifrost.demo.api;

import cn.mycommons.bifrost.demo.bean.User;

/**
 * IUserApi <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public interface IUserApi {

    User login(String name, String password);
}