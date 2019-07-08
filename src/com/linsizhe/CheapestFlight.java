package com.linsizhe;
// https://leetcode.com/problems/cheapest-flights-within-k-stops
class CheapestFlight {
    // Dijkstra algorithm not working for K constain!!!!!
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] toSrc = new int[n];
        // build distance matrix
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -1;
            }
        }
        for (int[] flight : flights) {
            matrix[flight[0]][flight[1]] = flight[2];
        }

        for (int i = 0; i < n; i++) {
            if (i != src) {
                toSrc[i] = Integer.MAX_VALUE;
            } else {
                toSrc[i] = 0;
            }
        }
        boolean[] include = new boolean[n];
        int[] stops = new int[n];
        for (int i = 0; i < n; i ++) {
            int out = updateToSrc(toSrc, include, n, matrix, stops, K);
            if (out == dst && stops[out] <= K) {
                return toSrc[out];
            }
        }
        return -1;
    }

    // pick smallest one to src distance that is not yet included.
    // Update all its connection toSrc.
    // return that smallest node.
    public int updateToSrc(int[] toSrc, boolean[] include, int n, int[][] matrix, int[] stops, int k) {
        int minToSrc = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < n; i++) {
            if (!include[i] && toSrc[i] != Integer.MAX_VALUE && toSrc[i] < minToSrc) {
                minToSrc = toSrc[i];
                minIdx = i;
            }
        }
        include[minIdx] = true;
        // update other nodes distance to minIdx;
        int[] connects = matrix[minIdx];
        for (int i = 0; i < connects.length; i++) {
            if (connects[i] != -1 && !include[i]) {
                toSrc[i] = minToSrc + connects[i];
                stops[i] = stops[minIdx] + 1;
            }
        }
        return minIdx;
    }

    // Bellman ford algorithm. O(UV)
    //  It first calculates the shortest distances which have at-most one edge in the path. Then, it calculates shortest paths with at-most 2 edges,
    // and so on. After the i-th iteration of outer loop, the shortest paths with at most i edges are calculated
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == src) {
                dist[i] = 0;
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < K + 1; i++) {
            int[] newDist = new int[n];
            // Key point 1: new path always from last iteration so stop is at most +1.
            System.arraycopy(dist, 0, newDist, 0, n);
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int uv = flight[2];
                // dist[] here is from last iteration. Should not update it.
                // key point 2 is that we use newDist[v] > distp[u] + uv so for cases of u1v u2v u3v... we will keep shortest! important!
                // Also note that u1v u2v still from last iteration
                // think about dist[u] + uv as a new path, keep new path from last iteration so this iteration will
                // only advice stops by one.
                if (dist[u] != Integer.MAX_VALUE && newDist[v] > dist[u] + uv) {
                    newDist[v] = dist[u] + uv;
                }
            }
            dist = newDist;
        }

        // For Bellman ford. do check newDist[v] > dist[u] + uv one more time. If any occurs true, then it contains negative cycle.
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        CheapestFlight cf = new CheapestFlight();
        System.out.println(cf.findCheapestPrice2(n, flights, src, dst, k));
    }
}