package com.hekrxe.plana.minor.java.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载阶段主要做：
 * 1，通过一个类的全限定名来获取定义此类的二进制字节流。
 * 2，将字节流代表的静态存储结构转化为方法区的运行时结构。方法区主要存放：已被加载的类信息，常量，静态变量。编译后的代码的运行时内存区域
 * 3，内存中生成一个代表这个类的Class对象，作为这个类在方法区的访问入口。这个Class对象并没有规定是在Java堆内存中，而是存放在方法区中。
 * <p>
 * 类“相等”判定条件：
 * 1、两个类来自同一个Class文件
 * 2、两个类是由同一个虚拟机加载
 * 3、两个类是由同一个类加载器加载;反之Class对象的
 * equals()
 * isAssignableFrom()
 * isInstance()
 * instanceof
 * 这些操作都是返回 false
 * <p>
 * 双亲委派：
 * 主要体现在ClassLoader的loadClass()方法中：
 * 先检查是否已经被加载过，若没有加载则调用父类加载器的loadClass()方法，
 * 若父类加载器为空则默认使用启动类加载器作为父类加载器。
 * 如果父类加载器加载失败，抛出ClassNotFoundException异常后，调用自己的findClass()方法进行加载。
 * User: tanhuayou
 * Date: 2018/6/22
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        Class<?> cClass = ClassLoaderTest.class.getClassLoader().loadClass("com.hekrxe.plana.minor.java.classloader.ClassLoaderTest");
        Object obj1 = cClass.newInstance();

        System.out.println("cClass.getName(): " + cClass.getName());
        System.out.println("cClass.equals(ClassLoaderTest.class): " + cClass.equals(ClassLoaderTest.class));
        System.out.println("cClass.isAssignableFrom(ClassLoaderTest.class):" + cClass.isAssignableFrom(ClassLoaderTest.class));
        System.out.println("cClass.isInstance(obj1): " + cClass.isInstance(obj1));
        System.out.println("obj1 instanceof ClassLoaderTest: " + (obj1 instanceof ClassLoaderTest));

        System.out.println("==================");
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("aaa");
        Object obj2 = aClass.newInstance();

        System.out.println("aClass.getName(): " + aClass.getName());
        System.out.println("aClass.equals(ClassLoaderTest.class): " + aClass.equals(ClassLoaderTest.class));
        System.out.println("aClass.isAssignableFrom(ClassLoaderTest.class):" + aClass.isAssignableFrom(ClassLoaderTest.class));
        System.out.println("aClass.isInstance(obj2): " + aClass.isInstance(obj2));
        System.out.println("obj2 instanceof ClassLoaderTest: " + (obj2 instanceof ClassLoaderTest));
        System.out.println(aClass == ClassLoaderTest.class);


    }

    private static class MyClassLoader extends ClassLoader {
        // 自己加载 ClassLoaderTest

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                InputStream inputStream = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("com.hekrxe.plana.minor.java.classloader".replaceAll("\\.", "/") + "/ClassLoaderTest.class");
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                return defineClass(b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
