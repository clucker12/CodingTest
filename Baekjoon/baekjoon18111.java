package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon18111 {

    // 전역 변수 선언
    static int N, M, B;               // N: 세로, M: 가로, B: 인벤토리 블록 수
    static int[][] groud;            // 땅 높이를 저장할 2차원 배열
    static int maxHeight = 0;        // 결과로 나올 가장 높은 땅 높이
    static int minTime = Integer.MAX_VALUE;  // 최소 작업 시간 (초기값: 아주 큰 수)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 줄 입력: N, M, B
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기
        B = Integer.parseInt(st.nextToken()); // 인벤토리 블록 수

        groud = new int[N][M]; // 땅 배열 초기화
        int min = 256;         // 땅 높이의 최소값 (최소 높이 기준)
        int max = 0;           // 땅 높이의 최대값 (최대 높이 기준)

        // 땅의 높이 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                groud[i][j] = Integer.parseInt(st.nextToken()); // (i,j) 위치의 땅 높이
                min = Math.min(min, groud[i][j]); // 최소 높이 갱신
                max = Math.max(max, groud[i][j]); // 최대 높이 갱신
            }
        }

        // 가능한 높이(min~max)마다 시도
        for (int h = min; h <= max; h++) {
            int remove = 0; // 제거할 블록 수
            int add = 0;    // 추가할 블록 수

            // 모든 좌표에 대해 블록을 제거/추가하는 데 필요한 수 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int height = groud[i][j]; // 현재 좌표의 높이

                    if (height > h) {
                        // 현재 높이가 목표보다 높으면 제거
                        remove += height - h;
                    } else {
                        // 현재 높이가 목표보다 낮으면 추가
                        add += h - height;
                    }
                }
            }

            // 인벤토리에 있는 블록 + 제거한 블록 >= 추가할 블록이어야 가능
            if (remove + B >= add) {
                int currentTime = remove * 2 + add; // 제거는 2초, 추가는 1초

                // 최소 시간일 경우 또는 같은 시간이지만 더 높은 높이를 만들 수 있으면 갱신
                if (currentTime < minTime || (currentTime == minTime && h > maxHeight)) {
                    minTime = currentTime;
                    maxHeight = h;
                }
            }
        }

        // 결과 출력: 최소 시간, 해당하는 최대 높이
        System.out.println(minTime + " " + maxHeight);
    }
}
