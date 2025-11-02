import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int v1 = 0;
        int v2 = 0;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < N - 1; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int sum = arr[i] + arr[mid];

                if (Math.abs(sum) < minDiff) {
                    minDiff = Math.abs(sum);
                    v1 = arr[i];
                    v2 = arr[mid];
                }

                if (sum < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(v1 + " " + v2);
    }
}
