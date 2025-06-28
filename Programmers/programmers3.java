package Programmers;  // 패키지 선언 (필요에 따라 다름)

import java.util.*;  // Java 컬렉션 프레임워크 사용을 위한 임포트 (HashSet 등)

public class programmers3 {  // 외부 클래스 선언

    public class Solution {  // 문제 풀이를 위한 내부 클래스 선언 (프로그래머스 규칙에 맞게)

        // 문제 해결 메인 함수
        public int solution(String numbers) {
            Set<Integer> uniqueNumbers = new HashSet<>();
            // 중복 제거를 위해 Set 사용
            // numbers 문자열로 만들 수 있는 모든 숫자 조합 저장

            // 모든 가능한 숫자 조합 생성 (순열)
            permutation("", numbers, uniqueNumbers);

            int count = 0;  // 소수 개수를 셀 변수 초기화

            // 생성된 숫자들 중 소수인 것만 골라 개수 세기
            for (int num : uniqueNumbers) {
                if (isPrime(num)) {  // 소수 판별 함수 호출
                    count++;  // 소수면 개수 증가
                }
            }

            return count;  // 최종 소수 개수 반환
        }

        // 재귀를 이용한 순열 생성 함수
        // prefix: 지금까지 선택한 숫자 조합 (문자열)
        // remaining: 아직 선택하지 않은 숫자들 (문자열)
        // results: 만들어진 숫자들을 저장하는 집합
        private void permutation(String prefix, String remaining, Set<Integer> results) {

            // prefix가 빈 문자열이 아니면 (즉, 숫자 조합이 만들어졌으면)
            if (!prefix.isEmpty()) {
                // prefix를 정수로 변환해 results에 추가
                results.add(Integer.parseInt(prefix));
            }

            // remaining 문자열 길이만큼 반복하면서 각 문자를 순서대로 선택
            for (int i = 0; i < remaining.length(); i++) {
                // 현재 선택한 문자(remaining.charAt(i))를 prefix에 붙이고,
                // 선택한 문자를 제외한 나머지를 다음 재귀 호출에 넘김
                permutation(
                        prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1),
                        results
                );
            }
        }

        // 소수 판별 함수
        private boolean isPrime(int n) {
            if (n < 2) return false;  // 2보다 작으면 소수가 아님

            // 2부터 n-1까지 모두 나눠보면서 나누어떨어지는지 확인
            for (int i = 2; i < n; i++) {
                if (n % i == 0) return false;  // 나누어떨어지면 소수 아님
            }

            return true;  // 나누어떨어지는 수가 없으면 소수임
        }
    }
}
