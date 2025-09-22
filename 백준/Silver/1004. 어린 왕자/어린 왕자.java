import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int N = Integer.parseInt(br.readLine());

            int cnt = 0;

            for (int j = 0; j < N; j++) {
                int sum = 0;
                st = new StringTokenizer(br.readLine());
                int c1 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                int r1 = (int) (Math.pow(x1 - c1, 2) + Math.pow(y1 - c2, 2));
                int r2 = (int) (Math.pow(x2 - c1, 2) + Math.pow(y2 - c2, 2));

                if (r1 < r*r && r2 >= r*r)
                    cnt++;
                else if (r1 >= r*r && r2 < r*r)
                    cnt++;
            }
            System.out.println(cnt);
        }
    }
}