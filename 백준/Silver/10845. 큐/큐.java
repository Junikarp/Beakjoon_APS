import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[] arr = new int[30000];
    static int size = 0;
    static int f_idx = 0;
    static int b_idx = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if (str.equals("push")) {
                int v = Integer.parseInt(st.nextToken());
                push(v);
            } else if (str.equals("pop")) {
                sb.append(pop()).append("\n");
            } else if (str.equals("size")) {
                sb.append(size()).append("\n");
            } else if (str.equals("empty")) {
                sb.append(empty()).append("\n");
            } else if (str.equals("front")) {
                sb.append(front()).append("\n");
            } else if (str.equals("back")) {
                sb.append(back()).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void push(int v) {
        arr[++b_idx] = v;
        size++;
    }

    public static int pop() {
        if (size == 0) {
            return -1;
        } else {
            size--;
            return arr[f_idx++];
        }
    }

    public static int size() {
        return size;
    }

    public static int empty() {
        if (size == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int front() {
        if (size == 0) {
            return -1;
        } else {
            return arr[f_idx];
        }
    }

    public static int back() {
        if (size == 0) {
            return -1;
        } else {
            return arr[b_idx];
        }
    }
}
