import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
 
public class Main {
    static int[] parent; 
    static int[] friends;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int M = N * 2;
            parent = new int[M];
            friends = new int[M];
            for (int i = 0; i < M; i++) {
                parent[i] = i;
                friends[i] = 1;
            }
 
            int idx = 0;
            Map<String, Integer> map = new HashMap<>();
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String str1 = st.nextToken();
                String str2 = st.nextToken();
 
                if (!map.containsKey(str1)) {
                    map.put(str1, idx++);
                }
 
                if (!map.containsKey(str2)) {
                    map.put(str2, idx++);
                }
 
                sb.append(union(map.get(str1), map.get(str2)) + "\n");
            }
        }
 
        System.out.println(sb);
    }
 
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
 
        return parent[x] = find(parent[x]);
    }
 
    public static int union(int x, int y) {
        x = find(x);
        y = find(y);
 
        if (x != y) {
            parent[y] = x;
            friends[x] += friends[y]; 
        } 
        return friends[x];
    }
 
}
 