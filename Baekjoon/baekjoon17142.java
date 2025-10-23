package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon17142 {
    static int N, M;  // 실험실 크기 N과 바이러스 개수 M
    static int[][] lab;  // 실험실 맵을 나타내는 2D 배열
    static List<int[]> virusLocations = new ArrayList<>();  // 바이러스가 있는 위치를 저장하는 리스트
    static int[] dx = {-1, 1, 0, 0};  // 상, 하 방향으로 이동하기 위한 배열
    static int[] dy = {0, 0, -1, 1};  // 좌, 우 방향으로 이동하기 위한 배열
    static int minTime = Integer.MAX_VALUE;  // 최소 시간 (초기값은 무한대)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 실험실 크기 N과 바이러스 개수 M 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];  // 실험실 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());  // 실험실 각 칸의 상태 입력
                if (lab[i][j] == 2) {
                    // 바이러스 위치를 리스트에 추가
                    virusLocations.add(new int[]{i, j});
                }
            }
        }

        // 바이러스를 선택할 수 있는 모든 경우의 조합을 구하고, 각각에 대해 BFS를 실행
        combination(0, 0, new int[M]);

        // 결과 출력: 최소 시간이 갱신되지 않으면 -1을 출력 (불가능한 경우)
        if (minTime == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minTime);
        }
    }

    // 조합을 이용해 바이러스 위치를 M개 선택하는 함수
    static void combination(int start, int depth, int[] selected) {
        // M개 바이러스를 선택한 경우, BFS 실행
        if (depth == M) {
            bfs(selected);
            return;
        }

        // 바이러스를 조합하여 선택 (백트래킹 방식)
        for (int i = start; i < virusLocations.size(); i++) {
            selected[depth] = i;  // 선택된 바이러스의 인덱스 기록
            combination(i + 1, depth + 1, selected);  // 다음 깊이로 진행
        }
    }

    // BFS를 통해 바이러스가 퍼지는 시간을 계산하는 함수
    static void bfs(int[] selected) {
        int[][] visited = new int[N][N];  // 방문 여부를 나타내는 배열
        Queue<int[]> queue = new LinkedList<>();  // BFS를 위한 큐
        int time = 0;  // 최소 시간을 계산할 변수

        // 선택된 바이러스들을 큐에 넣고 시작
        for (int i = 0; i < M; i++) {
            int[] virus = virusLocations.get(selected[i]);
            int x = virus[0];
            int y = virus[1];
            queue.offer(new int[]{x, y, 0});  // (x, y) 좌표와 시간 0을 큐에 넣음
            visited[x][y] = 1;  // 바이러스가 있는 칸은 방문 처리
        }

        // BFS 수행
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int t = current[2];  // 현재 시간

            // 상하좌우로 이동하면서 전파
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];  // 다음 x 좌표
                int ny = y + dy[i];  // 다음 y 좌표

                // 범위 체크 및 벽이 아닌 곳만 이동
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0 && lab[nx][ny] != 1) {
                    visited[nx][ny] = 1;  // 방문 처리
                    // 만약 빈 공간(0)이라면 시간 갱신 후 큐에 추가
                    if (lab[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, t + 1});  // (nx, ny)와 새로운 시간 t+1을 큐에 넣음
                        time = Math.max(time, t + 1);  // 가장 오래 걸린 시간 갱신
                    } else {
                        queue.offer(new int[]{nx, ny, t + 1});  // 벽(1)은 아니지만, 바이러스 전파가 필요하므로 큐에 넣음
                    }
                }
            }
        }

        // 모든 빈 칸이 감염되었는지 확인하고, 가능하다면 최소 시간 갱신
        if (checkAllInfected(visited)) {
            minTime = Math.min(minTime, time);  // 최소 시간 계산
        }
    }

    // 모든 빈 칸이 감염되었는지 확인하는 함수
    static boolean checkAllInfected(int[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lab[i][j] == 0 && visited[i][j] == 0) {  // 빈 칸이 아직 감염되지 않았다면
                    return false;  // 감염되지 않은 빈 칸이 있으면 false
                }
            }
        }
        return true;  // 모든 빈 칸이 감염되었으면 true
    }
}
