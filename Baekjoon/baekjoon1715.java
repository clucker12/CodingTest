package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class baekjoon1715 {
    public static void main(String[] args)throws IOException {
        // 입력을 받기 위해 BufferedReader 사용
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 숫자 카드 묶음의 개수 N 입력 받기
        int N = Integer.parseInt(bf.readLine());

        // 우선순위 큐(PriorityQueue)를 사용하여 항상 가장 작은 묶음을 먼저 꺼내기
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // N개의 숫자 카드 묶음을 우선순위 큐에 삽입
        for(int i=0; i<N; i++){
            int input = Integer.parseInt(bf.readLine());
            pq.offer(input);  // 카드 묶음 크기 저장
        }

        int total = 0;  // 총 비교 횟수를 저장할 변수

        // 카드 묶음이 2개 이상 있을 때만 합치기 가능
        while(pq.size() > 1){
            // 가장 작은 카드 묶음 2개 꺼내기
            int first = pq.poll();
            int second = pq.poll();

            // 두 묶음을 합친다
            int sum = first + second;

            // 합치는 데 사용된 비교 횟수를 누적
            total += sum;

            // 합쳐진 카드 묶음을 다시 우선순위 큐에 삽입
            pq.offer(sum);
        }

        // 최소 비교 횟수 출력
        System.out.println(total);
    }
}
