import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        ArrayList<Point> arr;
        ArrayList<ArrayList<Integer>> graph;

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            arr = new ArrayList<>();

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr.add(new Point(x, y));
            }

            graph = new ArrayList<>();
            for (int i = 0; i < N + 2; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    if (dist(arr.get(i), arr.get(j)) <= 1000) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
            sb.append((bfs(graph, N) ? "happy" : "sad") + '\n');
        }
        System.out.print(sb);
    }

    public static int dist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static boolean bfs(ArrayList<ArrayList<Integer>> graph, int N) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        boolean[] visited = new boolean[N + 2];
        visited[0] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == N + 1) {
                return true;
            }

            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        return false;
    }

}
