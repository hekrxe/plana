package com.hekrxe.zk.minor;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author hztanhuayou
 * @date 2017/12/20
 */
public class MinorTest {
    private static final String ZK_CONNECT_STRING = "my.linux.com:2181,my.linux.com:2182,my.linux.com:2183";
    private static final String NAMESPACE = "zkStudy";

    public static CuratorFramework getClient() {
        // 重试策略
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 创建客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                // 命名空间,以后对于该客户端的所有操作都是基于此命名空间的
                .namespace(NAMESPACE)
                .build();
        // 创建会话
        client.start();
        return client;
    }

    // 同步操作
    private static void crud() throws Exception {
        CuratorFramework client = getClient();
//        // 创建一个结点,默认是持久结点,内容是空
//        String forPath = client.create().forPath("/test4");
//        System.out.println(forPath);
//        // 创建一个持久结点,指定内容
//        String path = client.create().forPath("/test2", "你好".getBytes("UTF-8"));
//        System.out.println(path);

//        // 创建临时结点
//        String ephemeral = client.create().withMode(CreateMode.EPHEMERAL).forPath("/ephemeral", "ephemeral".getBytes());
//        byte[] bytes = client.getData().forPath(ephemeral);
//        System.out.println(new String(bytes));

//        // 创建临时结点,若其父结点不存在的话那么会创建父亲节点,
//        // 又,zk规定非叶子节点一定是持久结点,所以其父亲节点都是持久节点
//        String forPath = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/a/b/c", "abcd".getBytes());
//        System.out.println(forPath);
//        byte[] bytes1 = client.getData().forPath(forPath);
//        System.out.println(new String(bytes1));
//

//        // 删除节点,只能删除叶子节点
//        client.delete().forPath("/test");

//        // 删除一个结点及其子节点
//        client.delete().deletingChildrenIfNeeded().forPath("/a");

//        // 保证删除一个结点
//        client.delete().guaranteed().forPath("/test2");


        // 更新结点数据
        Stat stat = client.setData().forPath("/test4");
        System.out.println(stat);
        client.setData().forPath("/test4", "aaaaaaa".getBytes());
    }


    public static void main(String[] args) throws Exception {
//        final CountDownLatch semphore = new CountDownLatch(2);
//
//        CuratorFramework client = getClient();
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
//            @Override
//            public void processResult(CuratorFramework client, CuratorEvent event) {
//                System.out.println(Thread.currentThread().getName() + " callback1.........." + event);
//                semphore.countDown();
//            }
//        }, Executors.newFixedThreadPool(2)).forPath("/a/b/c", "init".getBytes());
//
//
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
//            @Override
//            public void processResult(CuratorFramework client, CuratorEvent event) {
//                System.out.println(Thread.currentThread().getName() + " callback2......................" + event);
//                semphore.countDown();
//            }
//        }).forPath("/c/d/e", "init".getBytes());
//
//        System.out.println("Wait......");
//        semphore.await();
//        System.out.println("Done.");

        CuratorFramework client = getClient();
        byte[] bytes = client.getData().forPath("/c");
        System.out.println(new String(bytes));

        Stat stat = client.checkExists().forPath("/a/b");
        System.out.println(stat);
    }
}
