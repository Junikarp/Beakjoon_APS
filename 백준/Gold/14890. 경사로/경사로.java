import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static boolean[] can;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int load = 0;
        visited = new boolean[N][N];
        can = new boolean[N];

        // 가로 탐색
        for (int i = 0; i < N; i++) {
            int h = map[i][0];
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j] == h) {
                    cnt++;
                }
                if (map[i][j] == h - 1) {
                    h = map[i][j];
                    if (!visited[i][j]) {
                        cnt = 1;
                    } else {
                        cnt = 0;
                    }
                }
                if (map[i][j] == h + 1) {
                    if (cnt >= M) {
                        for(int k = 1; k<=M; k++) {
                            visited[i][j - k] = true;
                        }
                        cnt = 1;
                    } else {
                        can[i] = true;
                        break;
                    }
                    h = map[i][j];
                }
                if (Math.abs(map[i][j] - h) >= 2) {
                    can[i] = true;
                    break;
                }
            }
        }

        loop1:
        for (int i = 0; i < N; i++) {
            int h = map[i][N - 1];
            int cnt = 0;
            if (can[i]) {
                continue;
            }
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] == h && !visited[i][j]) {
                    cnt++;
                }
                if (map[i][j] == h - 1) {
                    h = map[i][j];
                    if (!visited[i][j]) {
                        cnt = 1;
                    } else {
                        cnt = 0;
                    }
                }
                if (map[i][j] == h + 1) {
                    if (cnt < M) {
                        can[i] = true;
                        continue loop1;
                    }
                    h = map[i][j];
                    if (!visited[i][j]) {
                        cnt = 1;
                    } else {
                        cnt = 0;
                    }
                }
                if (Math.abs(map[i][j] - h) >= 2) {
                    can[i] = true;
                    continue loop1;
                }
            }
            load++;
        }

        visited = new boolean[N][N];
        can = new boolean[N];


        int[][] rotate = new int[N][N];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = map[N - 1 - j][i];
            }
        }

        map = rotate;

        // 세로 탐색
        for (int i = 0; i < N; i++) {
            int h = map[i][0];
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j] == h) {
                    cnt++;
                }
                if (map[i][j] == h - 1) {
                    h = map[i][j];
                    if (!visited[i][j]) {
                        cnt = 1;
                    } else {
                        cnt = 0;
                    }
                }
                if (map[i][j] == h + 1) {
                    if (cnt >= M) {
                        for(int k = 1; k<=M; k++) {
                            visited[i][j - k] = true;
                        }
                        h = map[i][j];
                        cnt = 1;
                    } else {
                        can[i] = true;
                        break;
                    }
                }
                if (Math.abs(map[i][j] - h) >= 2) {
                    can[i] = true;
                    break;
                }
            }
        }

        loop1:
        for (int i = 0; i < N; i++) {
            int h = map[i][N - 1];
            int cnt = 0;
            if (can[i]) {
                continue;
            }
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] == h && !visited[i][j]) {
                    cnt++;
                }
                if (map[i][j] == h - 1) {
                    h = map[i][j];
                    if (!visited[i][j]) {
                        cnt = 1;
                    } else {
                        cnt = 0;
                    }
                }
                if (map[i][j] == h + 1) {
                    h = map[i][j];
                    if (cnt < M) {
                        can[i] = true;
                        continue loop1;
                    }
                    if (!visited[i][j]) {
                        cnt = 1;
                    } else {
                        cnt = 0;
                    }
                }
                if (Math.abs(map[i][j] - h) >= 2) {
                    can[i] = true;
                    continue loop1;
                }
            }
            load++;
        }

        System.out.println(load);
    }

}