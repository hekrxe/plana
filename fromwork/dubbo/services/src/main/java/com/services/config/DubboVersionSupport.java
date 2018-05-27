package com.services.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author hztanhuayou
 * 2018/2/11
 */
final class DubboVersionSupport {
    private static List<Properties> PROPERTIES = new ArrayList<>();
    private static List<String> SOURCES = Collections.singletonList("classpath:config/dubbo.version.properties");

    static {
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            for (String source : SOURCES) {
                PROPERTIES.add(PropertiesLoaderUtils.loadProperties(loader.getResource(source)));
            }
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(DubboVersionSupport.class);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static String fetch(String key) {
        for (Properties property : PROPERTIES) {
            String value = property.getProperty(key);
            if (null != value) {
                return value;
            }
        }
        return null;
    }
}
