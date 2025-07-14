package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class baekjoon1966 {
    // solution 함수: 문서 개수 N, 궁금한 문서 위치 M, 각 문서의 중요도 배열 priorities를 입력받아
    // 궁금한 문서가 몇 번째로 인쇄되는지 반환
    private static int solution(int N, int M, int[] priorities) {
        // 문서 정보를 저장할 큐 생성
        // 각 원소는 int 배열로 [문서 인덱스, 중요도] 형태
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            // 문서 인덱스와 중요도를 함께 큐에 추가
            queue.add(new int[]{i, priorities[i]});
        }

        int printOrder = 0;  // 인쇄 순서 카운트

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            // 큐에서 맨 앞 문서를 꺼냄
            int[] cur = queue.poll();

            // 현재 문서보다 중요도가 더 높은 문서가 큐에 존재하는지 확인하는 변수
            boolean hasHigher = false;

            // 큐에 남아있는 모든 문서들을 확인
            for (int[] doc : queue) {
                if (doc[1] > cur[1]) { // 현재 문서보다 중요도가 높은 문서가 있으면
                    hasHigher = true;
                    break;  // 더 이상 탐색할 필요 없이 탈출
                }
            }

            if (hasHigher) {
                // 더 중요한 문서가 있다면, 현재 문서를 다시 큐의 뒤로 보냄
                queue.add(cur);
            } else {
                // 현재 문서가 가장 중요도가 높다면 인쇄함
                printOrder++;  // 인쇄된 문서 수 증가

                // 인쇄된 문서가 우리가 찾던 문서인지 확인
                if (cur[0] == M) {
                    // 찾던 문서가 인쇄된 순서 반환 (종료)
                    return printOrder;
                }
            }
        }

        // 이 부분은 사실 도달하지 않음 (논리상)
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();  // 테스트 케이스 개수 입력
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();  // 문서 개수 입력
            int M = sc.nextInt();  // 궁금한 문서 위치 입력

            int[] priorities = new int[N];  // 중요도 배열 생성
            for (int i = 0; i < N; i++) {
                priorities[i] = sc.nextInt();  // 각 문서의 중요도 입력
            }

            // 각 테스트 케이스마다 결과 출력
            System.out.println(solution(N, M, priorities));
        }

        sc.close();  // Scanner 자원 해제
    }
}
