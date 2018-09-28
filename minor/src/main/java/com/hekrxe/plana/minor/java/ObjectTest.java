package com.hekrxe.plana.minor.java;

/**
 * 1,调用某个对象的wait()方法能让当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）
 * 2,调用某个对象的notify()方法能够唤醒一个正在等待这个对象的monitor的线程，
 * 如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程；
 * 3,调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程；
 * <p>
 * 如果调用某个对象的wait()方法，当前线程必须拥有这个对象的monitor（即锁），
 * 因此调用wait()方法必须在同步块或者同步方法中进行（synchronized块或者synchronized方法）。
 * 调用某个对象的wait()方法，相当于让当前线程交出此对象的monitor，然后进入等待状态，
 * 等待后续再次获得此对象的锁（Thread类中的sleep方法使当前线程暂停执行一段时间，从而让其他线程有机会继续执行，但它并不释放对象锁）
 * <p>
 * 假如有三个线程Thread1、Thread2和Thread3都在等待对象objectA的monitor，
 * 此时Thread4拥有对象objectA的monitor，当在Thread4中调用objectA.notify()方法之后，
 * Thread1、Thread2和Thread3只有一个能被唤醒。注意，被唤醒不等于立刻就获取了objectA的monitor。
 * 假若在Thread4中调用objectA.notifyAll()方法，则Thread1、Thread2和Thread3三个线程都会被唤醒，
 * 至于哪个线程接下来能够获取到objectA的monitor就具体依赖于操作系统的调度了。
 * 上面尤其要注意一点，一个线程被唤醒不代表立即获取了对象的monitor，
 * 只有等调用完notify()或者notifyAll()并退出synchronized块，释放对象锁后，其余线程才可获得锁执行。
 * <p>
 * 以上来自: https://www.cnblogs.com/csuwater/p/5411693.html
 *
 * @author hztanhuayou
 * @date 2017/12/21
 */
public class ObjectTest {
    public static void main(String[] args) throws InterruptedException {

        final Object monitor = new Object();

        final int[] sum1 = {0};
        final int[] sum2 = {0};

        for (int i = 0; i < 1000; ++i) {
            final int j = i;
            new Thread() {
                @Override
                public void run() {
                    System.out.println(j + " start");
                    try {
                        synchronized (monitor) { // 拥有此对象的锁(monitor)
                            monitor.wait(); // 才能使用
                            System.out.println(j + " end");
                            sum1[0]++;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum2[0]++;
                }
            }.start();
        }
        Thread.sleep(5000);
        System.out.println("Main...");
        synchronized (monitor) {
            monitor.notify();
            System.out.println("notify");
        }
        System.out.println(3000);
        synchronized (monitor) {
            monitor.notifyAll();
        }
        Thread.sleep(5000);
        System.out.println("End");
        System.out.println("sum1=" + sum1[0] + "  sum2=" + sum2[0]);
    }
}
