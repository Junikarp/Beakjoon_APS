import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arr = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        arr.sort(Collections.reverseOrder());

        int level = 0;
        int sum = 0;

        if (arr.size() > 42) {
            for (int i = 0; i < 42; i++) {
                if (arr.get(i) >= 250) {
                    sum += 5;
                } else if (arr.get(i) >= 200 && arr.get(i) < 250) {
                    sum += 4;
                } else if (arr.get(i) >= 140 && arr.get(i) < 200) {
                    sum += 3;
                } else if (arr.get(i) >= 100 && arr.get(i) < 140) {
                    sum += 2;
                } else if (arr.get(i) >= 60 && arr.get(i) < 100){
                    sum += 1;
                }
                level += arr.get(i);
            }
        } else {
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) >= 250) {
                    sum += 5;
                } else if (arr.get(i) >= 200 && arr.get(i) < 250) {
                    sum += 4;
                } else if (arr.get(i) >= 140 && arr.get(i) < 200) {
                    sum += 3;
                } else if (arr.get(i) >= 100 && arr.get(i) < 140) {
                    sum += 2;
                } else if(arr.get(i) >= 60 && arr.get(i) < 100) {
                    sum += 1;
                }
            level += arr.get(i);
            }
        }
        System.out.print(level+" "+sum);
    }
}


