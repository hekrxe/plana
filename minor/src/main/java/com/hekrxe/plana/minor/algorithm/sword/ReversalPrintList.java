package com.hekrxe.plana.minor.algorithm.sword;

/**
 * User: tanhuayou
 * Date: 2018/9/3
 */
public class ReversalPrintList {

    public static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }


    // 递归写法  非递归写的话借助栈
    public static void reversalPrint(Node head) {
        if (null == head) return;
        reversalPrint(head.next);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        Node node4 = new Node(4, node3);
        Node node5 = new Node(5, node4);
        Node node6 = new Node(6, node5);

        reversalPrint(node6);
    }
}
