package com.hekrxe.plana.minor.design.factory;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class MainBoardFactory {
    public interface MainBoardType {
        int AMD = 0;
        int INTEL = 1;
    }

    public static MainBoard getMainboard(int type) {
        MainBoard mainBoard;
        switch (type) {
            case MainBoardType.INTEL:
                mainBoard = new IntelMainBoard();
                break;
            default:
                mainBoard = new AmdMainBoard();
        }
        return mainBoard;
    }
}
