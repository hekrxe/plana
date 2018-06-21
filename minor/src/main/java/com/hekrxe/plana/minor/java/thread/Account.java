package com.hekrxe.plana.minor.java.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: tanhuayou
 * Date: 2018/5/26
 */
public class Account {
    // volatile 关键字修饰的字段 不要依赖于原值 不建议 -= += 等操作

    private int balance = 1000000;
    private ReentrantLock lock = new ReentrantLock();

    public boolean deduction(int money) {
        if (balance < money) {
            return false;
        }
        lock.lock();
        try {
            balance -= money;
        } finally {
            lock.unlock();
        }
        return true;
    }

    public int getBalance() {
        return balance;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger sum = new AtomicInteger();
        Account account = new Account();
        Thread a = new Thread(() -> {
            while (account.deduction(500)) {
                sum.addAndGet(500);
                try {
                    // 模拟延迟
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            System.out.println("A余额不足：" + account.getBalance());
        });
        Thread b = new Thread(() -> {
            while (account.deduction(300)) {
                sum.addAndGet(300);
                try {
                    // 模拟延迟
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            System.out.println("B余额不足：" + account.getBalance());
        });
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(sum.get());
    }
}
