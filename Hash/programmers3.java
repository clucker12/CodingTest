package Hash;
import java.util.*;

public class programmers3 {
    class Solution {
        public boolean solution(String[] phone_book) {
            // 전화번호부를 사전순으로 정렬
            Arrays.sort(phone_book);

            // 인접한 두 번호를 하나씩 비교
            for(int i = 0; i < phone_book.length - 1; i++){
                /*
                 * startsWith 메서드:
                 * 문자열 s1.startsWith(s2)는
                 * s1이 s2로 시작하면 true를 반환합니다.
                 *
                 * 예를 들어,
                 * "1195524421".startsWith("119") 는 true,
                 * "97674223".startsWith("119") 는 false 입니다.
                 *
                 * 여기서는 phone_book[i + 1]이 phone_book[i]로 시작하는지 체크하여,
                 * 접두어 관계인지 판단합니다.
                 */
                if(phone_book[i + 1].startsWith(phone_book[i])){
                    // 접두어 관계가 발견되면 false 반환
                    return false;
                }
            }
            // 모든 번호를 비교해 접두어 관계가 없으면 true 반환
            return true;
        }
    }
}
