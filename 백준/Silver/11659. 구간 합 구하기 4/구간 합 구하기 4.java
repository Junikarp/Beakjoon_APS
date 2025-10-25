import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        int[] dp = new int[N+1];

        arr[0] = 0;
        dp[0] = arr[0];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<N+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i-1]+arr[i];
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            sb.append(dp[W]-dp[V-1]).append("\n");
        }
        System.out.print(sb);
    }
}