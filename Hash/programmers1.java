package Hash;
import java.util.*;

public class programmers1 {
    class Solution {
        // keySet() 개념
        //이 메서드는 Map<K, V> 객체에서 모든 key만을 모아서 Set으로 반환합니다.
        //반환 타입은 Set<K> 이며, 이는 중복을 허용하지 않는 컬렉션입니다.

        public String solution(String[] participant, String[] completion) {
            String answer = "";
            HashMap<String, Integer> map = new HashMap<>();

            // 참가자 명단을 해시맵에 저장 (이름 -> 등장 횟수)
            for (String name : participant) {
                map.put(name, map.getOrDefault(name, 0) + 1);
            }

            // 완주자 명단을 보고 맵에서 수를 줄임
            for (String name : completion) {
                map.put(name, map.get(name) - 1);
            }

            // 값이 1인 사람 (완주 못한 사람)
//            for (String key : map.keySet()) {
//                if (map.get(key) > 0) {
//                    answer= key;
//                }
//            }

            // 값이 1인 사람 (완주 못한 사람) 권장 방법
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 0) {
                    return entry.getKey();
                }
            }
            return answer;
        }
    }
}
