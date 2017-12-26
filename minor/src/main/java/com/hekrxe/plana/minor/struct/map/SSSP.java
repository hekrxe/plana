package com.hekrxe.plana.minor.struct.map;

/**
 * 单源最短路径SSSP(single source shortest path)
 *
 * @author hztanhuayou
 * @date 2017/11/27
 */
public final class SSSP {
    private static final int MAX = Integer.MAX_VALUE / 2;


    /**
     * dijkstra 算法:
     * 求从一个顶点到其余各顶点的最短路径
     * </P
     * 置两个集合A,B
     * 其中A集合是结果,其初始值为空
     * B集合是原图
     * <p/>
     * 从起点start开始,找到一条最短路径,将该路径加入A集合;
     * 这时,找到从A集合中的所有起点开始找出一条最短路径加入A集合
     * </p>
     * 重复以上步骤,直至B集合为空
     *
     * @param map        有向联通图,map[i][j]=x 表示从i到j的路径为x
     * @param nodeNumber 节点个数
     * @param start      起点
     */
    public static int[] dijkstra(int[][] map, int nodeNumber, int start) {
        // 表示从start到各节点的最短路径
        int[] dist = new int[nodeNumber];
        // 表示i节点是否已经被加入到A集合
        boolean[] hit = new boolean[nodeNumber];
        for (int i = 0; i < nodeNumber; ++i) {
            dist[i] = map[start][i];
            hit[i] = false;
        }
        hit[start] = true;
        for (int i = 0; i < nodeNumber; ++i) {
            int begin = start;
            // 从begin节点开始先找到一个最短路径
            for (int j = 0; j < nodeNumber; ++j) {
                if (!hit[j] && dist[j] < dist[begin]) {
                    begin = j;
                }
            }
            // 此时已经找到一条最短边了,加入A集合
            hit[begin] = true;
            // 更新到k节点的最短路径
            for (int k = 0; k < nodeNumber; ++k) {
                if (!hit[k]) {
                    int distance = map[start][begin] + map[begin][k];
                    if (distance < dist[k]) {
                        dist[k] = distance;
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] map = {
                {MAX, MAX, 10, MAX, 30, 100},
                {MAX, MAX, 5, MAX, MAX, MAX},
                {MAX, MAX, MAX, 50, MAX, MAX},
                {MAX, MAX, MAX, MAX, MAX, 10},
                {MAX, MAX, MAX, 20, MAX, 60},
                {MAX, MAX, MAX, MAX, MAX, MAX}
        };
        int[] dijkstra = dijkstra(map, 6, 0);
        for (int i : dijkstra) {
            System.out.println(i);
        }
    }

}
