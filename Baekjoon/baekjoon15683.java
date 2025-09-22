package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon15683 {
    static int N, M; // 사무실 크기
    static int[][] board; // 원본 맵
    static List<CCTV> cctvs = new ArrayList<>(); // CCTV 위치와 타입 저장 리스트
    static int min = Integer.MAX_VALUE; // 사각지대 최소값

    // 상, 우, 하, 좌 (← 이 순서는 중요: 방향 인덱스 기준)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // CCTV 종류별 감시 방향 조합
    static int[][][] directions = {
            {}, // 0번은 사용하지 않음
            {{0}, {1}, {2}, {3}}, // 1번 CCTV: 4방향 중 1개 선택
            {{0, 2}, {1, 3}}, // 2번 CCTV: 양쪽 (↑↓ or →←)
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번 CCTV: 직각 (↑→, →↓ 등)
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번 CCTV: 3방향
            {{0, 1, 2, 3}} // 5번 CCTV: 4방향 전부 감시
    };

    public static void main(String[] args) throws IOException {
        // 입력 받기 위한 BufferedReader와 StringTokenizer 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N, M 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        // 맵 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                // CCTV일 경우 리스트에 저장
                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, board[i][j]));
                }
            }
        }

        // DFS 시작: CCTV 방향 조합 탐색
        dfs(0, board);

        // 결과 출력: 최소 사각지대 크기
        System.out.println(min);
    }

    /**
     * DFS로 모든 CCTV의 방향 조합을 탐색
     * @param depth 현재 처리 중인 CCTV 번호 (index)
     * @param board 현재 상태의 감시 맵
     */
    static void dfs(int depth, int[][] board) {
        // 모든 CCTV의 방향을 정했으면 사각지대 계산
        if (depth == cctvs.size()) {
            min = Math.min(min, cntSpot(board)); // 최소 사각지대 갱신
            return; // 현재 재귀 종료 → 이전 단계로 되돌아감
        }

        CCTV cctv = cctvs.get(depth);
        int type = cctv.type;

        // 해당 CCTV의 모든 방향 조합을 시도
        for (int[] dirs : directions[type]) {
            // 맵 복사 (감시 영역 표시용)
            int[][] map = copyMap(board);

            // 각 방향(dir)에 대해 감시 처리
            for (int dir : dirs) {
                watch(cctv.x, cctv.y, dir, map);
            }

            // 다음 CCTV 처리
            dfs(depth + 1, map);
        }
    }

    /**
     * 특정 방향으로 감시 영역을 표시
     * @param x CCTV x 좌표
     * @param y CCTV y 좌표
     * @param dir 감시 방향 인덱스 (0~3)
     * @param map 감시할 맵
     */
    static void watch(int x, int y, int dir, int[][] map) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 맵 경계를 벗어나지 않고, 벽이 아닐 때까지 감시
        while (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] != 6) {
            if (map[nx][ny] == 0) {
                map[nx][ny] = -1; // 감시된 영역은 -1로 표시
            }

            // 다음 칸으로 이동
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    /**
     * 사각지대(0) 개수 세기
     */
    static int cntSpot(int[][] map) {
        int cnt = 0;
        for (int[] row : map) {
            for (int val : row) {
                if (val == 0) cnt++; // 빈 칸(사각지대) 카운트
            }
        }
        return cnt;
    }

    /**
     * 맵 깊은 복사
     */
    static int[][] copyMap(int[][] board) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            newMap[i] = board[i].clone(); // 1차원 배열 복사
        }
        return newMap;
    }

    /**
     * CCTV 정보를 저장하는 클래스
     */
    static class CCTV {
        int x, y, type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
