package com.hekrxe.plana.minor.design.factory;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Client {
    public static void main(String[] args) {
        ComputerFactory.createAmdComputer().display();
        System.out.println("==================================");
        ComputerFactory.createIntelComputer().display();
    }
}
