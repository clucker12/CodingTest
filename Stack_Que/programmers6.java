package Stack_Que;

public class programmers6 {
    class Solution {
        public int[] solution(int[] prices) {
            int[] answer = {};
            answer = new int[prices.length]; // 결과를 담을 배열을 prices와 같은 크기로 초기화

            // prices[i]는 i초 시점의 주식 가격
            for (int i = 0; i < prices.length; i++) {
                int count = 0; // 가격이 떨어지지 않은 시간(초)

                // i 시점 이후의 모든 시점을 확인
                for (int j = i + 1; j < prices.length; j++) {
                    count++; // 1초 경과

                    // 가격이 떨어지면 종료
                    if (prices[i] > prices[j]) {
                        break;
                    }
                }

                // 계산한 시간 저장
                answer[i] = count;
            }

            return answer; // 결과 배열 반환
        }
    }
}
