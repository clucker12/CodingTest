package Sort;
import java.util.*;

public class programmers2 {
    class Solution {
        public String solution(int[] numbers) {
            String answer = ""; // 결과로 반환할 문자열 초기화

            List<String> list = new ArrayList<>(); // 정수를 문자열로 변환하여 담을 리스트 생성

            // 배열의 모든 숫자를 문자열로 변환하여 리스트에 추가
            for(int i : numbers){
                list.add(Integer.toString(i));
            }

            // 문자열 정렬: 두 문자열 a, b를 이어붙인 ab와 ba를 비교해서 내림차순 정렬
            // 예: "3", "30" -> "330" vs "303" -> "330"이 크므로 "3"이 먼저 옴
            list.sort((a, b) -> (b + a).compareTo(a + b));

            // 가장 앞에 있는 값이 "0"이면, 모든 숫자가 0이라는 뜻이므로 "0"을 리턴
            if (list.get(0).equals("0")) {
                return "0";
            }

            // 정렬된 문자열을 모두 이어붙임
            for(String s : list){
                answer += s;
            }

            // 최종 결과 반환
            return answer;
        }
    }

}
