import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		HashSet<String> arr = new HashSet<>();
		for(int i =0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String enter = st.nextToken();
			
			if(enter.equals("enter")) {
				arr.add(name);
			}
			else if(enter.equals("leave")) {
				arr.remove(name);
			}
		}
		List<String> keymap = new ArrayList<>(arr);
		Collections.sort(keymap);
		Collections.reverse(keymap);
		
		for(String str : keymap) {			
			System.out.println(str);
		}
	}
}