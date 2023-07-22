import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[] seq;
    static int cnt;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[N+1];
        seq = new int[N+1];

        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }


        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 1; i<=N; i++) {
            graph.get(i).sort(Collections.reverseOrder());
        }

        seq[start] = 1;
        visited[start] = true;
        cnt = 2;

        dfs(start);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++) {
            sb.append(seq[i]).append("\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int start) {

        for(int v : graph.get(start)) {
                if(!visited[v]){
                    visited[v] = true;
                    seq[v] = cnt++;
                    dfs(v);
            }
        }
    }
}