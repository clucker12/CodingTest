package Programmers;
import java.util.*;

public class programmers14 {
    // 지도 배열과 방문 여부 배열 선언
    static int[][] map;
    static boolean[][] visited;
    // 상하좌우 이동 방향
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[102][102]; // 좌표를 2배로 확장해 모서리 겹침 방지
        visited = new boolean[102][102]; // 방문 체크 배열

        // 직사각형을 2배 확장 좌표로 map에 표시 (1로)
        for (int[] rec : rectangle) {
            int x1 = rec[0]*2;
            int y1 = rec[1]*2;
            int x2 = rec[2]*2;
            int y2 = rec[3]*2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = 1; // 직사각형 영역 전체를 1로 표시
                }
            }
        }

        // 직사각형 내부를 제거하여 외곽선만 남김
        for (int[] rec : rectangle) {
            int x1 = rec[0]*2;
            int y1 = rec[1]*2;
            int x2 = rec[2]*2;
            int y2 = rec[3]*2;

            for (int i = x1+1; i < x2; i++) {
                for (int j = y1+1; j < y2; j++) {
                    map[i][j] = 0; // 내부는 0으로
                }
            }
        }

        // BFS를 위한 큐 초기화, 시작 위치 넣기
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{characterX*2, characterY*2, 0}); // x, y, 거리
        visited[characterX*2][characterY*2] = true;

        // BFS 시작
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];

            // 아이템 위치에 도달하면 거리 반환 (2배 확장했으므로 2로 나눔)
            if (x == itemX*2 && y == itemY*2) {
                return dist / 2;
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 지도 범위 내이며, 방문하지 않았고 외곽선이면 이동
                if (nx >= 0 && nx <= 101 && ny >= 0 && ny <= 101) {
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1}); // 거리 +1
                    }
                }
            }
        }

        return 0; // 도달 불가 시
    }
}
