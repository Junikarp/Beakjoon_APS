import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static class Person {
        int age;
        String name;
        int num;
        public Person(int age, String name, int num) {
            this.age = age;
            this.name = name;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Person[] arr = new Person[N];

        for(int i=0; i<arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Person(Integer.parseInt(st.nextToken()), st.nextToken(), i);
        }

        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.age == o2.age) {
                    return o1.num-o2.num;
                } else {
                    return o1.age-o2.age;
                }
            }
        });
        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i].age+" "+arr[i].name);
        }
    }
}
