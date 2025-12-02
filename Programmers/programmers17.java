package Programmers;

import java.util.*;

public class programmers17 {
    public int solution(int n, int[][] edge) {

        // -----------------------------
        // 1. 그래프를 인접 리스트로 생성
        // -----------------------------
        // 노드 번호가 1 ~ n 이므로 인덱스를 그대로 사용하려면
        // 크기를 n+1 로 만들어야 한다 (0번은 사용하지 않음)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // -----------------------------
        // 2. 간선 정보를 이용해 그래프 연결
        // -----------------------------
        // 양방향 그래프이므로 서로 추가
        for (int[] vertex : edge){
            int a = vertex[0];
            int b = vertex[1];

            graph.get(a).add(b); // a → b 연결
            graph.get(b).add(a); // b → a 연결
        }

        // -----------------------------
        // 3. BFS 준비
        // -----------------------------
        // dist[i] = 1번 노드에서 i번 노드까지의 최단 거리
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1); // -1은 "아직 방문하지 않음"을 의미한다

        Queue<Integer> q = new LinkedList<>();

        // 시작점은 1번 노드
        dist[1] = 0; // 자기 자신까지의 거리는 0
        q.add(1);

        // -----------------------------
        // 4. BFS 수행하여 최단 거리 계산
        // -----------------------------
        while (!q.isEmpty()) {
            int cur = q.poll(); // 현재 노드 꺼냄

            // 인접한 노드 탐색
            for (int next : graph.get(cur)) {
                // 방문하지 않은 노드라면
                if (dist[next] == -1) {
                    // 현재 노드까지 거리 + 1
                    dist[next] = dist[cur] + 1;
                    q.add(next); // 다음 탐색 큐에 추가
                }
            }
        }

        // -----------------------------
        // 5. 가장 멀리 떨어진 거리(maxDist) 찾기
        // -----------------------------
        int maxDist = 0;
        for (int d : dist) {
            if (d > maxDist) maxDist = d;
        }

        // -----------------------------
        // 6. 가장 먼 거리와 같은 노드 개수 세기
        // -----------------------------
        int answer = 0;
        for (int d : dist) {
            if (d == maxDist) answer++;
        }

        return answer;
    }
}
