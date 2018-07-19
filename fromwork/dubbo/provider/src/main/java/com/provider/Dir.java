package com.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;

/**
 * User: tanhuayou
 * Date: 2018/7/6
 */
public class Dir {
    public static void main(String[] args) throws IOException {
        load("META-INF/dubbo/internal/com.alibaba.dubbo.cache.CacheFactory");
    }

    private static void load(String dir) throws IOException {
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(dir);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
