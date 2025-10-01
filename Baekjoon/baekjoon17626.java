package Baekjoon; // 패키지명 (자바 프로젝트 내에서 파일을 분류하기 위한 이름)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon17626 {
    static int N; // 입력값 N을 저장할 변수

    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사용자로부터 자연수 N 입력 받기
        N = Integer.parseInt(br.readLine());

        // dp[i]는 숫자 i를 제곱수의 합으로 표현할 수 있는 최소 개수를 의미
        int[] dp = new int[N + 1];

        // 1부터 N까지 각 수에 대해 최소 제곱수 개수 계산
        for (int i = 1; i <= N; i++) {
            // 최악의 경우: 모두 1²로 구성하는 경우 (ex: i=5 → 1+1+1+1+1)
            dp[i] = i;

            // 1², 2², 3², ..., j²가 i 이하일 때 모두 시도
            for (int j = 1; j * j <= i; j++) {
                // i - j² 를 만들 수 있는 최소 제곱수 개수 + 1(= j² 사용)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        // 결과 출력: N을 제곱수 합으로 표현할 수 있는 최소 개수
        System.out.println(dp[N]);
    }
}
