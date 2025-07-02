package Programmers;
import java.util.*;

public class Programmers6 {

    // 전력망을 두 개로 나눴을 때 송전탑 수 차이의 최소값을 구하는 함수
    public int solution(int n, int[][] wires) {
        int answer = n;  // 초기값은 최대 가능한 차이(n)로 설정

        // 모든 간선을 하나씩 끊어보면서 탐색
        for (int i = 0; i < wires.length; i++) {

            // 인접 리스트 형태로 그래프 초기화 (노드 번호는 1~n)
            List<List<Integer>> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }

            // i번째 간선을 끊고 나머지 간선들로 그래프를 구성
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;  // i번째 간선은 끊은 상태이므로 무시
                int a = wires[j][0];
                int b = wires[j][1];

                // 양방향 간선 연결
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            // 방문 여부 체크 배열
            boolean[] visited = new boolean[n + 1];

            // 1번 노드를 기준으로 한 쪽 전력망의 송전탑 수를 DFS로 계산
            int count = dfs(1, graph, visited);

            // 다른 쪽은 전체 n에서 count를 뺀 값
            // 두 전력망의 송전탑 수 차이 계산
            int diff = Math.abs(n - count - count);

            // 최소 차이값 업데이트
            answer = Math.min(answer, diff);
        }

        return answer;  // 최소 차이값 반환
    }

    // DFS를 이용해 하나의 트리에 연결된 노드 개수 계산
    public int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true; // 현재 노드 방문 표시
        int count = 1;        // 자기 자신 포함해서 카운트 시작

        // 현재 노드에 인접한 노드들 탐색
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                // 인접 노드 중 방문하지 않은 노드에 대해 재귀적으로 DFS 호출
                count += dfs(next, graph, visited);
            }
        }

        return count;  // 이 노드와 연결된 전체 노드 수 반환
    }

}
