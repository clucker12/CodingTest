package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1018 {
    static int N, M;               // 보드의 크기 N행 M열
    static char[][] board;         // 보드 상태를 저장하는 2차원 배열
    static int min = Integer.MAX_VALUE; // 최소 다시 칠해야 할 칸 수 (초기값은 매우 큰 수)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄에서 N과 M 읽기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 보드 배열 초기화
        board = new char[N][M];

        // 보드 상태 입력 받기
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                // 각 칸의 색깔('B' 또는 'W')를 char로 저장
                board[i][j] = line.charAt(j);
            }
        }

        // 8x8 체스판을 만들 수 있는 모든 시작 위치에 대해서 검사
        // i, j가 시작 좌표로, 8x8 범위를 벗어나지 않도록 <= N-8, M-8 까지 반복
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                check(i, j); // (i,j)부터 8x8 부분을 검사하는 함수 호출
            }
        }

        // 최종적으로 최소 칠해야 할 개수를 출력
        System.out.println(min);
    }

    // (startX, startY)부터 시작하는 8x8 영역에서 다시 칠해야 하는 최소 칸 수를 계산하는 함수
    static void check(int startX, int startY) {
        int countStartW = 0; // 체스판 시작 칸이 'W'일 때 다시 칠해야 하는 칸 수
        int countStartB = 0; // 체스판 시작 칸이 'B'일 때 다시 칠해야 하는 칸 수

        // 8x8 범위 내에서 모든 칸을 순회
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 실제 보드의 좌표 계산
                int x = startX + i;
                int y = startY + j;
                char current = board[x][y]; // 현재 칸 색상

                // (i + j) % 2 == 0 인 칸은 체스판 시작 칸과 같은 색이어야 함
                if ((i + j) % 2 == 0) {
                    // 시작 칸이 'W'인 체스판으로 볼 때,
                    // 현재 칸이 'W'가 아니면 다시 칠해야 함
                    if (current != 'W') countStartW++;

                    // 시작 칸이 'B'인 체스판으로 볼 때,
                    // 현재 칸이 'B'가 아니면 다시 칠해야 함
                    if (current != 'B') countStartB++;

                    // (i + j) % 2 == 1 인 칸은 체스판 시작 칸과 반대 색이어야 함
                } else {
                    // 시작 칸이 'W'인 경우, 이 칸은 'B'여야 하므로
                    // 현재 칸이 'B'가 아니면 다시 칠해야 함
                    if (current != 'B') countStartW++;

                    // 시작 칸이 'B'인 경우, 이 칸은 'W'여야 하므로
                    // 현재 칸이 'W'가 아니면 다시 칠해야 함
                    if (current != 'W') countStartB++;
                }
            }
        }

        // 시작 칸이 'W'인 경우와 'B'인 경우 중 더 적은 칸을 다시 칠해야 하므로 최소값 계산
        int localMin = Math.min(countStartW, countStartB);

        // 전체 보드에서 최소 칠해야 할 개수와 비교하여 더 작은 값으로 갱신
        min = Math.min(min, localMin);
    }
}
