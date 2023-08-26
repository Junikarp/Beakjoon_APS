import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N+1];
            boolean[] visited = new boolean[N+1];

            for (int i = 0; i < N-1; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                arr[e] = s;
            }

            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());


            while(true) {
                visited[v1] = true;
                v1 = arr[v1];
                if (v1 == 0) {
                    break;
                }
            }

            if(visited[v2]) {
                sb.append(v2).append("\n");
            } else {
                while(true) {
                    v2 = arr[v2];
                    if(visited[v2]) {
                        sb.append(v2).append("\n");
                        break;
                    }
                }
            }

        }
        System.out.println(sb);

    }

}