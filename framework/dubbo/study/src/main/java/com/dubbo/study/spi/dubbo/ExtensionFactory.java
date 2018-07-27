package com.dubbo.study.spi.dubbo;

/**
 * User: tanhuayou
 * Date: 2018/7/6
 */
@SPI
public interface ExtensionFactory {

    <T> T getExtension(Class<T> type, String name);
}
