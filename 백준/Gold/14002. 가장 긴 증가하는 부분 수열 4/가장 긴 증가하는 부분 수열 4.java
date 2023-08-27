import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] lis = new int[N];
        int[] len = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];
        len[0] = 1;
        int idx = 1;

        for(int i = 1; i<arr.length; i++) {
            if(arr[i] < lis[0]) {
                lis[0] = arr[i];
                len[i] = 1;
            } else if (lis[idx - 1] < arr[i]) {
                len[i] = idx+1;
                lis[idx++] = arr[i];
            } else {
                int tmp = Arrays.binarySearch(lis, 0, idx, arr[i]);
                if(tmp < 0) {
                    tmp = -(tmp+1);
                }
                lis[tmp] = arr[i];
                len[i] = tmp+1;
            }
        }
        int v = idx;

        Stack<Integer> stack = new Stack<Integer>();
        for(int i = N-1; i>=0; i--) {
            if(v == len[i]) {
                stack.push(arr[i]);
                v--;
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(idx);
        System.out.print(sb);
    }
}


//6
//1 3 5 7 2 3