package Programmers;

public class programmers11 {
    // 문제 해결 메서드
    public int solution(int n, int[][] computers) {
        int answer = 0;                   // 네트워크 개수를 세기 위한 변수
        boolean[] visited = new boolean[n]; // 각 컴퓨터의 방문 여부를 저장하는 배열

        // 모든 컴퓨터를 한 번씩 확인
        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않은 컴퓨터라면 (새로운 네트워크 발견)
            if (!visited[i]) {
                dfs(i, computers, visited, n); // 해당 컴퓨터와 연결된 모든 컴퓨터 탐색
                answer++;                      // 하나의 네트워크 탐색이 끝났으므로 +1
            }
        }

        // 모든 컴퓨터를 확인한 후, 네트워크의 개수 반환
        return answer;
    }

    // 깊이 우선 탐색(DFS) 메서드
    private void dfs(int node, int[][] computers, boolean[] visited, int n) {
        visited[node] = true; // 현재 컴퓨터(node)를 방문 처리

        // 현재 컴퓨터와 연결된 다른 컴퓨터들을 탐색
        for (int i = 0; i < n; i++) {
            // computers[node][i] == 1 : 연결되어 있음
            // !visited[i] : 아직 방문하지 않은 컴퓨터
            if (computers[node][i] == 1 && !visited[i]) {
                dfs(i, computers, visited, n); // 연결된 컴퓨터로 재귀 탐색
            }
        }
    }
}
