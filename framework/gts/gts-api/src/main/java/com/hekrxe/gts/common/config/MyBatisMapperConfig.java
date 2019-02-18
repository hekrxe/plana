package com.hekrxe.gts.common.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hztanhuayou
 * 2017/12/4
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
class MyBatisMapperConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.hekrxe.gts.common.dao");
        return mapperScannerConfigurer;
    }
}
