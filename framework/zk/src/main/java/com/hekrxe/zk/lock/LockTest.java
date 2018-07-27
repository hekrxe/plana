package com.hekrxe.zk.lock;

import com.hekrxe.zk.minor.MinorTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author hztanhuayou
 * @date 2017/12/20
 */
public class LockTest {
    private static String PATH = "/lock";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = MinorTest.getClient();
        InterProcessMutex mutex = new InterProcessMutex(client, PATH);
        mutex.acquire();
        mutex.release();
    }
}
