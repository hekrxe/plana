package com.hekrxe.plana.minor.algorithm.sword;

import java.util.LinkedList;
import java.util.Queue;

/**
 * User: tanhuayou
 * Date: 2018/9/3
 */
public class QueueToStack<E> {
    private Queue<E> queue1 = new LinkedList<>();
    private Queue<E> queue2 = new LinkedList<>();

    // 1 2 3
    // 3 2 1

    public void push(E e) {
        if (!queue1.isEmpty()) {
            queue1.add(e);
        } else {
            queue2.add(e);
        }
    }

    public E pop() {
        if (!queue1.isEmpty() || !queue2.isEmpty()) {
            if (!queue2.isEmpty()) {
                while (queue2.size() > 1) {
                    queue1.add(queue2.remove());
                }
                return queue2.remove();
            } else {
                while (queue1.size() > 1) {
                    queue2.add(queue1.remove());
                }
                return queue1.remove();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        QueueToStack<Integer> stack = new QueueToStack<>();
        stack.push(1);
        stack.push(2);
        // 1 2
        System.out.println(stack.pop());
        stack.push(3);
        stack.push(4);
        stack.push(5);
        // 1 3 4 5
        System.out.println(stack.pop());
        stack.push(6);
        // 1 3 4 6
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
