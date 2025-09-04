package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2468 {

    // N: 지도 크기 (N x N), s: 높이 정보 배열
    static int N;
    static int[][] s;

    // 상하좌우 방향 벡터
    static int[] dx = {-1, 1, 0, 0}; // 위, 아래
    static int[] dy = {0, 0, -1, 1}; // 왼쪽, 오른쪽

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N 입력
        N = Integer.parseInt(br.readLine());
        s = new int[N][N];

        int maxHeight = 0; // 지역 내 최대 높이 저장

        // 높이 정보 입력 받으면서 maxHeight 갱신
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, s[i][j]);
            }
        }

        int maxSafeArea = 0; // 최대 안전 영역 개수

        // 비의 양을 0부터 maxHeight-1까지 변화시키며 시뮬레이션
        for (int rain = 0; rain < maxHeight; rain++) {
            boolean[][] visited = new boolean[N][N]; // 방문 여부 배열
            int cnt = 0; // 현재 비의 높이에서의 안전 영역 개수

            // 전체 지역을 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 해당 지점이 비에 잠기지 않았고, 아직 방문하지 않았다면
                    if (!visited[i][j] && s[i][j] > rain) {
                        // DFS 탐색 시작 (연결된 안전 영역 전체 탐색)
                        dfs(i, j, rain, visited);
                        cnt++; // 하나의 안전 영역 발견 → 개수 증가
                    }
                }
            }

            // 지금까지의 최대 안전 영역 개수 갱신
            maxSafeArea = Math.max(maxSafeArea, cnt);
        }

        // 정답 출력
        System.out.println(maxSafeArea);
    }

    // DFS: 상하좌우로 연결된 안전 지역을 탐색하며 visited 표시
    static void dfs(int x, int y, int rain, boolean[][] visited) {
        visited[x][y] = true; // 현재 지점 방문 처리

        // 4방향 모두 탐색
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir]; // 다음 위치의 x
            int ny = y + dy[dir]; // 다음 위치의 y

            // 경계 체크
            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                // 다음 지점이 비에 잠기지 않고, 아직 방문하지 않았다면
                if (!visited[nx][ny] && s[nx][ny] > rain) {
                    // 재귀적으로 DFS 탐색 계속
                    dfs(nx, ny, rain, visited);
                }
            }
        }
    }
}
