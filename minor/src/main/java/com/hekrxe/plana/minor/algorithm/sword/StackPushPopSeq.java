package com.hekrxe.plana.minor.algorithm.sword;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * User: tanhuayou
 * Date: 2018/9/4
 */
public class StackPushPopSeq {

    public static boolean isPopSeq(List<Integer> push, List<Integer> pop) {
        if (null == push || null == pop) {
            return null == push && null == pop;
        }

        if (push.size() != pop.size()) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        Iterator<Integer> ite = push.iterator();
        for (Integer p : pop) {
            while ((stack.isEmpty() || !p.equals(stack.peek())) && ite.hasNext()) {
                stack.push(ite.next());
                ite.remove();
            }
            if (p.equals(stack.peek())) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        List<Integer> push = new ArrayList<>();
        push.add(1);
//        push.add(2);
//        push.add(3);
//        push.add(4);
//        push.add(5);

        List<Integer> pop = new ArrayList<>();
        System.out.println(isPopSeq(push, pop));
//        pop.add(4);
//        pop.add(5);
//        pop.add(3);
//        pop.add(2);
        pop.add(2);


        System.out.println(isPopSeq(push, pop));

    }
}
