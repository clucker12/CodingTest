package Baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;

public class baekjoon1927 {
    // N 연산 개수
    public static void main(String[] args) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 연산의 개수

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(); // 입력된 연산

            if (a != 0) {
                // 자연수이면 힙에 추가
                pQ.offer(a);
            } else {
                // 0이면 최솟값 출력 또는 0 출력
                if (!pQ.isEmpty()) {
                    System.out.println(pQ.poll()); // 최솟값 출력 및 제거
                } else {
                    System.out.println(0); // 힙이 비어 있을 경우
                }
            }
        }

        sc.close(); // 리소스 정리
    }
}
