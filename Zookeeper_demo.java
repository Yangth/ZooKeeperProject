package com.scme.service;

import org.apache.zookeeper.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/10/27.
 */
public class Zookeeper_demo {
    private static final String ADDRESS = "127.0.0.1:2181";
    private static final int SESSION_OUTTIME = 2000;
    private static final CountDownLatch downlatch=new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk=new ZooKeeper(ADDRESS, SESSION_OUTTIME, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
               Event.KeeperState state = watchedEvent.getState();
               Event.EventType eventType = watchedEvent.getType();
               if(state== Event.KeeperState.SyncConnected){
                   if (eventType== Event.EventType.None){
                       downlatch.countDown();
                       System.out.println("zk..开始启动了。。。");
                   }
               }
            }
        });
        downlatch.await();
        String result = zk.create("/itmayeidu_Lasting", "Lasting".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.out.println(result);
        Thread.sleep(5000);
        zk.close();


    }
}
