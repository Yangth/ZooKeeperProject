package com.scme.zookeeper_lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by Administrator on 2018/10/28.
 */
public abstract class AbstractZklockImpl implements Lock {
    // zk连接地址
    private static final String CONNECTSTRING = "127.0.0.1:2181";
    // 创建zk连接
    protected ZkClient zkClient = new ZkClient(CONNECTSTRING);

    protected static final String PATH = "/lock";

    @Override
    public void getlock() {
        if (trylock()){
            System.out.println("####获得锁资源#####");
        }else {
            awaitlock();
            getlock();
        }
    }

    @Override
    public void unlock() {
        if (zkClient!=null){
            zkClient.close();
            System.out.println("####释放锁资源#####");
        }
    }

     abstract  Boolean trylock();

     abstract  void awaitlock();
}
