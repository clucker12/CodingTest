package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 2210번: 숫자판 점프
public class baekjoon2210 {

    // 5x5 숫자판
    static int[][] board = new int[5][5];

    // 중복을 피하기 위해 HashSet 사용 (만들어진 6자리 숫자를 저장)
    static Set<String> set = new HashSet<>();

    // 상하좌우 방향 이동을 위한 좌표 변화량
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 숫자판 입력 받기
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 칸에서 DFS 탐색 시작
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // 시작 위치 (i, j)에서 깊이 0, 초기 문자열은 현재 칸의 숫자
                dfs(i, j, 0, "" + board[i][j]);
            }
        }

        // HashSet에 저장된 서로 다른 6자리 수의 개수를 출력
        System.out.println(set.size());
    }

    /**
     * 깊이 우선 탐색 (DFS)
     * @param x 현재 행 좌표
     * @param y 현재 열 좌표
     * @param depth 현재 이동 횟수 (0부터 시작)
     * @param num 현재까지 만든 숫자 문자열
     */
    static void dfs(int x, int y, int depth, String num) {
        // depth == 5이면 현재까지 6자리 숫자 완성
        if (depth == 5) {
            set.add(num);  // HashSet에 추가 (자동으로 중복 제거)
            return;
        }

        // 상, 하, 좌, 우 네 방향으로 이동 시도
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];  // 다음 행 좌표
            int ny = y + dy[dir];  // 다음 열 좌표

            // 보드 범위를 벗어나지 않으면 이동
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                // 다음 칸으로 이동하면서 숫자 이어붙이기
                dfs(nx, ny, depth + 1, num + board[nx][ny]);
            }
        }
    }
}
