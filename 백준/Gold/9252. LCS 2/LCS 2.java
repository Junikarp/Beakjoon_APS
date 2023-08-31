import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        sb.append(dp[str.length()][str2.length()]).append("\n");

        Stack<Character> stack = new Stack<>();

        int c = str.length();
        int r = str2.length();

        while (c != 0 && r != 0) {

            if (dp[c - 1][r] == dp[c][r]) {
                c--;
            } else if (dp[c][r - 1] == dp[c][r]) {
                r--;
            } else {
                if (str.charAt(c - 1) == str2.charAt(r - 1)) {
                    stack.push(str.charAt(c - 1));
                }
                c--;
                r--;
            }

        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}
