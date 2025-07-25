package Hash;
import java.util.*;

public class programmers2 {
    class Solution {
        public int solution(int[] nums) {
            // N마리의 폰켓몬 중 N/2마리를 선택하여
            // 가장 다양한 종류를 선택할 수 있는 경우의 수를 구하는 문제
            int answer = 0;
            int n = nums.length / 2; // 선택 가능한 폰켓몬 수 (절반만 선택 가능)


            // HashSet: 중복을 허용하지 않는 자료구조
            // Set 인터페이스를 구현한 컬렉션으로, 요소의 중복 저장을 허용하지 않음
            // 내부적으로는 Hash를 이용해 빠른 검색/추가/삭제가 가능 (평균 시간 복잡도 O(1))
            Set<Integer> set = new HashSet<>();

            // 중복 제거하여 폰켓몬의 '종류' 수 계산
            for (int num : nums) {
                set.add(num); // Set은 중복을 허용하지 않으므로 자동으로 중복 제거
            }

            // 선택 가능한 수(n)보다 종류 수가 많으면 n이 최대 종류 수
            // 그렇지 않으면 set.size()가 최대 종류 수
            if(set.size()>n){
                answer=n;
            }else{
                answer=set.size();
            }

            return answer;

        }
    }
}
