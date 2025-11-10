package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2615 {
    // 19x19 바둑판
    static int[][] board = new int[19][19];

    // 탐색 방향 (→, ↓, ↘, ↗)
    static int[] dx = {0, 1, 1, -1};
    static int[] dy = {1, 0, 1, 1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: 19줄의 바둑판 상태를 읽어옴
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 바둑판 전체 탐색
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {

                // 돌이 있는 칸(1 또는 2)일 때만 검사
                if (board[i][j] != 0) {
                    int color = board[i][j]; // 현재 돌 색 (1=검은색, 2=흰색)

                    // 4가지 방향(→, ↓, ↘, ↗) 각각 검사
                    for (int k = 0; k < 4; k++) {
                        // 이전 방향으로 같은 색 돌이 있으면 이미 체크한 줄이므로 스킵
                        int nx = i - dx[k];
                        int ny = j - dy[k];
                        if (inRange(nx, ny) && board[nx][ny] == color) continue;

                        int cnt = 1;  // 현재 돌 포함
                        int x = i;    // 현재 위치
                        int y = j;

                        // 같은 색 돌이 연속되는지 계속 탐색
                        while (true) {
                            x += dx[k];
                            y += dy[k];

                            // 바둑판을 벗어나거나 색이 다르면 중단
                            if (!inRange(x, y) || board[x][y] != color) break;

                            cnt++;  // 연속된 같은 색 돌 개수 증가
                        }

                        // 정확히 5개가 연속된 경우 (승리 조건)
                        if (cnt == 5) {
                            System.out.println(color);              // 승리한 색 출력
                            System.out.println((i+1)+" " +(j+1));   // 시작 위치 출력 (1-based)
                            return;                                 // 프로그램 종료
                        }
                    }
                }
            }
        }

        // 승부가 결정되지 않은 경우
        System.out.println(0);
    }

    // 좌표가 바둑판 범위 내에 있는지 확인하는 함수
    static boolean inRange(int x, int y) {
        return x >= 0 && x < 19 && y >= 0 && y < 19;
    }
}
