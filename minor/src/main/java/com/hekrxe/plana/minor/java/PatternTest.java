package com.hekrxe.plana.minor.java;

/**
 * User: tanhuayou
 * Date: 2018/6/28
 */
public class PatternTest {
    public static void main(String[] args) {
        String str = "patternTestMainUpperCaseLowerCase";
        System.out.println(str.replaceAll("([A-Z]+)", "_$1").toLowerCase());
        System.out.println(str.replaceAll("\\*","${C}"));
    }
}
