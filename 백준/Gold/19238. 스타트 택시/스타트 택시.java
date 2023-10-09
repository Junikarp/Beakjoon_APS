import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, energy, nowP;
    static int[][] map, dist;
    static Point[] end;
    static boolean[][] visited;
    static Point taxi;
    static int[] dr = { 1, 0, -1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static class Person implements Comparable<Person> {
        int r;
        int c;
        int d;
        int num;

        @Override
        public String toString() {
            return "Person [r=" + r + ", c=" + c + ", d=" + d + ", num=" + num + "]";
        }

        public Person(int r, int c, int d, int num) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.num = num;
        }

        @Override
        public int compareTo(Person o) {

            if (this.d == o.d) {
                if (this.r == o.r) {
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.d - o.d;
        }

    }

    static LinkedList<Person> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        energy = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        nowP = 0;
        end = new Point[M+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1 ? -1 : 0;
            }
        }
        

        st = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(st.nextToken())-1;
        int ty = Integer.parseInt(st.nextToken())-1;
        taxi = new Point(tx, ty);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int Csx = Integer.parseInt(st.nextToken())-1;
            int Csy = Integer.parseInt(st.nextToken())-1;
            int Cex = Integer.parseInt(st.nextToken())-1;
            int Cey = Integer.parseInt(st.nextToken())-1;

            list.add(new Person(Csx, Csy, 0, i));
            end[i] = new Point(Cex, Cey);
        }
        
        int size = list.size();
        
        
        while(size-- > 0) {
            bfs();
            move();
            bfs2();
         }
        
        System.out.println(energy);

    }

    // 현재 택시에서 손님위치 찾기
    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[N][N];
        dist = new int[N][N];
        queue.add(new Point(taxi.x, taxi.y));
        visited[taxi.x][taxi.y] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now.x + dr[i];
                int nc = now.y + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc] || map[nr][nc] == -1) {
                    continue;
                }

                dist[nr][nc] += dist[now.x][now.y] + 1;
                visited[nr][nc] = true;
                queue.add(new Point(nr, nc));
            }
        }

        // 승객과의 거리 갱신
        for (Person p : list) {
            if(!visited[p.r][p.c]) {
                System.out.println(-1);
                System.exit(0);
            }
            p.d = dist[p.r][p.c];
        }
//        printd();
    }

    public static void bfs2() {
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[N][N];
        queue.add(new Point(taxi.x, taxi.y));
        visited[taxi.x][taxi.y] = true;
        
        if(end[nowP].x == taxi.x && end[nowP].y== taxi.y) {
        	return;
        }
    
        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = now.x + dr[i];
                    int nc = now.y + dc[i];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc] || map[nr][nc] == -1) {
                        continue;
                    }
//                    System.out.println(nowP);
//                    print();
                    if(end[nowP].x == nr && end[nowP].y== nc) {
                    	cnt++;
                    	energy--;
                    	
                        if(energy < 0) {
                            System.out.println(-1);
                            System.exit(0);
                        }
                        energy += cnt*2;
                        taxi = new Point(nr, nc);
                        return;
                    }

                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc));
                }
            }
            cnt++;
            energy--;
            
            if(energy < 0) {
                System.out.println(-1);
                System.exit(0);
            }
            
           
        }
        if(!visited[end[nowP].x][end[nowP].y]) {
        	System.out.println(-1);
            System.exit(0);
        }
//        print();
    }

    // 승객에게 이동
    public static void move() {
        Collections.sort(list);
        Person target = list.poll();
        
        taxi = new Point(target.r, target.c);
        energy -= target.d;
        nowP = target.num;
        
        if(energy < 0) {
            System.out.println(-1);
            System.exit(0);
        }
    }
    
    public static void print() {
        for (int i = 0; i < N; i++) {
            System.out.println();
            for (int j = 0; j < N; j++) {
                System.out.print(dist[i][j]+"\t");
            }
        }
    }
    
    public static void printd() {
        for (Person p : list) {
            System.out.print(p.d+"\t");;
        }
        System.out.println();
    }
}

//1. 누군가의 도착지가 또 다른 누군가의 출발지
//
//- 문제에서 모든 출발지와 목적지는 빈칸이고, 모든 출발지는 서로 다르며, 각 손님의 출발지와 목적지는 다르다. 라고
//
//  설명되어 있어 맵에 출발지와 도착지를 승객의 번호로 지정하였었는데, 여기서 나타날 수 있는 예외는
//
//   A승객의 도착지가 B승객의 출발지가 될 수 있습니다. 이렇게 되면 A승객의 도착지를 맵에 표시하고
//
//   B승객의 출발지를 맵에 표시하는 순간 A승객의 도착지는 사라지게 됩니다.
//
//1-1. 도착지에서 바로 승객 태우기
//
//- 위 1번을 깨닫지 못하고 문제를 풀었을 때 저는 승객을 도착지에 내려준 후 현재 좌표에서 인접한 부분부터
//
//  탐색하여 가장 가까운 승객을 찾았습니다. 이렇게 되면 가장 가까운 승객의 거리는 최소 1이 되겠죠.
//
//  내려주자마자 태우면 0일수도 있는데,,      
//
//2. 승객이 무조건 도착지를 갈 수 있다는 보장이 없음
//
//- 이걸 찾는데 상당히 오래걸렸는데,,, 승객이 도착지로 갈 수 있는 루트가  없을 수도 있습니다.
