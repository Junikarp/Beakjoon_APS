import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean tag = false;

        String str = br.readLine();


        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '<') {
                tag = true;
                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(str.charAt(i));
            } else if(str.charAt(i) == '>') {
                tag = false;
                sb.append(str.charAt(i));
            } else if(tag) {
                sb.append(str.charAt(i));
            } else if(!tag){
                if(str.charAt(i) == ' ') {
                    while(!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                } else {
                    stack.push(str.charAt(i));
                }
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.print(sb);
    }
}
