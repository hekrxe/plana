package com.hekrxe.plana.minor.design.factory;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class CPUFactory {
    public interface CPUType {
        int AMD = 0;
        int INTEL = 1;
    }

    public static CPU createCPU(int type) {
        CPU cpu;
        switch (type) {
            case CPUType.INTEL:
                cpu = new IntelCPU();
                break;
            default:
                cpu = new AmdCPU();
        }
        return cpu;
    }
}
