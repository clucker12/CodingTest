package Programmers;

public class programmers10 {
    // 전체 경우의 수 중 타겟 넘버를 만드는 경우의 개수를 저장할 변수
    int answer = 0;

    // 메인 메서드: numbers 배열과 target 값을 받아서 경우의 수를 계산
    public int solution(int[] numbers, int target) {
        // DFS 탐색 시작: 초기 깊이(depth)=0, 초기 합(sum)=0
        dfs(0, target, numbers, 0);
        return answer; // 최종적으로 target을 만든 횟수를 반환
    }

    // DFS(깊이 우선 탐색) 재귀 함수
    // depth: 현재 탐색 중인 인덱스
    // target: 목표 숫자
    // numbers: 주어진 숫자 배열
    // sum: 현재까지의 계산 결과 합
    public void dfs(int depth, int target, int[] numbers, int sum) {
        // 모든 숫자를 다 사용한 경우 (리프 노드에 도달)
        if (depth == numbers.length) {
            // 누적된 합(sum)이 target과 같으면 경우의 수 증가
            if (sum == target) {
                answer++;
            }
            return; // 더 이상 탐색할 필요 없으므로 종료
        }

        // 현재 숫자를 더하는 경우
        dfs(depth + 1, target, numbers, sum + numbers[depth]);
        // 현재 숫자를 빼는 경우
        dfs(depth + 1, target, numbers, sum - numbers[depth]);
    }
}
