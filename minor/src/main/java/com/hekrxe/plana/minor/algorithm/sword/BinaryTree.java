package com.hekrxe.plana.minor.algorithm.sword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * User: tanhuayou
 * Date: 2018/9/3
 */
public class BinaryTree {


    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
            left = null;
            right = null;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(Node other) {
            this(other.val);
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }


    public static void preRec(Node root) {
        if (null != root) {
            System.out.print(root.val + ", ");
            preRec(root.left);
            preRec(root.right);
        }
    }

    public static void midRec(Node root) {
        if (null != root) {
            midRec(root.left);
            System.out.print(root.val + ", ");
            midRec(root.right);
        }
    }


    public static void lastRec(Node root) {
        if (null != root) {
            lastRec(root.left);
            lastRec(root.right);
            System.out.print(root.val + ", ");
        }
    }

    public static List<Node> pre(Node root) {
        List<Node> seq = new ArrayList<>();

        Stack<Node> stack = new Stack<>();

        Node node = root;

        while (null != node || !stack.empty()) {

            if (null != node) {
                stack.push(node);
                seq.add(node);
                node = node.left;
            } else {
                Node first = stack.pop();
                node = first.right;
            }
        }

        System.out.println("pre: ");
        System.out.print("[");
        preRec(root);
        System.out.println("]");
        System.out.println(Arrays.toString(seq.toArray()));

        return seq;
    }


    public static List<Node> mid(Node root) {
        List<Node> seq = new ArrayList<>();

        Stack<Node> stack = new Stack<>();

        Node node = root;

        while (null != node || !stack.empty()) {
            if (null != node) {
                stack.push(node);
                node = node.left;
            } else {
                Node pop = stack.pop();
                seq.add(pop);
                node = pop.right;
            }
        }

        System.out.println("mid: ");
        System.out.print("[");
        midRec(root);
        System.out.println("]");
        System.out.println(Arrays.toString(seq.toArray()));

        return seq;
    }


    public static List<Node> last(Node root) {
        List<Node> seq = new ArrayList<>();

        Stack<Node> stack = new Stack<>();

        Node node = root;

        // 右子树输出后才会输出根
        Node preDisplay = null;
        while (null != node || !stack.empty()) {
            if (null != node) {
                stack.push(node);
                node = node.left;
            } else {
                Node peek = stack.peek();
                if (preDisplay == peek.right || null == peek.right) {
                    seq.add(peek);
                    preDisplay = peek;
                    stack.pop();
                } else {
                    // 右子树还没输出过的话 父亲还不能出栈
                    node = peek.right;
                }
            }
        }

        System.out.println("last: ");
        System.out.print("[");
        lastRec(root);
        System.out.println("]");
        System.out.println(Arrays.toString(seq.toArray()));

        return seq;
    }

    // [10, 6, 4, 8, 14, 12, 16]
    // [4, 6, 8, 10, 12, 14, 16]
    public static Node buildByPreAndMid(List<Node> preList, List<Node> midList) {
        if (null == preList || null == midList || preList.size() <= 0 || midList.size() <= 0) {
            return null;
        }

        Node root = new Node(preList.get(0).val);

        int i = indexOf(midList, root.val);


        List<Node> left = midList.subList(0, i);
        List<Node> right = midList.subList(i + 1, midList.size());

        int leftSize = left.size();

        root.left = buildByPreAndMid(preList.subList(1, leftSize + 1), left);
        root.right = buildByPreAndMid(preList.subList(leftSize + 1, preList.size()), right);

        return root;
    }

    private static int indexOf(List<Node> nodes, int val) {
        for (int i = 0; i < nodes.size(); ++i) {
            if (nodes.get(i).val == val) {
                return i;
            }
        }
        return -1;
    }

    private static void clearReference(List<Node> nodes) {
        for (Node node : nodes) {
            node.left = node.right = null;
        }
    }

    public static Node mirrorTree(Node node) {
        if (null == node) return null;
        if (null == node.right && null == node.left) return new Node(node);

        Node root = new Node(node);

        root.left = mirrorTree(node.right);
        root.right = mirrorTree(node.left);

        return root;
    }


    /**
     * 判断是不是一颗二叉搜索树的后序遍历序列
     * 后序遍历 那么根节点在最后
     * 左子树小于右子树的值
     */
    public static boolean isBST(List<Integer> lastOrder, int start, int end) {
        if (end == start) {
            // 只有一个结点
            return true;
        }
        Integer root = lastOrder.get(end);

        int i = start;
        while (lastOrder.get(i) < root) {
            i++;
        }

        for (int j = i; j < end; ++j) {
            if (lastOrder.get(j) < root) {
                return false;
            }
        }

        boolean left = true;
        if (i > 0) {
            left = isBST(lastOrder, start, i - 1);
        }
        boolean right = true;
        if (left && end > 0 && i < (end - 1)) {
            right = isBST(lastOrder, i, end - 1);
        }
        return left && right;
    }

    public static void pathOfSum(Node root, int expected) {
        if (null == root) {
            return;
        }
        Stack<Node> path = new Stack<>();
        pathOfSum(root, path, 0, expected);
        System.out.println("===");
    }

    public static void pathOfSum(Node root, Stack<Node> path, int cur, int expected) {
        cur += root.val;
        path.push(root);

        if (cur == expected && isLeaf(root)) {
            System.out.println(Arrays.toString(path.toArray()));
        }

        if (!isLeaf(root)) {
            if (null != root.left) {
                pathOfSum(root.left, path, cur, expected);
            }
            if (null != root.right) {
                pathOfSum(root.right, path, cur, expected);
            }
        }

        path.pop();
    }

    public static boolean isLeaf(Node node) {
        return null == node.left && null == node.right;
    }

    public static void main(String[] args) {
        pathOfSum(build(), 22);
    }


    public static List<Node> level(Node root) {
        List<Node> seq = new ArrayList<>();

        StackToQueue<Node> queue = new StackToQueue<>();
        queue.push(root);

        while (!queue.isEmpty()) {
            Node tmp = queue.pop();
            seq.add(tmp);
            if (null != tmp.left) {
                queue.push(tmp.left);
            }

            if (null != tmp.right) {
                queue.push(tmp.right);
            }
        }

        return seq;
    }

    private static Node build() {
        /*          10
                5        12
            4      7
         */

        Node root = new Node(10);

        Node node1 = new Node(5);
        Node node2 = new Node(12);

        Node node3 = new Node(4);
        Node node4 = new Node(7);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;


        return root;
    }

}
