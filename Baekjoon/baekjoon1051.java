package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1051 {
    // N: 행 개수, M: 열 개수
    static int N, M;

    // 2차원 배열: 숫자들이 들어있는 직사각형
    static int[][] board;

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력 (N, M)
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        // 배열 초기화
        board = new int[N][M];

        // 숫자판 입력 받기 (줄마다 한 줄씩, 각 글자를 숫자로 변환)
        for (int i = 0; i < N; i++) {
            String line = br.readLine(); // 예: "42101"
            for (int j = 0; j < M; j++) {
                // 문자를 숫자로 변환해서 배열에 저장
                board[i][j] = line.charAt(j) - '0';
            }
        }

        // 현재까지 찾은 가장 큰 정사각형의 한 변 길이 (초기값 1)
        int maxLen = 1;

        // 모든 좌표 (i,j) 에서 시작 가능한 정사각형을 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 정사각형의 크기를 하나씩 늘려가며 확인
                for (int size = 1; i + size < N && j + size < M; size++) {
                    int val = board[i][j]; // 기준 꼭짓점의 숫자

                    // 정사각형의 4 꼭짓점이 같은 숫자인지 확인
                    if (board[i][j + size] == val &&        // 우측 상단
                            board[i + size][j] == val &&        // 좌측 하단
                            board[i + size][j + size] == val) { // 우측 하단

                        // 4 꼭짓점이 모두 같다면, 최대 길이 갱신
                        maxLen = Math.max(maxLen, size + 1);
                    }
                }
            }
        }

        // 정사각형의 넓이 = 한 변의 길이 제곱
        System.out.println(maxLen * maxLen);
    }
}
