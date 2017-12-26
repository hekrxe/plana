package com.hekrxe.zk.watcher;

import com.hekrxe.zk.minor.MinorTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;

/**
 * @author hztanhuayou
 * @date 2017/12/20
 */
public class WatcherTest {

    private static void watcherPath() throws Exception {
        String path = "/watcher/single";
        CuratorFramework client = MinorTest.getClient();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());

        final NodeCache nodeCache = new NodeCache(client, path, false);
        nodeCache.start(true);

        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() {
                System.out.println("nodeChanged. " + new String(nodeCache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path, "aaa".getBytes());
        Thread.sleep(100);
        System.out.println(1);
        client.setData().forPath(path, "ccc".getBytes());
        Thread.sleep(100);
        System.out.println(2);
        client.setData().forPath(path, "bbb".getBytes());
        System.out.println(3);
        Thread.sleep(2000);
        client.delete().deletingChildrenIfNeeded().forPath(path);

        Thread.sleep(2000);

        nodeCache.close();
        client.close();
    }

    // 只能监控儿子,孙子是不行的
    private static void watcherChildrenPath() throws Exception {
        String path = "/watcher/a";
        CuratorFramework client = MinorTest.getClient();

        PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) {
                System.out.println(pathChildrenCacheEvent);
            }
        });

        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        Thread.sleep(1000);
        client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1", "aaa".getBytes());
        Thread.sleep(1000);
        client.setData().forPath(path + "/c1", "ddd".getBytes());
        Thread.sleep(1000);
        client.delete().forPath(path + "/c1");
        Thread.sleep(1000);
        client.delete().forPath(path);

        Thread.sleep(2000);
    }

    public static void main(String[] args) throws Exception {
        watcherPath();
    }

}
