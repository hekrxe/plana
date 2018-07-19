package com.dubbo.study.spi.dubbo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: tanhuayou
 * Date: 2018/7/6
 */
public class ExtensionLoader<T> {
    private static final ConcurrentMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        if (null == type || !type.isInterface()) {
            throw new RuntimeException("Null or None-Interface for type");
        }
        if (!withExtensionAnnotation(type)) {
            throw new RuntimeException("SPI annotation");
        }
        ExtensionLoader<T> loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        if (null == loader) {
            EXTENSION_LOADERS.putIfAbsent(type, new ExtensionLoader<T>(type));
            loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        }
        return loader;
    }


    private static <T> boolean withExtensionAnnotation(Class<T> type) {
        return type.isAnnotationPresent(SPI.class);
    }

    private final Class<?> type;
    private final ExtensionFactory objectFactory;

    private ExtensionLoader(Class<?> type) {
        this.type = type;
        objectFactory = (ExtensionFactory.class == type ? null : ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension());
    }


    public T getAdaptiveExtension() {
        return null;
    }

}
