import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(M>=45) {
            M -= 45;
        } else {
            int min = 45 - M;
            M = 60-min;
            if(N != 0) {
                N -= 1;
            } else {
                N = 23;
            }
        }
        System.out.print(N+" "+M);
    }
}
