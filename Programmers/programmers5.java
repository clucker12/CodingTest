package Programmers;

public class programmers5 {
    // Solution 클래스 정의
    class Solution {
        // 어떤 던전을 이미 탐험했는지를 저장하는 배열
        private boolean[] visited;

        // 최대 탐험 가능한 던전 수를 저장하는 변수
        private int answer = 0;

        // 메인 solution 함수: 현재 피로도 k와 던전 배열 dungeons를 입력받음
        public int solution(int k, int[][] dungeons) {
            // 방문 여부 배열 초기화 (던전 개수만큼)
            visited = new boolean[dungeons.length];

            // DFS 탐색 시작: 깊이 0, 현재 피로도 k, 던전 정보
            dfs(0, k, dungeons);

            // 최대 탐험한 던전 수 반환
            return answer;
        }

        // DFS 함수: 현재까지 탐험한 수(depth), 남은 피로도(k), 던전 정보
        private void dfs(int depth, int k, int[][] dungeons) {
            // 현재 depth가 최대 탐험 수보다 크면 갱신
            answer = Math.max(answer, depth);

            // 모든 던전을 순회하면서 탐험 가능한지 확인
            for (int i = 0; i < dungeons.length; i++) {
                // 아직 방문하지 않았고, 현재 피로도가 최소 필요 피로도 이상이면 탐험 가능
                if (!visited[i] && k >= dungeons[i][0]) {
                    // 던전 i를 탐험한다고 표시
                    visited[i] = true;

                    // 다음 던전 탐험: 탐험 수 1 증가, 피로도 소모
                    dfs(depth + 1, k - dungeons[i][1], dungeons);

                    // 백트래킹: 탐험 취소하고 다른 경우의 수를 위해 방문 표시 초기화
                    visited[i] = false;
                }
            }
        }
    }
}
