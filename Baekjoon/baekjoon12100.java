package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon12100 {
    static int N;                  // 보드의 크기 (N x N)
    static int[][] board;         // 초기 보드 상태 저장 배열
    static int max = 0;           // 이동을 통해 만들 수 있는 최대 블록 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N 입력 (보드의 크기)
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        // 보드의 초기 상태 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS 탐색 시작 (최대 5번 이동 가능하므로 depth 1부터 시작)
        dfs(1, board);

        // 결과 출력 (만들 수 있는 최대 블록 값)
        System.out.println(max);
    }

    /**
     * DFS (깊이 우선 탐색)를 통해 가능한 모든 이동 시나리오 탐색
     * @param depth 현재 이동 횟수 (최대 5까지)
     * @param board 현재 보드 상태
     */
    static void dfs(int depth, int[][] board) {
        // 5번 이동 완료했으면 현재 보드에서 최댓값 찾아서 max 갱신
        if (depth > 5) {
            max = Math.max(max, getMax(board));
            return;
        }

        // 상(0), 하(1), 좌(2), 우(3) 4가지 방향으로 이동 시도
        for (int i = 0; i < 4; i++) {
            int[][] newBoard = move(board, i);  // 현재 보드 복사 후 이동
            dfs(depth + 1, newBoard);           // 다음 단계로 재귀 호출
        }
    }

    /**
     * 주어진 방향으로 보드를 이동시키고 새로운 보드 반환
     * @param board 현재 보드 상태
     * @param dir 이동 방향 (0: 상, 1: 하, 2: 좌, 3: 우)
     * @return 이동 후 보드 상태
     */
    static int[][] move(int[][] board, int dir) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            newBoard[i] = board[i].clone();  // 행 단위로 깊은 복사
        }

        // 방향에 따라 이동 수행
        switch (dir) {
            case 0: moveUp(newBoard); break;
            case 1: moveDown(newBoard); break;
            case 2: moveLeft(newBoard); break;
            case 3: moveRight(newBoard); break;
        }

        return newBoard;
    }

    /**
     * 보드를 위쪽으로 이동시키는 함수
     * 같은 숫자는 한 번만 합쳐짐
     */
    static void moveUp(int[][] board) {
        for (int j = 0; j < N; j++) { // 열 단위로 처리
            int[] newCol = new int[N];  // 이동 후 열 결과 저장
            int idx = 0;                // newCol에 값을 채울 인덱스
            int last = 0;               // 마지막으로 본 숫자 (합칠 대상)

            for (int i = 0; i < N; i++) {
                if (board[i][j] == 0) continue; // 0은 무시
                if (last == 0) {
                    last = board[i][j];         // 첫 값 저장
                } else {
                    if (last == board[i][j]) {
                        newCol[idx++] = last * 2; // 같은 수 -> 합치기
                        last = 0;                 // 합쳤으니 초기화
                    } else {
                        newCol[idx++] = last;     // 다르면 last 저장
                        last = board[i][j];       // 현재 값으로 갱신
                    }
                }
            }
            if (last != 0) newCol[idx] = last; // 남은 숫자 저장

            // 계산된 열을 보드에 반영
            for (int i = 0; i < N; i++) {
                board[i][j] = newCol[i];
            }
        }
    }

    /**
     * 아래 방향으로 이동
     */
    static void moveDown(int[][] board) {
        for (int j = 0; j < N; j++) {
            int[] newCol = new int[N];
            int idx = N - 1;
            int last = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] == 0) continue;
                if (last == 0) {
                    last = board[i][j];
                } else {
                    if (last == board[i][j]) {
                        newCol[idx--] = last * 2;
                        last = 0;
                    } else {
                        newCol[idx--] = last;
                        last = board[i][j];
                    }
                }
            }
            if (last != 0) newCol[idx] = last;

            for (int i = 0; i < N; i++) {
                board[i][j] = newCol[i];
            }
        }
    }

    /**
     * 왼쪽 방향으로 이동
     */
    static void moveLeft(int[][] board) {
        for (int i = 0; i < N; i++) {
            int[] newRow = new int[N];
            int idx = 0;
            int last = 0;

            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) continue;
                if (last == 0) {
                    last = board[i][j];
                } else {
                    if (last == board[i][j]) {
                        newRow[idx++] = last * 2;
                        last = 0;
                    } else {
                        newRow[idx++] = last;
                        last = board[i][j];
                    }
                }
            }
            if (last != 0) newRow[idx] = last;

            board[i] = newRow; // 계산된 행을 보드에 반영
        }
    }

    /**
     * 오른쪽 방향으로 이동
     */
    static void moveRight(int[][] board) {
        for (int i = 0; i < N; i++) {
            int[] newRow = new int[N];
            int idx = N - 1;
            int last = 0;

            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] == 0) continue;
                if (last == 0) {
                    last = board[i][j];
                } else {
                    if (last == board[i][j]) {
                        newRow[idx--] = last * 2;
                        last = 0;
                    } else {
                        newRow[idx--] = last;
                        last = board[i][j];
                    }
                }
            }
            if (last != 0) newRow[idx] = last;

            board[i] = newRow;
        }
    }

    /**
     * 보드 내에서 가장 큰 블록 값을 반환
     * @param board 현재 보드 상태
     * @return 최댓값
     */
    static int getMax(int[][] board) {
        int val = 0;
        for (int[] row : board) {
            for (int num : row) {
                val = Math.max(val, num); // 현재 값과 비교해 최댓값 갱신
            }
        }
        return val;
    }
}
