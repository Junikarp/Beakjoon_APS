import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static StringBuilder sb = new StringBuilder();
    static int[] edge;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        edge = new int[N+1];

        for(int i = 0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            edge[v]++;
        }

        for(int i = 1; i < N+1; i++) {
            if(edge[i] == 0) {
                queue.offer(i);
            }
        }

        topo();

        System.out.println(sb);
    }

    public static void topo() {
        while(!queue.isEmpty()) {
            int now = queue.poll();

            sb.append(now).append(" ");

            for(int v : graph.get(now)) {
                edge[v]--;
                if(edge[v] == 0) {
                    queue.offer(v);
                }
            }
        }
    }
}
