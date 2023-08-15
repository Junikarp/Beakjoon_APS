import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        Long sum = 0L;
        Long xor = 0L;

        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());

            if(q == 1) {
                int num = Integer.parseInt(st.nextToken());
                sum += num;
                xor ^= num;
            } else if(q == 2) {
                int num = Integer.parseInt(st.nextToken());
                sum -= num;
                xor ^= num;
            } else if(q == 3) {
                sb.append(sum).append("\n");
            } else if(q == 4) {
                sb.append(xor).append("\n");
            }
        }
        System.out.print(sb);
    }
}
