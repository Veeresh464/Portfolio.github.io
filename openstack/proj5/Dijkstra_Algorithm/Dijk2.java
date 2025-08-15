package Dijkstra_Algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijk2 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0; i<5; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1,30));
        adj.get(0).add(new Pair(4,5));
        adj.get(0).add(new Pair(3,20));

        adj.get(1).add(new Pair(0,30));
        adj.get(1).add(new Pair(2,4));

        adj.get(2).add(new Pair(1,4));
        adj.get(2).add(new Pair(3,7));
        adj.get(2).add(new Pair(4,8));

        adj.get(3).add(new Pair(0,20));
        adj.get(3).add(new Pair(2,7));
        adj.get(3).add(new Pair(4,6));

        adj.get(4).add(new Pair(0,5));
        adj.get(4).add(new Pair(2,8));
        adj.get(4).add(new Pair(3,6));
        //------------------creating the adj matrix---------------------

        int n = adj.size();
        int cost[] = new int [n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        int src = 0;
        algorithm(adj, cost, src);

        for(int i=0; i<n; i++){
            System.out.println(cost[i]);
        }
    }

    static void algorithm(ArrayList<ArrayList<Pair>> adj, int[] cost, int src){
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.getWt()-b.getWt());
        pq.add(new Pair(src, 0));
        cost[src] = 0;

        while(!pq.isEmpty()){
            Pair res = pq.poll();

            for(Pair neighbour : adj.get(res.getVal())){
                if(res.getWt() + neighbour.getWt() < cost[neighbour.getVal()]){
                    cost[neighbour.getVal()] = res.getWt() + neighbour.getWt();
                    pq.add(new Pair(neighbour.getVal(), res.getWt() + neighbour.getWt()));
                }
            }
        }

    }


}
