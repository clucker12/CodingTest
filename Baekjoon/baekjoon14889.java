package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14889 {
    // 총 인원 수 (짝수)
    static int N;

    // 능력치 배열
    static int[][] S;

    // 스타트 팀 여부를 표시하는 배열
    static boolean[] visited;

    // 능력치 차이의 최소값 저장
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 인원 수 입력
        N = Integer.parseInt(br.readLine());

        // 능력치 배열 및 visited 배열 초기화
        S = new int[N][N];
        visited = new boolean[N];

        // 능력치 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 팀 나누기 시작
        maketeam(0, 0);

        // 최소 능력치 차이 출력
        System.out.println(min);
    }

    // 팀을 나누는 함수 (재귀적으로 N/2명 선택)
    static void maketeam(int idx, int depth) {
        // N/2명을 선택했다면 계산
        if (depth == N / 2) {
            calculate();
            return;
        }

        // 조합을 위해 idx부터 N까지 반복
        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;  // i번 사람을 스타트 팀으로 선택
                maketeam(i + 1, depth + 1);  // 다음 사람 선택
                visited[i] = false; // 백트래킹
            }
        }
    }

    // 두 팀의 능력치 차이 계산
    static void calculate() {
        int start = 0; // 스타트 팀 능력치 합
        int link = 0;  // 링크 팀 능력치 합

        // 모든 쌍(i, j)을 돌면서 같은 팀끼리만 능력치 더하기
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // 둘 다 visited → 스타트 팀
                if (visited[i] && visited[j]) {
                    start += S[i][j] + S[j][i];
                }
                // 둘 다 미방문 → 링크 팀
                else if (!visited[i] && !visited[j]) {
                    link += S[i][j] + S[j][i];
                }
            }
        }

        // 두 팀의 능력치 차이
        int sol = Math.abs(start - link);

        // 최소값 갱신
        min = Math.min(min, sol);
    }
}
