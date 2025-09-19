package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon12100 {
    static int N;                  // 보드의 크기 (N x N)
    static int[][] board;         // 초기 보드 상태
    static int max = 0;           // 만들 수 있는 최대 블록 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        // 보드 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS 시작 (최대 5번 이동)
        dfs(1, board);

        // 결과 출력
        System.out.println(max);
    }

    // 깊이 우선 탐색으로 모든 이동 시나리오를 탐색
    static void dfs(int depth, int[][] board) {
        // 5번 이동했으면 최대값 갱신
        if (depth > 5) {
            max = Math.max(max, getMax(board));
            return;
        }

        // 4가지 방향으로 이동 시도
        for (int i = 0; i < 4; i++) {
            int[][] newBoard = move(board, i);  // 현재 보드를 복사해서 이동
            dfs(depth + 1, newBoard);           // 다음 단계로 탐색
        }
    }

    // 보드를 복사하고 방향에 따라 이동
    static int[][] move(int[][] board, int dir) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            newBoard[i] = board[i].clone();  // 깊은 복사 (행 기준)
        }

        // 방향에 따라 이동 함수 호출
        switch (dir) {
            case 0: moveUp(newBoard); break;
            case 1: moveDown(newBoard); break;
            case 2: moveLeft(newBoard); break;
            case 3: moveRight(newBoard); break;
        }

        return newBoard;
    }

    // 위로 이동
    static void moveUp(int[][] board) {
        for (int j = 0; j < N; j++) {
            int[] newCol = new int[N];  // 이동 후 열을 저장
            int idx = 0;                // newCol에 넣을 위치
            int last = 0;               // 마지막으로 본 숫자 (합치기용)

            for (int i = 0; i < N; i++) {
                if (board[i][j] == 0) continue;
                if (last == 0) {
                    last = board[i][j];
                } else {
                    if (last == board[i][j]) {
                        newCol[idx++] = last * 2;  // 합치기
                        last = 0;
                    } else {
                        newCol[idx++] = last;      // 다른 숫자라면 이전 숫자 넣고 갱신
                        last = board[i][j];
                    }
                }
            }
            if (last != 0) newCol[idx] = last;      // 남은 숫자 처리

            for (int i = 0; i < N; i++) {
                board[i][j] = newCol[i];
            }
        }
    }

    // 아래로 이동
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

    // 왼쪽으로 이동
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

            board[i] = newRow;
        }
    }

    // 오른쪽으로 이동
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

    // 보드 내 최댓값 구하기
    static int getMax(int[][] board) {
        int val = 0;
        for (int[] row : board) {
            for (int num : row) {
                val = Math.max(val, num);
            }
        }
        return val;
    }
}
