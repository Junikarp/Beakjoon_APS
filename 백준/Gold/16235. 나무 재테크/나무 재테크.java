import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] food, plus;
    static int N, M, K, cnt;
    static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static ArrayList<tree> newtree;
    static ArrayList<tree> die_tree;
    static LinkedList<tree> queue = new LinkedList<>();

    static class tree implements Comparable<tree> {
        int c;
        int r;
        int age;

        public tree(int c, int r, int age) {
            this.c = c;
            this.r = r;
            this.age = age;
        }

        @Override
        public int compareTo(tree o1) {
            return this.age - o1.age;
        }

        @Override
        public String toString() {
            return "tree [c=" + c + ", r=" + r + ", age=" + age + "]";
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        food = new int[N+1][N+1];
        plus = new int[N+1][N+1];
        cnt = 0;

        // 양분 처음에 5
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                food[i][j] = 5;
            }
        }


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                plus[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            // 나무 정보 입력
            queue.add(new tree(c, r, age));
            cnt++;
        }

        while(K-- > 0) {
            solve();
        }


        System.out.print(queue.size());
    }

    public static void solve() {
        newtree = new ArrayList<>();
        die_tree = new ArrayList<>();

        // 봄
        int size = queue.size();

        if(size == 0) {
            return;
        }

        Collections.sort(queue);
        for(int i = 0; i < size; i++) {
            tree t = queue.poll();
            if(t.age <= food[t.c][t.r]) {
                food[t.c][t.r] -= t.age;
                queue.add(new tree(t.c,t.r,t.age+1));
                if((t.age+1) % 5 == 0) {
                    newtree.add(new tree(t.c,t.r,0));
                }
            } else {
                die_tree.add(t);
                cnt--;
            }
        }

        // 여름
        for(int i = 0; i < die_tree.size(); i++) {
            food[die_tree.get(i).c][die_tree.get(i).r] += die_tree.get(i).age/2;
        }

        // 가을
        for(int i = 0; i < newtree.size(); i++) {
            for(int j = 0; j < 8; j++) {
                int nc = newtree.get(i).c + dc[j];
                int nr = newtree.get(i).r + dr[j];

                if(nc > N || nr > N || nc <= 0 || nr <= 0) {
                    continue;
                }

                queue.add(new tree(nc,nr,1));
                cnt++;
            }
        }

        // 겨울
        for(int i = 1; i<=N; i++) {
            for (int j = 1; j <= N; j++) {
                food[i][j] += plus[i][j];
            }
        }

//        print();
    }

    public static void print() {
        for (int i = 1; i <= N; i++) {
            System.out.println();
            for (int j = 1; j <= N; j++) {
                System.out.print(food[i][j]+"\t");
            }
        }
        System.out.println();
    }
}
