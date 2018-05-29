package cn.mycommons.bifrost.demo.api.impl;

import cn.mycommons.bifrost.demo.api.INumberApi;

/**
 * NumberApiImpl <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class NumberApiImpl implements INumberApi {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}