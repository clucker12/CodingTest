package Baekjoon; // 패키지 이름 (백준 문제 풀이용, 선택 사항)

import java.util.Scanner;

public class baekjoon4375 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in); // 표준 입력을 받기 위한 Scanner 생성

        // 입력이 여러 줄로 들어오기 때문에
        // hasNextInt()로 입력이 더 이상 없을 때까지 반복
        while (sc.hasNextInt()){
            int n = sc.nextInt(); // n 입력받기 (2나 5로 나누어지지 않는 수)

            int cnt = 1;     // 자릿수 (맨 처음 1로 시작하므로 1자리)
            int num = 1 % n; // 현재 '1'로만 이루어진 수를 n으로 나눈 나머지 저장 (처음엔 1)

            // num이 0이 될 때까지 반복
            // num == 0 이라는 건, 지금까지 만든 '1...1'이 n의 배수라는 뜻
            while(num != 0){
                // 다음 자리에 '1'을 추가한 수의 나머지를 구함
                // 예: num=1 → (1*10+1)=11 → 11%n 의 나머지 계산
                num = (num * 10 + 1) % n;

                // 한 자리 추가됐으므로 자릿수 증가
                cnt++;
            }

            // num % n == 0 이 되는 순간의 cnt 출력
            // 즉, 모든 자릿수가 1로만 이루어진 n의 배수 중 가장 작은 수의 자릿수
            System.out.println(cnt);
        }

        sc.close(); // Scanner 자원 해제
    }
}
