import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[12];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;

        for(int i=3; i<dp.length; i++){
            dp[i] = dp[i-3]+dp[i-2]+dp[i-1];
        }
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
