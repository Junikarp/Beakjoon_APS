import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+1];
        int[] P = new int[N+1];
        int[] dp = new int[N+2];

        int max = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken()); // 기간
            P[i] = Integer.parseInt(st.nextToken()); // 이익
        }

        for(int i = N; i >= 0; i--) {
            if(i+T[i] <= N) {
                dp[i] = Math.max(dp[i+T[i]]+P[i],dp[i+1]);
            } else {
                dp[i] = dp[i+1];
            }
        }

        System.out.print(dp[0]);
    }
}