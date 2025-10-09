import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        int max_dis = 0;
        
        List<List<Integer>> graph = new ArrayList<>();
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i++) {
            int v = edge[i][0];
            int w = edge[i][1];
            
            graph.get(v).add(w);
            graph.get(w).add(v);
        }

        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(1);
        
        while(!queue.isEmpty()) {
            int start = queue.poll();
            visited[start] = true;
            
            for(int node : graph.get(start)) {
                if(!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                    dist[node] = dist[start] + 1;
                    max_dis = Math.max(max_dis, dist[node]);
                }
            }
        }
        
        for(int i = 1; i < n+1; i++) {
            if(max_dis == dist[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}