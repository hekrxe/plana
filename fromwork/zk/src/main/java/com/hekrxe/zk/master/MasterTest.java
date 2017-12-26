package com.hekrxe.zk.master;

import com.hekrxe.zk.minor.MinorTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

/**
 * 选择一个根节点,如: /master,多台机器同时向该节点创建一个子节点如: /master/lock
 * 利用zk的特性,最终只有一台机器能够创建成功,成功的那一台便是master.
 *
 * @author hztanhuayou
 * @date 2017/12/20
 */
public class MasterTest {
    private static String MASTER_PATH = "/master";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = MinorTest.getClient();

        LeaderSelector leaderSelector = new LeaderSelector(client, MASTER_PATH, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("Master....");
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();

        Thread.sleep(4000);
    }
}
