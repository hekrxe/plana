package com.hekrxe.plana.minor.algorithm.sword;

import java.util.Stack;

/**
 * User: tanhuayou
 * Date: 2018/9/4
 */
public class MiniStack {

    private Stack<Integer> main = new Stack<>();
    private Stack<Integer> min = new Stack<>();


    public void push(Integer e) {
        int size = min.size();
        if (size <= 0) {
            min.push(e);
        } else {
            Integer peek = min.peek();
            min.push(Math.min(peek, e));
        }

        main.push(e);
    }

    public Integer pop() {
        min.pop();
        return main.pop();
    }

    public Integer min() {
        return min.peek();
    }

    public static void main(String[] args) {
        MiniStack stack = new MiniStack();

        stack.push(4);
        System.out.println(stack.min());
        stack.push(2);
        stack.pop();
        stack.push(3);
        stack.pop();
        System.out.println(stack.min());
        stack.push(1);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        stack.push(8);
        stack.pop();
        System.out.println(stack.min());
    }
}
