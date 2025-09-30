package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon2589 {
    static int L, W;                 // L: 세로 크기, W: 가로 크기
    static char[][] road;           // 지도 정보 저장 (육지: 'L', 바다: 'W')
    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌 방향 이동을 위한 배열
    static int[] dy = {0, 1, 0, -1};

    // BFS에 사용할 Point 클래스 (좌표와 현재 거리)
    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 줄에서 지도 크기 입력 받기
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 세로
        W = Integer.parseInt(st.nextToken()); // 가로

        road = new char[L][W]; // 지도 크기만큼 배열 초기화

        // 지도 정보 입력 받기 (L과 W가 공백 없이 주어짐)
        for (int i = 0; i < L; i++) {
            String line = br.readLine(); // 한 줄씩 입력 받음
            for (int j = 0; j < W; j++) {
                road[i][j] = line.charAt(j); // 문자 하나씩 지도에 저장
            }
        }

        int maxDistance = 0; // 가장 먼 거리 저장할 변수

        // 모든 육지 칸에서 BFS 수행
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < W; j++) {
                if (road[i][j] == 'L'){ // 육지인 경우만 BFS 수행
                    int dist = bfs(i, j); // 해당 위치에서 가장 먼 거리 구하기
                    maxDistance = Math.max(maxDistance, dist); // 최대값 갱신
                }
            }
        }

        // 결과 출력: 육지 간 최장 최단 거리
        System.out.println(maxDistance);
    }

    // BFS 탐색 함수: 시작점에서 가장 먼 육지까지 거리 구하기
    static int bfs(int startX, int startY){
        boolean[][] visited = new boolean[L][W]; // 방문 여부 체크
        Queue<Point> queue = new LinkedList<>(); // BFS용 큐

        // 시작점 큐에 삽입
        queue.add(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        int max = 0; // BFS 중 가장 먼 거리 저장

        while (!queue.isEmpty()) {
            Point current = queue.poll(); // 현재 좌표 꺼내기
            max = Math.max(max, current.dist); // 거리 최대값 갱신

            // 4방향 이동
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i]; // 이동한 x좌표
                int ny = current.y + dy[i]; // 이동한 y좌표

                // 범위 안에 있고, 아직 방문 안 했고, 육지일 때만 이동
                if (nx >= 0 && ny >= 0 && nx < L && ny < W) {
                    if (!visited[nx][ny] && road[nx][ny] == 'L') {
                        visited[nx][ny] = true; // 방문 처리
                        queue.add(new Point(nx, ny, current.dist + 1)); // 다음 좌표 큐에 삽입
                    }
                }
            }
        }

        return max; // 가장 멀리 이동한 거리 반환
    }
}
