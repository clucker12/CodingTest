package Baekjoon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class baekjoon11286 {
    public static void main(String[] args) {
        // 우선순위 큐 생성 (사용자 정의 정렬 기준 사용)
        PriorityQueue<Integer> pQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int absA = Math.abs(a); // a의 절댓값
                int absB = Math.abs(b); // b의 절댓값

                if (absA == absB) {
                    // 절댓값이 같을 경우 실제 값이 더 작은 것을 우선
                    return a - b;
                }
                // 절댓값이 더 작은 것을 우선
                return absA - absB;
            }
        });

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 입력 개수

        // 입력된 횟수만큼 반복
        for(int i = 0; i < n; i++){
            int a = sc.nextInt(); // 정수 입력

            if(a != 0){
                // 0이 아니면 우선순위 큐에 추가
                pQ.offer(a);
            } else {
                // 0이면 절댓값이 가장 작은 값 출력 및 제거
                if(!pQ.isEmpty()){
                    System.out.println(pQ.poll()); // 큐에서 꺼내 출력
                } else {
                    // 큐가 비어있으면 0 출력
                    System.out.println(0);
                }
            }
        }
    }
}
