package com.pa.jvm.mem;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms堆最小值
 * -Xmx堆最大值
 * 若Xms与Xmx一样即可避免堆自动扩展
 * <p>
 * -XX +HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存溢出时Dump出当前的内存堆转储快照
 * <p>
 * -XX:PermSize 设置持久代(perm gen)初始值
 * -XX:MaxPermSize 设置持久代最大值
 * <p>
 * User: tanhuayou
 * Date: 2018/11/12
 */
public class JavaHeapOutOfMemoryTest {
    /**
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<>();
        while (true) {
            list.add(new HeapOOM());
        }
    }

    static class HeapOOM {
    }
}

