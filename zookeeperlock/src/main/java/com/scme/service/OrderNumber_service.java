package com.scme.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/10/28.
 */
public class OrderNumber_service {
    private static int num=0;

    public String getnumber(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-SS");
        return simpleDateFormat.format(new Date())+"-"+ ++num;
    }
}
