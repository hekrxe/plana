package com.hekrxe.plana.minor.algorithm;

/**
 * User: tanhuayou
 * Date: 2018/7/19
 */
public class TreeMap<K extends Comparable<K>, V> {


    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Entry<K extends Comparable<K>, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;
        boolean color = BLACK;

        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }


    private transient Entry<K, V> root;

    public V put(K key, V value) {
        Entry<K, V> t = root;
        if (null == t) {
            root = new Entry<>(key, value, null);
            return null;
        }
        TreeMap.Entry<K, V> parent;
        int cmp;
        do {
            parent = t;
            cmp = key.compareTo(t.key);
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                return t.value = value;
            }
        } while (t != null);

        Entry<K, V> newNode = new Entry<>(key, value, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        fixAfterInsertion(newNode);
        return null;
    }


    private void fixAfterInsertion(Entry<K, V> x) {
        x.color = RED;
        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                //      /
                //     R
                //    /
                //   xR
                Entry<K, V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    //      /
                    //     / \
                    //    R   R
                    //   /
                    //  xR
                    setColor(y, BLACK);
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                    //       /
                    //      R <- rec(loop)
                    //     / \
                    //    B   B
                    //   /
                    //  xR
                } else {
                    //      /
                    //     / \
                    //    R   B(Nil)
                    //     \
                    //     xR
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                        //      /
                        //     / \
                        //    xR  B(Nil)
                        //   /
                        //  R <- x
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    //       /
                    //      R
                    //     / \
                    //    Bx  B(Nil)
                    //   /
                    //  R <- x
                    rotateRight(parentOf(parentOf(x)));
                    //     /
                    //    Bx
                    //   /  \
                    //  R   R
                    //        \
                    //         B(Nil)
                }
            } else {
                //
            }
        }

        root.color = BLACK;
    }


    private void rotateLeft(Entry<K, V> p) {
        if (p != null) {
            Entry<K, V> r = p.right;
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            r.parent = p.parent;
            if (p.parent == null) {
                root = r;
            } else if (p.parent.left == p) {
                p.parent.left = r;
            } else {
                p.parent.right = r;
            }
            r.left = p;
            p.parent = r;
        }
    }

    private void rotateRight(Entry<K, V> p) {
        if (p != null) {
            Entry<K, V> l = p.left;
            p.left = l.right;
            if (l.right != null) {
                l.right.parent = p;
            }
            l.parent = p.parent;
            if (p.parent == null) {
                root = l;
            } else if (p.parent.right == p) {
                p.parent.right = l;
            } else {
                p.parent.left = l;
            }
            l.right = p;
            p.parent = l;
        }
    }

    private static <K extends Comparable<K>, V> Entry<K, V> parentOf(Entry<K, V> p) {
        return (p == null ? null : p.parent);
    }

    private static <K extends Comparable<K>, V> Entry<K, V> leftOf(Entry<K, V> p) {
        return (p == null) ? null : p.left;
    }

    private static <K extends Comparable<K>, V> Entry<K, V> rightOf(Entry<K, V> p) {
        return (p == null) ? null : p.right;
    }

    private static <K extends Comparable<K>, V> boolean colorOf(Entry<K, V> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <K extends Comparable<K>, V> void setColor(Entry<K, V> p, boolean c) {
        if (p != null) {
            p.color = c;
        }
    }

    public static void main(String[] args) {
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("aa", "sss");
    }
}
