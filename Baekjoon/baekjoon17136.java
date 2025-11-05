package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17136 {
    // 10x10 크기의 종이 (0: 빈칸, 1: 색종이 붙여야 할 칸)
    static int[][] board = new int[10][10];

    // 색종이 남은 개수 (1x1 ~ 5x5까지 각각 5장씩)
    // 인덱스를 1~5로 쓰기 위해 0번째는 더미 값
    static int[] remain = {0, 5, 5, 5, 5, 5};

    // 최소 사용 색종이 수 (답)
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: 10줄에 걸쳐 10개의 숫자 (0 또는 1)
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS 탐색 시작 (pos = 0: 첫 번째 칸, used = 0: 사용한 색종이 수)
        dfs(0, 0);

        // 모든 경우를 탐색했는데도 덮을 수 없는 경우 => -1 출력
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    /**
     * DFS (백트래킹)
     * pos  : 현재 검사 중인 칸 번호 (0~99)
     * used : 지금까지 사용한 색종이 개수
     */
    static void dfs(int pos, int used) {
        // 이미 찾은 최소값보다 많이 쓴 경우는 더 볼 필요 없음 (가지치기)
        if (used >= answer) return;

        // 아직 100칸 다 안봤으면, 다음 1을 찾을 때까지 pos 이동
        while (pos < 100) {
            int x = pos / 10;  // 행
            int y = pos % 10;  // 열
            if (board[x][y] == 1) break;  // 색종이 붙여야 하는 칸 찾으면 중단
            pos++;  // 다음 칸으로 이동
        }

        // 100칸(0~99) 다 돌았으면 모든 칸이 덮인 상태 → 답 갱신
        if (pos == 100) {
            answer = Math.min(answer, used);
            return;
        }

        // 현재 칸 좌표 계산
        int x = pos / 10;
        int y = pos % 10;

        // 혹시 현재 칸이 0이면 (이미 덮인 경우) 다음 칸으로
        if (board[x][y] == 0) {
            dfs(pos + 1, used);
            return;
        }

        // 색종이 크기를 5x5 → 1x1 순으로 시도
        for (int size = 5; size >= 1; size--) {
            // 해당 크기의 색종이 다 썼으면 건너뜀
            if (remain[size] == 0) continue;

            // 현재 위치에 size 크기의 색종이를 붙일 수 있으면
            if (canAttach(x, y, size)) {
                // 붙이기 (board값을 0으로 바꿈)
                attach(x, y, size, 0);
                remain[size]--;  // 남은 색종이 개수 줄임

                // 다음 칸 탐색
                dfs(pos + 1, used + 1);

                // 되돌리기 (원상복구, 백트래킹)
                attach(x, y, size, 1);
                remain[size]++;
            }
        }
    }

    /**
     * 현재 (x, y)에 size 크기의 색종이를 붙일 수 있는지 확인
     */
    static boolean canAttach(int x, int y, int size) {
        // 색종이가 종이 범위를 벗어나면 불가능
        if (x + size > 10 || y + size > 10) return false;

        // size x size 영역 안에 1이 아닌 칸이 있으면 불가능
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true; // 모두 1이면 붙일 수 있음
    }

    /**
     * 색종이 붙이기 / 떼기
     * val = 0 → 색종이 붙이기 (1 → 0)
     * val = 1 → 색종이 떼기 (0 → 1)
     */
    static void attach(int x, int y, int size, int val) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = val;
            }
        }
    }
}
