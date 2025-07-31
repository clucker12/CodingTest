package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class baekjoon1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에서 n: 큐 크기, m: 뽑을 원소 개수
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        // 1부터 n까지 덱에 삽입 (초기 큐 상태)
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }

        // 두 번째 줄에서 뽑을 원소들 입력
        Integer[] sol = new Integer[m];
        String[] sol_input = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            sol[i] = Integer.parseInt(sol_input[i]);
        }

        int cnt = 0;   // 2번, 3번 연산 횟수 누적 변수
        int idx = 0;   // 현재 뽑아야 할 sol 배열의 인덱스

        // 뽑을 숫자가 남아 있는 동안 반복
        while (idx < m) {
            int target = sol[idx];  // 이번에 뽑을 숫자
            int index = 0;          // target의 현재 덱 내 위치

            // target의 위치 찾기
            for (int j : deque) {
                if (j == target) break;
                index++;
            }

            // 왼쪽 회전이 더 빠른 경우 (2번 연산)
            if (index <= deque.size() / 2) {
                while (index-- > 0) {
                    deque.addLast(deque.pollFirst());  // 왼쪽으로 회전
                    cnt++;
                }
            }
            // 오른쪽 회전이 더 빠른 경우 (3번 연산)
            else {
                int right = deque.size() - index;  // 오른쪽 회전 횟수
                while (right-- > 0) {
                    deque.addFirst(deque.pollLast());  // 오른쪽으로 회전
                    cnt++;
                }
            }

            // 맨 앞의 target 제거 (1번 연산)
            deque.pop();  // == deque.pollFirst() 와 동일
            idx++;        // 다음 target으로 이동
        }

        // 결과 출력: 2번, 3번 연산의 총 횟수
        System.out.println(cnt);
    }
}
