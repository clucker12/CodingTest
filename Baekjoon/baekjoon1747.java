package Baekjoon;

import java.util.Scanner;

public class baekjoon1747 {
    static int N; // 입력받은 수를 저장할 전역 변수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 사용자로부터 정수 N 입력받기

        // N부터 시작해서, 팰린드롬이면서 소수인 수를 찾을 때까지 반복
        while (true) {
            // isPalindrome(N): N이 팰린드롬인지 확인
            // isPrime(N): N이 소수인지 확인
            if (isPalindrome(N) && isPrime(N)) {
                // 두 조건을 모두 만족하면 결과 출력 후 종료
                System.out.println(N);
                break;
            }
            N++; // 조건을 만족하지 않으면 N을 1 증가시켜 계속 탐색
        }
    }

    // 🔹 소수 판별 함수
    static boolean isPrime(int n) {
        // 2보다 작은 수(0, 1)는 소수가 아님
        if (n < 2) return false;

        // √n 까지만 나눠보면 충분 (i*i <= n)
        for (int i = 2; i * i <= n; i++) {
            // 나누어 떨어지면 소수가 아님
            if (n % i == 0) {
                return false;
            }
        }

        // 위 반복을 모두 통과하면 소수임
        return true;
    }

    // 🔹 팰린드롬 판별 함수
    static boolean isPalindrome(int n) {
        int original = n; // 원래 숫자를 저장해둠 (뒤집은 값과 비교용)
        int reversed = 0; // 뒤집은 숫자를 저장할 변수

        // n이 0이 될 때까지 자릿수를 하나씩 뒤집어 더함
        while (n != 0) {
            int digit = n % 10;            // 마지막 자리 숫자 추출
            reversed = reversed * 10 + digit; // 뒤집은 숫자에 추가
            n /= 10;                       // 마지막 자리 제거
        }

        // 원래 숫자와 뒤집은 숫자가 같으면 팰린드롬
        return original == reversed;
    }
}
