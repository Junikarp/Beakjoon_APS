import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int size = 0;
    static int front = 0;
    static int back = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            switch(st.nextToken()){
                case "push": push(Integer.parseInt(st.nextToken())); break;
                case "pop" : pop();break;
                case "size" : size();break;
                case "empty" : empty();break;
                case "front" : front();break;
                case "back" : back();break;
            }
        }
        System.out.println(sb);
    }

    static void push(int n) {
        arr[back] = n;
        back++;
        size++;
    }

    static void pop() {
        if(size == 0) {
            sb.append(-1).append('\n');
        }
        else {
            sb.append(arr[front]).append('\n');
            size--;
            front++;
        }
    }

    static void size() {
        sb.append(size).append('\n');
    }

    static void empty() {
        if(size == 0) {
            sb.append(1).append('\n');
        }
        else sb.append(0).append('\n');
    }

    static void front() {
        if(size == 0) {
            sb.append(-1).append('\n');
        }
        else {
            sb.append(arr[front]).append('\n');
        }
    }

    static void back() {
        if(size == 0) {
            sb.append(-1).append('\n');
        }
        else {
            sb.append(arr[back - 1]).append('\n');
        }
    }
}