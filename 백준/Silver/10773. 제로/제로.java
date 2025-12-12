import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int[] arr;
    static boolean[] TF;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<K; i++){
            int N = Integer.parseInt(br.readLine());
            if(N == 0){
                stack.pop();
            }
            else{
                stack.push(N);
            }
        }
        int sum = 0;
        for(int i:stack){
            sum += i;
        }
        System.out.print(sum);
    }
}