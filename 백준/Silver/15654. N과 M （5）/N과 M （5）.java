import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int[] arr;
    static int[] res;
    static StringBuilder sb;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        res = new int[M];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs( 0);


    }

    public static void dfs( int d) {
        if(d==M) {
            for(int i=0; i<M; i++) {
                System.out.print(res[i]+" ");
            }
            System.out.println();
            return;
        }
        for(int i=0; i<arr.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                res[d] = arr[i];
                dfs( d + 1);
                visited[i] = false;
            }
        }
    }
}

