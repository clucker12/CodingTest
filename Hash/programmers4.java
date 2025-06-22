package Hash;
import java.util.*;

public class programmers4 {
    class Solution {
        public int solution(String[][] clothes) {
            int answer = 1; // 최종 경우의 수를 저장할 변수 (초기값 1: 곱셈용)

            HashMap<String, Integer> map = new HashMap<>();

            // 옷의 종류별 개수를 세기
            for (int i = 0; i < clothes.length; i++) {
                // clothes[i][1]는 옷의 종류 (예: "headgear")
                // 해당 종류에 해당하는 의상 개수를 +1 증가
                map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
            }

            // 각 옷 종류마다 (입는 경우의 수 + 안 입는 경우의 수 = 개수 + 1)
            for (int count : map.values()) {
                answer *= (count + 1); // 각 종류마다 선택할 수 있는 경우의 수를 곱함
            }

            // 모든 옷을 입지 않는 경우는 제외 (최소 한 개는 입어야 하므로)
            return answer - 1;
        }
    }
}
