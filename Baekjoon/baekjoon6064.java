package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon6064 {
    static int T, M, N, x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 빠르게 받기 위한 BufferedReader
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수 입력
        for (int i = 0; i < T; i++) {
            // 테스트 케이스별 입력 파싱
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // M년 주기 (x의 최대값)
            int N = Integer.parseInt(st.nextToken()); // N년 주기 (y의 최대값)
            int x = Integer.parseInt(st.nextToken()); // x년 (1-based)
            int y = Integer.parseInt(st.nextToken()); // y년 (1-based)

            // 결과 출력 (x, y는 내부 계산 편의를 위해 0-based로 변환)
            System.out.println(findYear(M, N, x - 1, y - 1));
        }
    }

    // 최대공약수(GCD) 구하는 함수 - 유클리드 호제법 사용
    static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // 최소공배수(LCM) 구하는 함수
    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // <x, y>가 몇 번째 해인지 찾는 함수
    static int findYear(int M, int N, int x, int y) {
        // 카잉 달력의 최대 연도는 M과 N의 최소공배수
        int maxYear = lcm(M, N);

        // x값을 기준으로 year = x + M * i 형태로 증가
        // 해당 year가 y 조건도 만족하는지 확인
        for (int i = 0; x + M * i < maxYear; i++) {
            int year = x + M * i; // 현재 검사할 연도

            // y값은 (year % N)이어야 함 (0-based로 계산 중)
            if (year % N == y) {
                return year + 1; // 실제 연도는 1-based이므로 +1
            }
        }

        // 조건을 만족하는 해가 없으면 -1 반환
        return -1;
    }
}
