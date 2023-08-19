import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int cal(char c, int N, int M) {
        if(c == '*') {
            return N*M%7;
        } else {
            return (N+M)%7;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            boolean[][] dp = new boolean[N+1][7];
            dp[0][1] = true;

            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                char c1 = st.nextToken().charAt(0);
                int v1 = Integer.parseInt(st.nextToken());
                char c2 = st.nextToken().charAt(0);
                int v2 = Integer.parseInt(st.nextToken());

                for(int j=0; j<7; j++) {
                    if(dp[i-1][j]) {
                        dp[i][cal(c1,j,v1)] = true;
                        dp[i][cal(c2,j,v2)] = true;
                    }
                }
            }
            if(dp[N][0]) {
                System.out.println("LUCKY");
            } else {
                System.out.println("UNLUCKY");
            }
        }
    }
}

