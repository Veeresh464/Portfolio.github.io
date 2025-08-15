import java.util.*;

class MaxFlow {

    static final int N = 6; // number of nodes

    // BFS to find if there's an augmenting path from src to sink
    boolean bfs(int[][] res, int src, int sink, int[] par) {
        boolean[] vis = new boolean[N];
        Queue<Integer> q = new LinkedList<>();

        q.add(src);
        vis[src] = true;
        par[src] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < N; v++) {
                if (!vis[v] && res[u][v] > 0) {
                    par[v] = u;
                    vis[v] = true;
                    if (v == sink)
                        return true;
                    q.add(v);
                }
            }
        }
        return false;
    }

    // Ford-Fulkerson algorithm
    int maxFlow(int[][] cap, int src, int sink) {
        int[][] res = new int[N][N];
        for (int i = 0; i < N; i++)
            res[i] = Arrays.copyOf(cap[i], N);

        int[] par = new int[N];
        int flow = 0;

        while (bfs(res, src, sink, par)) {
            int minCap = Integer.MAX_VALUE;

            for (int v = sink; v != src; v = par[v]) {
                int u = par[v];
                minCap = Math.min(minCap, res[u][v]);
            }

            for (int v = sink; v != src; v = par[v]) {
                int u = par[v];
                res[u][v] -= minCap;
                res[v][u] += minCap;
            }

            flow += minCap;
        }
        return flow;
    }

    public static void main(String[] args) {
        int[][] cap = {
            { 0, 16, 13, 0, 0, 0 },
            { 0, 0, 10, 12, 0, 0 },
            { 0, 4, 0, 0, 14, 0 },
            { 0, 0, 9, 0, 0, 20 },
            { 0, 0, 0, 7, 0, 4 },
            { 0, 0, 0, 0, 0, 0 }
        };

        MaxFlow mf = new MaxFlow();
        System.out.println("Max Flow: " + mf.maxFlow(cap, 0, 5));
    }
}
