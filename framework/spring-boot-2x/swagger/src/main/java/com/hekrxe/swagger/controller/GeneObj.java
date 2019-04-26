package com.hekrxe.swagger.controller;

import java.io.Serializable;

/**
 * @author tanhuayou on 2019/04/12
 */
public class GeneObj<T> implements Serializable {
    private T data;
    private int code;
}
