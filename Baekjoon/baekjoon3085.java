package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon3085 {
    // 보드 크기 N
    static int N;

    // 사탕 색상 보드를 저장할 2차원 배열
    static char[][] candy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력 받기
        N = Integer.parseInt(br.readLine());

        // 사탕 보드 배열 초기화
        candy = new char[N][N];

        // 보드 정보 입력 받기 (각 행마다 한 줄씩)
        for (int i = 0; i < N; i++) {
            String line = br.readLine(); // 한 줄 입력
            for (int j = 0; j < N; j++) {
                candy[i][j] = line.charAt(j); // 문자 하나씩 배열에 저장
            }
        }

        int result = 0; // 최대 사탕 개수 결과 저장 변수

        // 보드의 모든 칸을 순회
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // 오른쪽 칸과 교환
                if (j + 1 < N) {
                    // 사탕 색이 다른 경우만 교환 (❗주의: 이 조건은 없어도 됨. 문제 설명 참조)
                    if (candy[i][j] != candy[i][j + 1]) {
                        swap(i, j, i, j + 1); // 교환
                        result = Math.max(result, checkMaxCandy()); // 최대 길이 계산
                        swap(i, j, i, j + 1); // 원상복구
                    }
                }

                // 아래쪽 칸과 교환
                if (i + 1 < N) {
                    if (candy[i][j] != candy[i + 1][j]) {
                        swap(i, j, i + 1, j);
                        result = Math.max(result, checkMaxCandy());
                        swap(i, j, i + 1, j);
                    }
                }
            }
        }

        // 정답 출력
        System.out.println(result);
    }

    // 두 칸의 사탕을 교환하는 함수
    static void swap(int x1, int y1, int x2, int y2) {
        char temp = candy[x1][y1];
        candy[x1][y1] = candy[x2][y2];
        candy[x2][y2] = temp;
    }

    // 현재 보드 상태에서 가장 긴 연속된 사탕 개수를 계산하는 함수
    static int checkMaxCandy() {
        int max = 1; // 최대 사탕 길이 (최소는 1)

        // 각 행과 열을 검사
        for (int i = 0; i < N; i++) {
            int rowcnt = 1; // 행 연속 카운트
            int colcnt = 1; // 열 연속 카운트

            for (int j = 1; j < N; j++) {
                // 같은 행에서 왼쪽 사탕과 같은 색인지 확인
                if (candy[i][j] == candy[i][j - 1]) {
                    rowcnt++; // 연속 증가
                } else {
                    rowcnt = 1; // 연속 끊김 → 1로 초기화
                }
                max = Math.max(max, rowcnt); // 최대값 갱신

                // 같은 열에서 위쪽 사탕과 같은 색인지 확인
                if (candy[j][i] == candy[j - 1][i]) {
                    colcnt++;
                } else {
                    colcnt = 1;
                }
                max = Math.max(max, colcnt); // 최대값 갱신
            }
        }

        return max;
    }
}
