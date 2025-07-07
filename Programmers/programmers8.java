package Programmers;

// 메인 클래스 (파일 이름 programmers8.java와 일치)
public class programmers8 {

    // 내부 클래스 Solution: 실제 문제 해결 로직
    class Solution {

        // 문제를 해결하는 main 함수
        public int solution(int[] numbers, int target) {
            // DFS(깊이 우선 탐색)를 시작: index 0부터 시작하고, 초기 합은 0
            return dfs(numbers, target, 0, 0);
        }

        /**
         * DFS 재귀 함수
         * @param numbers : 숫자 배열
         * @param target : 목표 숫자
         * @param index : 현재 탐색 중인 인덱스
         * @param sum : 현재까지의 합
         * @return 현재 경로에서 target을 만들 수 있는 경우의 수
         */
        private int dfs(int[] numbers, int target, int index, int sum) {
            // 모든 숫자를 다 사용한 경우 (기저 조건)
            if (index == numbers.length) {
                // 합이 target과 같으면 1 (성공적인 한 가지 방법)
                return sum == target ? 1 : 0;
            }

            // 현재 숫자를 더하는 경우
            int plus = dfs(numbers, target, index + 1, sum + numbers[index]);

            // 현재 숫자를 빼는 경우
            int minus = dfs(numbers, target, index + 1, sum - numbers[index]);

            // 두 경우의 수를 모두 합산하여 반환
            return plus + minus;
        }
    }
}
