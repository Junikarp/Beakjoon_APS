import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static int res[];
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        res = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0,0);
    }

    public static void dfs(int d, int idx) {
        if(d == 0) {
            res = new int[M];
        }

        if(d == M) {
            for(int i = 0; i<M; i++) {
                System.out.print(res[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i = idx; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                res[d] = arr[i];
                dfs(d+1, i+1);
                visited[i] = false;
            }
        }
    }
}
