package com.scme.impl;

import com.scme.Interface.BlogService;

/**
 * Created by Administrator on 2018/11/4.
 */
public class BlogServiceImpl implements BlogService {
    @Override
    public String dubbotest() {
        return "测试成功！";
    }
}
