package com.hekrxe.plana.minor.design.factory;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class ComputerFactory {

    public static Computer createAmdComputer() {
        CPU amdCPU = CPUFactory.createCPU(CPUFactory.CPUType.AMD);
        MainBoard amdMainBoard = MainBoardFactory.getMainboard(MainBoardFactory.MainBoardType.AMD);
        return new Computer(amdCPU, amdMainBoard);
    }

    public static Computer createIntelComputer() {
        CPU intelCPU = CPUFactory.createCPU(CPUFactory.CPUType.INTEL);
        MainBoard intelMainboard = MainBoardFactory.getMainboard(MainBoardFactory.MainBoardType.INTEL);
        return new Computer(intelCPU, intelMainboard);
    }
}
