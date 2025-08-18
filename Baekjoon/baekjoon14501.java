package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon14501 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 상담 가능한 날짜 수 (퇴사는 N+1일)
        int N = Integer.parseInt(br.readLine());

        // 각 날짜별 상담에 걸리는 기간 (1-indexed)
        int[] T = new int[N + 1];

        // 각 날짜별 상담으로 얻을 수 있는 수익 (1-indexed)
        int[] P = new int[N + 1];

        // DP 배열: dp[i] = i일까지 얻을 수 있는 최대 수익
        // 배열 크기를 넉넉히 N+7로 설정 (N+2면 충분하지만 여유 있게)
        int[] dp = new int[N + 7];

        // T[i], P[i] 입력 받기
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            T[i] = Integer.parseInt(input[0]);  // 상담 기간
            P[i] = Integer.parseInt(input[1]);  // 상담 수익
        }

        // DP 계산: 1일부터 N일까지 순회
        for (int i = 1; i <= N; i++) {
            // 1. 상담하지 않는 경우:
            //    다음 날로 수익을 그대로 넘김 (현재까지 수익 유지)
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // 2. 상담하는 경우:
            //    상담을 끝낼 수 있는 날짜가 퇴사 전이면
            if (i + T[i] <= N + 1) {
                // 상담이 끝나는 날에 수익을 갱신
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }

        // dp 배열 중 최대 수익을 찾음
        int max = 0;
        for (int i = 1; i <= N + 1; i++) {
            max = Math.max(max, dp[i]);
        }

        // 최대 수익 출력
        System.out.println(max);
    }
}
