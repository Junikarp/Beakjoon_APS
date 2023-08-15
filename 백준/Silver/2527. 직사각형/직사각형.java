import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Rectangle{
        int x1;
        int y1;
        int x2;
        int y2;
        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = 4;

        for(int t = 0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            Rectangle r1 = new Rectangle(a,b,c,d);
            Rectangle r2 = new Rectangle(e,f,g,h);

            if(r1.x2 < r2.x1 || r1.y2 < r2.y1|| r1.x1>r2.x2 || r1.y1>r2.y2) {
                sb.append("d").append("\n");
            }
            else if ((r1.x1 == r2.x2 && r1.y1 == r2.y2) || (r1.x1 == r2.x2 && r1.y2 == r2.y1) || (r1.x2 == r2.x1 && r1.y2 == r2.y1) || (r1.x2 == r2.x1 && r1.y1 == r2.y2)) {
                sb.append("c").append("\n");
            }
            else if (r1.x2 == r2.x1 || r1.y2 == r2.y1 || r2.x2 == r1.x1 || r1.y1 == r2.y2) {
                sb.append("b").append("\n");
            }
            else {
                sb.append("a").append("\n");
            }

        }
            System.out.print(sb);
    }
}
