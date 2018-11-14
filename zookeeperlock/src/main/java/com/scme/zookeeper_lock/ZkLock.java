package com.scme.zookeeper_lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/10/28.
 */
public class ZkLock extends AbstractZklockImpl {
    CountDownLatch countDownLatch=null;
    @Override
    Boolean trylock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        }catch (Exception e){
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    void awaitlock() {
        IZkDataListener iZkDataListener=new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if ((countDownLatch!=null))
                    countDownLatch.countDown();
            }
        };
        zkClient.subscribeDataChanges(PATH,iZkDataListener);
        if (zkClient.exists(PATH)){
            countDownLatch=new CountDownLatch(1);
            try {
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(PATH,iZkDataListener);

    }
}
