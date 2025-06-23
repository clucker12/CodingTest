package Sort;
import java.util.*;

public class programmers1 {
    class Solution {
        public int[] solution(int[] array, int[][] commands) {
            // 결과를 담을 배열을 commands 길이만큼 생성
            int[] answer = new int[commands.length];

            // commands 배열을 하나씩 처리
            for(int i = 0; i < commands.length; i++) {
                List<Integer> list = new ArrayList<>();

                // commands[i][0] ~ commands[i][1]까지 (1-based 인덱스)
                // array는 0-based 인덱스이므로 -1 보정하여 포함 범위 반복
                for(int j = commands[i][0] - 1; j <= commands[i][1] - 1; j++) {
                    list.add(array[j]);  // 해당 구간 원소를 리스트에 추가
                }

                // 리스트를 오름차순 정렬 (문제에서 정렬 후 특정 위치 값 구함)
                Collections.sort(list);

                // 정렬된 리스트에서 commands[i][2]번째 (1-based) 숫자를 결과 배열에 저장
                // commands[i][2]는 1-based 인덱스라 -1 보정하여 가져옴
                answer[i] = list.get(commands[i][2] - 1);
            }

            // 모든 명령어 처리 후 결과 배열 반환
            return answer;
        }
    }
}
