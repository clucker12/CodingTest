package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class programmers12 {

    // 4방향 이동을 위한 배열 (동, 서, 남, 북)
    public static int[] dx = {0, 0, 1, -1};  // x 좌표 (가로 방향)
    public static int[] dy = {1, -1, 0, 0};  // y 좌표 (세로 방향)

    public int solution(int[][] maps) {
        // 최단 경로를 저장할 변수
        int answer = 0;

        // 맵의 크기 (n은 세로, m은 가로)
        int n = maps.length;
        int m = maps[0].length;

        // 큐 초기화 (BFS에 사용할 큐)
        Queue<int[]> queue = new LinkedList<>();

        // 각 칸까지의 최단 거리를 기록하는 배열
        int[][] distances = new int[n][m];

        // distances 배열을 0으로 초기화
        for(int[] row : distances){
            Arrays.fill(row, 0);  // 모든 칸을 0으로 설정 (0은 아직 방문하지 않은 칸)
        }

        // 시작점 (0, 0)을 큐에 넣고, 해당 위치의 거리를 1로 설정
        queue.offer(new int[]{0, 0});
        distances[0][0] = 1;  // 시작점은 1칸이므로 1로 설정

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            // 큐에서 하나의 위치를 꺼냄
            int[] current = queue.poll();
            int x = current[0];  // 현재 위치의 x 좌표
            int y = current[1];  // 현재 위치의 y 좌표

            // 목표 지점 (n-1, m-1)에 도달하면 그때까지의 거리 반환
            if (x == n - 1 && y == m - 1) {
                return distances[x][y];  // 목표 지점까지의 최단 거리 반환
            }

            // 4방향으로 인접한 칸을 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];  // x 방향으로 이동
                int ny = y + dy[i];  // y 방향으로 이동

                // 이동한 위치가 유효한 범위 내에 있고, 벽이 아니며, 아직 방문하지 않은 곳이면
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1 && distances[nx][ny] == 0) {
                    // 해당 칸의 거리를 갱신
                    distances[nx][ny] = distances[x][y] + 1;
                    // 큐에 새 위치를 추가
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        // 목표 지점에 도달할 수 없는 경우에는 -1을 반환
        return -1;
    }
}
