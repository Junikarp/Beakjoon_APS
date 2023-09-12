import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static HashSet<Integer>[] like;
    static int[] dc = {-1,1,0,0};
    static int[] dr = {0,0,-1,1};  
    static int[] seq;
    static int sum;
    static class Point {
        int c;
        int r;
        int happy;
        int blank;
        public Point(int c, int r, int happy, int blank) {
            this.c = c;
            this.r = r;
            this.happy = happy;
            this.blank = blank;
        }
    }

    static PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            if(o1.happy == o2.happy) {
                if(o1.blank == o2.blank) {
                    if(o1.c == o2.c) {
                        return o1.r - o2.r;
                    }
                    return o1.c - o2.c;
                }
                return o2.blank - o1.blank;
            }
            return o2.happy - o1.happy;
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        like = new HashSet[N*N+1];
        seq = new int[N*N];
        sum = 0;
        
        for(int i = 0; i<like.length; i++) {
        	like[i] = new HashSet<>();
        }

        for(int i = 1; i < like.length; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            seq[i-1] = u;
            for(int j = 0; j<4; j++) {
                like[u].add(Integer.parseInt(st.nextToken()));
            }
        }
        
        for(int i = 0; i<seq.length; i++) {
        	search(seq[i]);
        }
        
        score();
        
        System.out.println(sum);
    }
    
    public static void search(int num) {
    	queue.clear();
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(map[i][j] != 0) {
    				continue;
    			}
    			int bcnt = 0; // 공백 갯수
    			int hcnt = 0; // 좋아하는 학생 수
    			for(int k= 0; k<4; k++) {
    				int nc = i+dc[k];
    				int nr = j+dr[k];
    				
    				if(nc >= N || nr >= N || nc < 0 || nr < 0) {
    					continue;
    				}
    				
    				if(map[nc][nr] == 0) {
    					bcnt++;
    				} else {
    					if(like[num].contains(map[nc][nr])) {
    						hcnt++;
    					}
    				}
    			}
    			queue.offer(new Point(i,j,hcnt,bcnt));
    		}
    	}
    	map[queue.peek().c][queue.peek().r] = num;
    }
    
    public static void score() {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			int hcnt = 0;
    			for(int k= 0; k<4; k++) {
    				int nc = i+dc[k];
    				int nr = j+dr[k];
    				
    				if(nc >= N || nr >= N || nc < 0 || nr < 0) {
    					continue;
    				}
    				if(like[map[i][j]].contains(map[nc][nr])) {
						hcnt++;
					}
    			}
    			if(hcnt == 1) {
    				sum += 1;
    			} else if(hcnt == 2) {
    				sum += 10;
    			} else if(hcnt == 3) {
    				sum += 100;
    			} else if(hcnt == 4){
    				sum += 1000;
    			}
    		}
    	}
    }
    public static void print() {
    	for (int i = 0; i < N; i++) {
    		System.out.println();
    		for (int j = 0; j < N; j++) {
    			System.out.print(map[i][j]+" ");
				
			}
			
		}
    }
}