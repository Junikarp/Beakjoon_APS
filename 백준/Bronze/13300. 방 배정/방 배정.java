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
        int K = Integer.parseInt(st.nextToken());
        
        int[][] room = new int[2][7];
        
        for(int t = 0; t<N; t++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	room[u][v]++;
        }
        
        int sum = 0;
        for(int i = 0; i < room.length; i++) {
        	for(int j=0; j<room[0].length; j++) {
        			sum += room[i][j]/K;
        			if(room[i][j]%K!=0) {
        				sum++;
        			}
        	}
        }
        
        System.out.println(sum);
    }
}