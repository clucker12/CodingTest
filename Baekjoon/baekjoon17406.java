package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon17406 {
    // 전역 변수 선언
    static int N, M, K;            // 배열 크기 N×M, 회전 연산 개수 K
    static int[][] board;          // 원본 배열
    static int[][] rotation;       // 회전 연산 정보 (r, c, s)
    static boolean[] visited;      // 순열 생성 시 사용 여부 체크
    static int[] order;            // 회전 연산 순서 저장용
    static int answer = Integer.MAX_VALUE; // 결과(최소값)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // --- 입력 ---
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        rotation = new int[K][3]; // 회전 연산 K개, 각 (r, c, s)

        // 배열 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 연산 입력 받기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotation[i][0] = Integer.parseInt(st.nextToken()); // r
            rotation[i][1] = Integer.parseInt(st.nextToken()); // c
            rotation[i][2] = Integer.parseInt(st.nextToken()); // s
        }

        visited = new boolean[K]; // 순열 생성용 방문 배열
        order = new int[K];       // 순열 결과 저장

        // 모든 회전 순서 탐색 시작
        dfs(0);

        // 결과 출력
        System.out.println(answer);
    }

    // ----------------------------------------------------
    // 순열을 이용해 모든 회전 순서 조합 생성
    // ----------------------------------------------------
    static void dfs(int depth) {
        if (depth == K) { // K개 모두 선택했을 때
            // 원본 배열 복사 (매 순열마다 원래 상태에서 시작)
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++)
                temp[i] = board[i].clone();

            // 순열 순서대로 회전 연산 수행
            for (int idx : order) {
                rotate(temp, rotation[idx]);
            }

            // 회전이 끝난 배열의 "값" 계산 후 최소값 갱신
            answer = Math.min(answer, calc(temp));
            return;
        }

        // 아직 선택 안 된 회전 연산을 하나씩 선택
        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;  // 현재 위치에 i번째 연산 선택
                dfs(depth + 1);    // 다음 깊이로 이동
                visited[i] = false; // 백트래킹 (선택 취소)
            }
        }
    }

    // ----------------------------------------------------
    // 회전 연산 수행 함수
    // arr : 현재 배열
    // rot : [r, c, s]
    // ----------------------------------------------------
    static void rotate(int[][] arr, int[] rot) {
        int r = rot[0] - 1; // 입력은 1-based → 배열은 0-based
        int c = rot[1] - 1;
        int s = rot[2];

        // 바깥 layer부터 안쪽 layer까지 순서대로 회전
        for (int layer = 1; layer <= s; layer++) {
            int top = r - layer;
            int left = c - layer;
            int bottom = r + layer;
            int right = c + layer;

            // 좌상단 값 임시 저장 (덮어쓰기 방지)
            int temp = arr[top][left];

            // 왼쪽 세로 ↓ 이동
            for (int i = top; i < bottom; i++) {
                arr[i][left] = arr[i + 1][left];
            }

            // 아래쪽 가로 ← 이동
            for (int i = left; i < right; i++) {
                arr[bottom][i] = arr[bottom][i + 1];
            }

            // 오른쪽 세로 ↑ 이동
            for (int i = bottom; i > top; i--) {
                arr[i][right] = arr[i - 1][right];
            }

            // 위쪽 가로 → 이동
            for (int i = right; i > left + 1; i--) {
                arr[top][i] = arr[top][i - 1];
            }

            // 저장해 둔 값 삽입
            arr[top][left + 1] = temp;
        }
    }

    // ----------------------------------------------------
    // 배열의 "값" 계산: 각 행의 합 중 최솟값
    // ----------------------------------------------------
    static int calc(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += arr[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }
}
