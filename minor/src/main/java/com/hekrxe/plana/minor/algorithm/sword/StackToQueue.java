package com.hekrxe.plana.minor.algorithm.sword;

import java.util.Stack;

/**
 * User: tanhuayou
 * Date: 2018/9/3
 */
public class StackToQueue<E> {
    private Stack<E> push = new Stack<>();
    private Stack<E> pop = new Stack<>();
    private int size = 0;

    public void push(E e) {
        push.push(e);
        size++;
    }

    public E pop() {
        if (pop.empty()) {
            while (!push.empty()) {
                pop.push(push.pop());
            }
        }
        if (!pop.empty()) {
            size--;
            return pop.pop();
        }
        return null;
    }

    public boolean isEmpty() {
        return push.isEmpty() && pop.isEmpty();
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        StackToQueue<Integer> queue = new StackToQueue<>();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push(4);
        queue.push(5);
        System.out.println(queue.pop());

        queue.push(6);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());

    }

}
