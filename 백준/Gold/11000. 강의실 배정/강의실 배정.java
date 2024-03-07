import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static class Room implements Comparable<Room>{
		int start;
		int end;
		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		// 시작 시간을 기준으로 오름차순 정렬
		// 시작 시간이 같은 경우 종료 시간을 기준으로 정렬
		@Override
		public int compareTo(Room room) {
			if(this.start == room.start) {
				return this.end - room.end;
			}
			return this.start-room.start;
		}				
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        List<Room> roomList = new ArrayList<>();
        
        // 시작 시각 : start, 종료 시각 : end
        for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 각각의 시각을 입력 받아 새로운 방 생성 후 리스트에 저장
			roomList.add(new Room(start, end));
		}
        
        // 리스트에 저장된 강의실 정렬 (정렬 기준 compareTo)
        Collections.sort(roomList);
        
        // 우선순위 큐 사용
        Queue<Integer> queue = new PriorityQueue<>();
        // 처음 종료시간을 0으로 지정해놓고 새로운 최적 강의실이 생길때마다 갱신
        int endT = 0;
        // 지금까지 저장된 방 리스트들 순회
        for (Room now : roomList) {
        	// 정렬된 리스트에서 하나 뽑아서 종료 시간 갱신
        	endT = now.end;
        	
        	// 큐가 비어있지 않고 마지막 강의 종료 시간이 다음 강의 시작 시간과 같거나 더 작다면 
        	// 큐에서 제거
        	if(!queue.isEmpty() && queue.peek() <= now.start) {
        		queue.poll();
        	}
        	// 큐에 새로운 종료시각 저장(가장 최근 사용된 강의실이 종료되는 시간)
        	queue.add(endT);
        }
        // 강의 갯수 출력
        System.out.println(queue.size());
    }

}



