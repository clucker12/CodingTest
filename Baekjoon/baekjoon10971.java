package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10971 {
    // 도시 개수
    static int N;
    // 비용 행렬
    static int[][] W;
    // 방문 여부 체크
    static boolean[] visited;
    // 최소 비용 결과 저장
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 빠른 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시 개수 입력
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];           // 비용 행렬 초기화
        visited = new boolean[N];    // 방문 배열 초기화

        // 비용 행렬 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0번 도시에서 시작
        visited[0] = true;
        dfs(0, 0, 0, 1);  // 시작도시: 0, 현재도시: 0, 비용: 0, 방문한 도시 수: 1

        // 최소 비용 출력
        System.out.println(min);
    }

    /**
     * 외판원 순회 DFS 백트래킹 함수
     *
     * @param start   시작 도시 번호 (되돌아갈 도시)
     * @param current 현재 위치한 도시 번호
     * @param cost    지금까지 누적된 비용
     * @param depth   방문한 도시 수
     */
    static void dfs(int start, int current, int cost, int depth) {
        // 모든 도시를 방문한 경우 (마지막 도시에서 시작 도시로 돌아가는 경우 포함)
        if (depth == N) {
            // 마지막 도시(current)에서 시작 도시(start)로 돌아갈 수 있는 경우만
            if (W[current][start] != 0) {
                // 총 비용 = 지금까지 비용 + 되돌아가는 비용
                min = Math.min(min, cost + W[current][start]);
            }
            return;
        }

        // 현재 도시에서 다음 방문할 도시 선택
        for (int i = 0; i < N; i++) {
            // i번 도시를 방문하지 않았고, current → i로 가는 길이 있는 경우
            if (!visited[i] && W[current][i] != 0) {
                visited[i] = true;  // 방문 처리
                // 다음 도시로 이동 (비용 누적 + 방문 도시 수 증가)
                dfs(start, i, cost + W[current][i], depth + 1);
                visited[i] = false; // 백트래킹 (방문 해제)
            }
        }
    }
}
