package com.hekrxe.plana.minor.design.factory;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Computer {
    private CPU cpu;
    private MainBoard mainBoard;

    public Computer(CPU cpu, MainBoard mainBoard) {
        this.cpu = cpu;
        this.mainBoard = mainBoard;
    }

    public void display() {
        System.out.println(getClass().getSimpleName() + " info:");
        cpu.calculate();
        mainBoard.installCPU(cpu);
    }
}
