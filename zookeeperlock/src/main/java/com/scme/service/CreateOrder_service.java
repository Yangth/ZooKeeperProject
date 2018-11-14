package com.scme.service;

import com.scme.zookeeper_lock.Lock;
import com.scme.zookeeper_lock.ZkLock;

/**
 * Created by Administrator on 2018/10/28.
 */
public class CreateOrder_service implements Runnable {

    OrderNumber_service orderNumber_service=new OrderNumber_service();
//     Lock lock=new ZkLock();

    public void run() {
//        lock.getlock();
        synchronized (CreateOrder_service.class){
            getnumber();
        }

//        lock.unlock();
    }

    public void getnumber(){
        String number=orderNumber_service.getnumber();
        System.out.println(number);
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(new CreateOrder_service()).start();
        }
    }
}
