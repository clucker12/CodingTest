package Sort;
import java.util.*;

public class programmers3 {
    class Solution {
        public int solution(int[] citations) {
            int answer = 0;

            // 논문 인용 횟수를 오름차순 정렬
            Arrays.sort(citations);

            int n = citations.length;

            // i번째 논문부터 끝까지 순회
            for (int i = 0; i < n; i++) {
                int h = n - i;  // 현재 위치에서 h = h번 이상 인용된 논문 개수

                // 현재 논문의 인용 횟수가 h 이상이라면,
                // 즉 h번 이상 인용된 논문이 h편 이상이면
                if (citations[i] >= h) {
                    return h;   // 조건 만족하므로 해당 h가 H-Index가 됨
                }
            }

            // 모든 조건을 만족하는 h가 없다면 H-Index는 0
            return answer;
        }
    }
}
