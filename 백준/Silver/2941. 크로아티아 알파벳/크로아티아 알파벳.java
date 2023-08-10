import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		int size = str.length();
		
		for(int i=0; i<str.length()-1; i++ ) {
			if(str.charAt(i) == 'c') {
				if(str.charAt(i+1) == '=' || str.charAt(i+1) == '-') {
					size--;
				}
			}
			if(str.charAt(i) == 'l' && str.charAt(i+1) == 'j') {
				size--;
			}
			if(str.charAt(i) == 'n' && str.charAt(i+1) == 'j') {
				size--;
			}
			if(i<str.length()-2 && str.charAt(i) == 'd' && str.charAt(i+1) == 'z'&& str.charAt(i+2) == '=') {
				size--;
			}
			if(str.charAt(i) == 'z'&& str.charAt(i+1) == '=') {
				size--;
			}
			if(str.charAt(i) == 's'&& str.charAt(i+1) == '=') {
				size--;
			}
			if(str.charAt(i) == 'd'&& str.charAt(i+1) == '-') {
				size--;
			}
		}
		System.out.println(size);
	}
}

