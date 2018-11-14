package com.scme.zookeeper_lock;

/**
 * Created by Administrator on 2018/10/28.
 */
public interface Lock {
    public void getlock();

    public void unlock();
}
