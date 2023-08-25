import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int max = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        // 배열의 가로 세로 길이
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

//        move_d();
//
//        for (int i = 0; i < N; i++) {
//            System.out.println(
//
//            );
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + "\t");
//            }
//        }


        System.out.print(max);
    }

    public static void move_d() {
        for(int i = 0; i < N; i++) {
            int index = N - 1;
            int block = 0;
            for(int j = N - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    if (block == map[j][i]) {
                        map[index + 1][i] = block * 2;
                        block = 0;
                        map[j][i] = 0;
                    } else {
                        block = map[j][i];
                        map[j][i] = 0;
                        map[index][i] = block;
                        index--;
                    }
                }
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }


    }

    public static void move_l() {
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = map[j][N - i - 1];
            }
        }
        map = arr;

        for(int i = 0; i < N; i++) {
            int index = N - 1;
            int block = 0;
            for(int j = N - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    if (block == map[j][i]) {
                        map[index + 1][i] = block * 2;
                        block = 0;
                        map[j][i] = 0;
                    } else {
                        block = map[j][i];
                        map[j][i] = 0;
                        map[index][i] = block;
                        index--;
                    }
                }
            }
        }


        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = map[N - j - 1][i];
            }
        }

        map = arr;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }


    }

    public static void move_r() {
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = map[N - j - 1][i];
            }
        }
        map = arr;

        for(int i = 0; i < N; i++) {
            int index = N - 1;
            int block = 0;
            for(int j = N - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    if (block == map[j][i]) {
                        map[index + 1][i] = block * 2;
                        block = 0;
                        map[j][i] = 0;
                    } else {
                        block = map[j][i];
                        map[j][i] = 0;
                        map[index][i] = block;
                        index--;
                    }
                }
            }
        }

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = map[j][N - i - 1];
            }
        }

        map = arr;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }
    }

    public static void move_u() {
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = map[N - i - 1][N - j - 1];
            }
        }
        map = arr;

        for(int i = 0; i < N; i++) {
            int index = N - 1;
            int block = 0;
            for(int j = N - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    if (block == map[j][i]) {
                        map[index + 1][i] = block * 2;
                        block = 0;
                        map[j][i] = 0;
                    } else {
                        block = map[j][i];
                        map[j][i] = 0;
                        map[index][i] = block;
                        index--;
                    }
                }
            }
        }


        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = map[N - i - 1][N - j - 1];
            }
        }

        map = arr;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }
    }

    public static void dfs(int d) {
        if (d == 5) {
            return;
        }

        int[][] clone = new int[N][N];

        for (int i = 0; i < N; i++) {
            clone[i] = map[i].clone();
        }

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                move_d();
                dfs(d + 1);
                for (int j = 0; j < N; j++) {
                    map[j] = clone[j].clone();
                }
            } else if (i == 1) {
                move_r();
                dfs(d + 1);
                for (int j = 0; j < N; j++) {
                    map[j] = clone[j].clone();
                }
            } else if (i == 2) {
                move_u();
                dfs(d + 1);
                for (int j = 0; j < N; j++) {
                    map[j] = clone[j].clone();
                }
            } else if (i == 3) {
                move_l();
                dfs(d + 1);
                for (int j = 0; j < N; j++) {
                    map[j] = clone[j].clone();
                }
            }
        }
    }
}