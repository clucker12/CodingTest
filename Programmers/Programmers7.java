package Programmers;

public class Programmers7 {
    public int solution(String word) {
        int answer = 0;

        // 각 자리별 가중치 배열
        // 1번째 자리: 5^4 + 5^3 + 5^2 + 5^1 + 5^0 = 781
        // 2번째 자리: 5^3 + 5^2 + 5^1 + 5^0 = 156
        // 3번째 자리: 5^2 + 5^1 + 5^0 = 31
        // 4번째 자리: 5^1 + 5^0 = 6
        // 5번째 자리: 5^0 = 1
        int[] weight = {781, 156, 31, 6, 1};

        // 모음 배열 (사전 순)
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};

        // 단어의 각 글자에 대해 처리
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // 현재 글자가 모음 배열에서 몇 번째 인덱스인지 찾기
            int index = 0;
            for (int j = 0; j < vowels.length; j++) {
                if (vowels[j] == c) {
                    index = j;
                    break;
                }
            }

            // 해당 자리 글자 인덱스에 가중치를 곱해
            // 그 앞에 올 수 있는 단어 개수를 계산
            // +1은 자기 자신을 포함하기 위함
            answer += index * weight[i] + 1;
        }

        // 계산된 위치 반환
        return answer;
    }
}
