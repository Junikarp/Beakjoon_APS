import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        long totSum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
            totSum += A.get(i);
        }

        A.add(0, -1);
        A.add(-1);
        List<Integer> I = new ArrayList<>();
        for (int i = 0; i < N + 2; i++) {
            if (A.get(i) != 1) {
                I.add(i);
            }
        }

        int ans = N;
        int K = I.size() - 2;
        for (int L = 1; L <= K; L++) {
            long mul = 1;
            long sum_ = 0;
            for (int R = L; R <= K; R++) {
                mul *= A.get(I.get(R));
                sum_ += A.get(I.get(R)) - 1;
                if (mul > totSum) {
                    break;
                }
                if (L != R) {
                    int U = I.get(L) - I.get(L - 1) - 1;
                    int V = I.get(R + 1) - I.get(R) - 1;
                    long Z = mul - (sum_ + I.get(R) - I.get(L) + 1);
                    if (0 <= Z && Z <= U + V) {
                        ans += Math.min(Z, Math.min(U + V - Z, Math.min(U, V))) + 1;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
