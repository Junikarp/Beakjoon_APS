import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main  {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] seq;
    static int cnt;

    public static void main (String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        seq = new int[N+1];

        graph = new ArrayList<>();

        for(int i=0; i<=N; ++i) {
            graph.add(new ArrayList<>());
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for(int i=1; i<graph.size(); i++){
            Collections.sort(graph.get(i));
        }

        cnt = 1;

        bfs(start);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<seq.length; i++) {
            sb.append(seq[i]).append("\n");
        }

        System.out.println(sb);
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        seq[start] = cnt;
        queue.offer(start);

        while(!queue.isEmpty()) {
            int Node = queue.poll();
            for(int i = 0; i < graph.get(Node).size(); i++) {
                int newNode = graph.get(Node).get(i);
                if(seq[newNode] == 0) {
                    cnt++;
                    seq[newNode] = cnt;
                    queue.offer(newNode);
                }
            }
        }
    }
}
