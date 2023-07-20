import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int N = Integer.parseInt(br.readLine());
         int start = 2;
         int x = 1;
         int y = 1;

         for(int i=1; i<N; i++) {
             if(start%2==1) {
                 if (y == 1) {
                     start++;
                     x++;
                 } else {
                     y--;
                     x++;
                 }
             } else {
                 if(x == 1) {
                     start++;
                     y++;
                 } else {
                     x--;
                     y++;
                 }
             }
         }
         System.out.print(x+"/"+y);
    }
}