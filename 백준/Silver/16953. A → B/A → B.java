import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int cnt = 0;

        while(true) {
            if(B%2 == 0) {
                B /= 2;
                cnt++;
            } else if(B%10 == 1) {
                B /= 10;
                cnt++;
            } else {
                System.out.print(-1);
                break;
            }
            if(A == B) {
                System.out.print(cnt+1);
                break;
            } else if(A > B) {
                System.out.print(-1);
                break;
            }
        }
    }
}
