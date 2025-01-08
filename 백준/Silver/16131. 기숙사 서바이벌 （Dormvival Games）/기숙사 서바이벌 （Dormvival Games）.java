import java.io.*;
import java.lang.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 전체 학생 수
        int A = Integer.parseInt(st.nextToken()); // 조 군의 방 번호
        int B = Integer.parseInt(st.nextToken()); // 기분 좋은 값
        int M = Integer.parseInt(st.nextToken()); // 기숙사 생활 기간 (단위 -> 주)

        int goodWeek = 0; // 기분 좋은 주
        int seqWeek = 0; // 연속으로 기분 좋은 주
        int MaxSeq = 0;
        int[] point = new int[N + 1]; // 상벌점 (index 가 학번)
        int[] seq = new int[N + 1]; // 현재 순서 (index 순서, value 학번)

        for (int i = 1; i < N + 1; i++) {
            seq[i] = i;
        }

        // 주의 수 만큼 반복
        for (int week = 1; week <= M; week++) {
            // 마지막 주가 아닌 경우 상점, 벌점 정산 -> 벌점은 빼기함
            // 배열의 index 값은 방 번호
            if (M != week) {
                st = new StringTokenizer(br.readLine());
                for (int i = 1; i < N + 1; i++) {
                    point[i] = Integer.parseInt(st.nextToken());
                }
                st = new StringTokenizer(br.readLine());
                for (int i = 1; i < N + 1; i++) {
                    point[i] -= Integer.parseInt(st.nextToken());
                }
            }
            int curA = 0;
            int cur1 = 0;
            for(int i = 1; i<N+1; i++) {
                if(seq[i] == 1) {
                    cur1 = i;
                }
                if(seq[i] == A) {
                    curA = i;
                }
            }
            // A 학번 친구 순서랑 주인공 순서가 B 이하로 떨어졌다면 좋은 주
            // 연속 주도 1 증가
            if (Math.abs(curA - cur1) <= B) {
                goodWeek++;
                seqWeek++;
                MaxSeq = Math.max(seqWeek, MaxSeq);
            }
            // 안 기쁘면 연속 기쁜 주 초기화
            else {
                seqWeek = 0;
            }
            for (int i = 1; i < N; i++) {
                int prev = point[seq[i]];
                int sub = point[seq[i + 1]];

                // 교환 조건 확인 및 교환
                if (change(prev, sub)) {
                    // 임시 변수로 point 값 저장
                    int tmpPrev = point[seq[i]];
                    int tmpSub = point[seq[i + 1]];

                    // 순서 교환
                    int tmp = seq[i];
                    seq[i] = seq[i + 1];
                    seq[i + 1] = tmp;

                    // 교환 후 point 값 갱신
                    point[seq[i]] = tmpSub - 2;
                    point[seq[i + 1]] = tmpPrev + 2;
                }
//              System.out.println("현재 순서:"+i+" "+(i+1)+" "+prev + " " + sub);
            }
//            int curA = 0;
//            int cur1 = 0;
//            for(int i = 1; i<N+1; i++) {
//                if(seq[i] == 1) {
//                    cur1 = i;
//                }
//                if(seq[i] == A) {
//                    curA = i;
//                }
//            }
//            // A 학번 친구 순서랑 주인공 순서가 B 이하로 떨어졌다면 좋은 주
//            // 연속 주도 1 증가
//            if (Math.abs(curA - cur1) <= B) {
//                goodWeek++;
//                seqWeek++;
//                MaxSeq = Math.max(seqWeek, MaxSeq);
//            }
//            // 안 기쁘면 연속 기쁜 주 초기화
//            else {
//                seqWeek = 0;
//            }
//          System.out.println(cur1+" "+curA);
        }
        System.out.println(goodWeek + " " + MaxSeq);
    }

    public static boolean change(int prev, int sub) {
        // 둘 다 0 이상인경우 나쁜 방 친구가 2점 이상 높으면 교환
        if (prev >= 0 && sub >= 0 && sub - prev >= 2) {
            return true;
        }
        // 둘 다 음수인 경우 나쁜 방 친구가  4점 이상 높으면 교환
        else if (prev < 0 && sub < 0 && sub - prev >= 4) {
            return true;
        }
        // 나쁜 방 친구가 0 이상, 좋은 방 친구가 음수라면 그냥 교환
        else if (sub >= 0 && prev < 0) {
            return true;
        }
        else {
            return false;
        }
    }
}