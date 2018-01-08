package com.hekrxe.plana.minor.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author hztanhuayou
 * @date 2017/12/27
 */
public class BinaryTree<V> {
    static class Node<V> {
        Node<V> left;
        Node<V> right;
        Node<V> parent;
        V value;
    }

    private Node<V> EMPTY_NODE = new Node<>();

    private Random random = new Random();
    private Node<V> root;

    public BinaryTree() {
    }

    public void put(V value) {
        Node<V> newNode = new Node<>();
        newNode.value = value;
        if (null == root) {
            this.root = newNode;
            newNode.parent = root;
            return;
        }
        Node<V> tree = root;
        while (null != tree.left || null != tree.right) {
            if (null != tree.left && null != tree.right) {
                if (shuffle()) {
                    tree = tree.left;
                } else {
                    tree = tree.right;
                }
            } else {
                break;
            }
        }
        if (null == tree.left && null == tree.right) {
            if (shuffle()) {
                tree.left = newNode;
            } else {
                tree.right = newNode;
            }
        } else {
            if (null == tree.left) {
                tree.left = newNode;
            } else {
                tree.right = newNode;
            }
        }
        newNode.parent = tree;
    }


    public void display() {
        if (null == root) {
            System.out.println("Null for root");
        }
        Queue<Node<V>> queue = new LinkedList<>();
        queue.add(root);

        Node<V> up = root;
        Node<V> down = root;

        while (!queue.isEmpty()) {
            Node<V> head = queue.poll();
            System.out.print(head.value + "(" + head.parent.value + "),\t");
            if (EMPTY_NODE != head) {
                if (null != head.left) {
                    queue.add(head.left);
                    down = head.left;
                }
                if (null != head.right) {
                    queue.add(head.right);
                    down = head.right;
                }
            }
            if (up == head) {
                System.out.println();
                up = down;
            }
        }
    }

    private boolean shuffle() {
        return Math.abs(random.nextInt()) % 100 <= 50;
    }


    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int size = 13;
        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            tree.put(Math.abs(random.nextInt() % 100));
        }
        tree.display();
        System.out.println("\n");
        tree.display();
    }
}
