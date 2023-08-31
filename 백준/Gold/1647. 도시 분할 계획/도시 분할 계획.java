import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;
    static int sum;
    static PriorityQueue<Integer> size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        size = new PriorityQueue<>();
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, cost));
            graph.get(v).add(new Node(u, cost));
        }

        prim(new Node(1, 0));

        int s = size.size();

        for (int i = 0; i < s - 1; i++) {
            sum += size.poll();
        }

        System.out.println(sum);
    }

    public static void prim(Node start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);
        sum = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (visited[now.v]) {
                continue;
            }

            visited[now.v] = true;
            size.add(now.cost);

            for (Node next : graph.get(now.v)) {
                if (!visited[next.v]) {
                    queue.offer(next);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
