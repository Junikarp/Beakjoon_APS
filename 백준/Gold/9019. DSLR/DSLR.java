import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static String[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            arr = new int[10000];
            boolean[] visited = new boolean[10000];
            ans = new String[10000];

            Arrays.fill(ans,  "");

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            bfs(start, end, visited);

            sb.append(ans[end]).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int start, int end, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int next = 0;
            for (int i = 1; i <= 4; i++) {
                if (i == 1) {
                    next = (now * 2) % 10000;
                    if (!visited[next]) {
                        visited[next] = true;
                        ans[next] = ans[now]+"D";
                        arr[next] = i;
                        queue.offer(next);
                        if (next == end) {
                            return;
                        }
                    }
                } else if (i == 2) {
                    next = now - 1;
                    if (next == -1) {
                        next = 9999;
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        ans[next] = ans[now]+"S";
                        arr[next] = i;
                        queue.offer(next);
                        if (next == end) {
                            return;
                        }
                    }
                } else if (i == 3) {
                    next = ((now % 1000) * 10) + (now / 1000);
                    if (!visited[next]) {
                        visited[next] = true;
                        ans[next] = ans[now]+"L";
                        arr[next] = i;
                        queue.offer(next);
                        if (next == end) {
                            return;
                        }
                    }
                } else if (i == 4) {
                    next = ((now % 10) * 1000) + (now / 10);
                    if (!visited[next]) {
                        visited[next] = true;
                        ans[next] = ans[now]+"R";
                        arr[next] = i;
                        queue.offer(next);
                        if (next == end) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
