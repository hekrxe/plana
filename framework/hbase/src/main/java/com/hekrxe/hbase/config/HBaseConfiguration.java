
package com.hekrxe.hbase.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * @author tanhuayou on 2019/10/13
 */
@PropertySource(value = "classpath:config/my.properties")
@Configuration
public class HBaseConfiguration {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${hbase.zookeeper.quorum}")
    private String zookeeperQuorum;

    @Value("${hbase.zookeeper.property.clientPort}")
    private String clientPort;

    @Value("${hbase.zookeeper.znode.parent}")
    private String znodeParent;

    @Bean
    public HbaseTemplate hbaseTemplate() {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("hbase.zookeeper.quorum", zookeeperQuorum);
        conf.set("hbase.zookeeper.property.clientPort", clientPort);
        conf.set("zookeeper.znode.parent", znodeParent);
        logger.info(zookeeperQuorum);
        logger.info(clientPort);
        logger.info(znodeParent);
        return new HbaseTemplate(conf);
    }
}
