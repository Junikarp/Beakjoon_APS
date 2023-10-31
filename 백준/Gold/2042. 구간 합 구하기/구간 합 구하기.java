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
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        long[] arr = new long[N+1];
        for(int i = 1; i<= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        SegmentTree stree = new SegmentTree(N);
        
        stree.init(arr,1,1,N);
        
        for(int i = 0; i< M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1) {
                stree.update(1, 1, N, b, c-arr[b]);
                arr[b] = c;
            } else {
                sb.append(stree.sum(1,1,N,b,(int)c)).append("\n");
            }
        }	
        System.out.println(sb);
    }
    
    static class SegmentTree{
        long tree[];
        int treeSize;
        
        public SegmentTree(int size) {
            // 트리의 높이
            int h = (int)Math.ceil(Math.log(size)/Math.log(2));
            
            // 트리의 사이즈 2의 h+1 승
            this.treeSize = (int) Math.pow(2, h+1);
            
            // 트리 생성
            tree = new long[treeSize];
        }
        
        public long init(long[] arr, int node, int start, int end) {
            if(start == end) {
                return tree[node] = arr[start];
            }
            
            return tree[node] = init(arr, node*2, start, (start+end)/2) + init(arr, node*2+1, (start+end)/2+1, end);
            
        }
        
        // diff 값 변경시 트리의 구간합 재계산
        public void update(int node, int start, int end, int idx, long diff) {
            if(idx < start || end < idx) {
                return;
            }
            
            int mid = (start+end)/2;
            tree[node] += diff;
            
            if(start != end) {
                update(node*2, start, mid, idx, diff);
                update(node*2+1, mid+1, end, idx, diff);
            }
        }
        
        public long sum(int node, int start, int end, int left, int right) {
            if(left > end || right < start) {
                return 0;
            }
            
            if(left <= start && end <= right) {
                return tree[node];
            }
            
            return sum(node*2, start, (start+end)/2, left, right) + sum(node*2 + 1, (start+end)/2 + 1, end, left, right);  
        }
    }

}



