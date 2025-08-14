package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon9663 {
    static int N;               // 체스판의 크기 (N x N)
    static int count = 0;       // 정답 개수를 저장하는 변수
    static boolean[] col;       // 열에 퀸이 있는지 여부 체크
    static boolean[] diag1;     // / 방향 대각선 (↗↙) 체크: row + col
    static boolean[] diag2;     // \ 방향 대각선 (↖↘) 체크: row - col + (N - 1)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 사용자로부터 체스판 크기 N 입력받음

        col = new boolean[N];             // 각 열에 퀸이 있는지 체크 (0 ~ N-1)
        diag1 = new boolean[2 * N - 1];   // / 대각선은 최대 (row + col) = 2N - 2 → 인덱스 범위 0 ~ 2N - 2
        diag2 = new boolean[2 * N - 1];   // \ 대각선은 row - col 값이 -N+1 ~ N-1 → 양수로 바꾸기 위해 + N - 1

        backtrack(0);                     // 첫 번째 행부터 퀸을 놓기 시작
        System.out.println(count);       // 가능한 배치 수 출력
    }

    static void backtrack(int row) {
        // row == N 이면 모든 행에 퀸을 놓은 것이므로 경우 1개 완료
        if (row == N) {
            count++;
            return;
        }

        // 현재 행(row)의 모든 열(c)을 순회하며 퀸을 놓을 수 있는지 확인
        for (int c = 0; c < N; c++) {
            // 아래 조건 중 하나라도 true이면 퀸을 놓을 수 없음 → continue로 건너뜀
            if (col[c] || diag1[row + c] || diag2[row - c + N - 1])
                continue;

            // 퀸 놓기 → 현재 위치 (row, c)
            col[c] = true;                  // 열 사용 표시
            diag1[row + c] = true;          // / 대각선 사용 표시
            diag2[row - c + N - 1] = true;  // \ 대각선 사용 표시

            backtrack(row + 1);             // 다음 행으로 이동

            // 백트래킹: 놓았던 퀸을 제거하고 다른 위치 탐색
            col[c] = false;                 // 열 사용 해제
            diag1[row + c] = false;         // / 대각선 사용 해제
            diag2[row - c + N - 1] = false; // \ 대각선 사용 해제
        }
    }
}
