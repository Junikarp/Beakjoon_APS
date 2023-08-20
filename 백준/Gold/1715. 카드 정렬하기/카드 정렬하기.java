import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> o1-o2);

        int sum = 0;
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        while(queue.size()>=2) {
            int v1 = queue.poll();
            int v2 = queue.poll();
            int v3 = v1+v2;
            sum += v3;
            queue.offer(v3);
        }

        System.out.print(sum);
    }
}