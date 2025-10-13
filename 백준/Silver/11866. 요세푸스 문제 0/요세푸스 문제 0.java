import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<=N; i++){
            queue.offer(i);
        }
        StringBuilder sb = new StringBuilder();

        while(queue.size()>=1) {
            if(queue.size()==1&&queue.peek()==1){
                sb.append(1);
                break;
            }
            for(int i=0; i<M-1; i++){
                int s = queue.peek();
                queue.poll();
                queue.offer(s);
            }
            sb.append(queue.peek()).append(", ");
            queue.poll();
            if(queue.size()==1){
                sb.append(queue.peek());
                break;
            }
        }
        System.out.print("<"+sb+">");
    }
}