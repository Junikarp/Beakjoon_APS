import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[][] V = new int[N+1][2];
            int[][] dp = new int[N+1][2];

            for(int j = 0; j <= 1; j++) {
                st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= N; i++) {
                    V[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[1][1] = V[1][1];
            dp[1][0] = V[1][0];

            for(int i = 2; i <= N; i++) {
                for(int j = 0; j<=1; j++) {
                    if(j == 0) {
                        dp[i][j] = Math.max(dp[i-1][1] + V[i][0], Math.max(dp[i-2][1],dp[i-2][0])+V[i][0]);
                    } else {
                        dp[i][j] = Math.max(dp[i-1][0] + V[i][1], Math.max(dp[i-2][1],dp[i-2][0])+V[i][1]);
                    }
                }
            }
            sb.append(Math.max(dp[N][0],dp[N][1])).append("\n");
        }
        System.out.print(sb);
    }
}