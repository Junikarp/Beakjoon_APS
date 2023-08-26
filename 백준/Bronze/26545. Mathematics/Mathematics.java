import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static boolean[] can;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        while(N-- > 0) {
            int v = Integer.parseInt(br.readLine());
            sum += v;
        }
        
        System.out.println(sum);

    }

}