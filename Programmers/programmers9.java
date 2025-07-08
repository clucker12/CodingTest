package Programmers;
import java.util.*;

public class programmers9 {
    public int solution(int[] scoville, int K) {
        int answer = 0; // 섞은 횟수를 저장할 변수
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 최소 힙(우선순위 큐)을 사용하여 가장 작은 스코빌 지수를 빠르게 꺼내기 위함

        // 모든 스코빌 지수를 우선순위 큐에 추가
        for (int s : scoville) {
            pq.offer(s);
        }

        // 큐에 원소가 2개 이상이고, 가장 낮은 스코빌 지수가 K보다 작을 때 반복
        while (pq.size() > 1 && pq.peek() < K) {
            int fir = pq.poll(); // 가장 맵지 않은 음식 꺼냄
            int sec = pq.poll(); // 두 번째로 맵지 않은 음식 꺼냄
            int sum = fir + (sec * 2); // 두 음식을 섞어서 새로운 음식 생성
            pq.offer(sum); // 새로운 음식의 스코빌 지수를 큐에 추가
            answer += 1; // 섞은 횟수 증가
        }

        // 모든 음식의 스코빌 지수가 K 이상이면 섞은 횟수 반환, 그렇지 않으면 -1
        return pq.peek() >= K ? answer : -1;
    }
}
