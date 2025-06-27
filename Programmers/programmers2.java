package Programmers;

import java.util.*;

public class programmers2 {

    class Solution {
        public int[] solution(int[] answers) {
            int cnt = answers.length; // 문제의 개수

            // 각 수포자의 찍기 패턴에 따라 정답을 담을 배열 생성
            int[] a = new int[cnt]; // 수포자 1
            int[] b = new int[cnt]; // 수포자 2
            int[] c = new int[cnt]; // 수포자 3

            // 수포자 1의 패턴: 1, 2, 3, 4, 5 반복
            for (int i = 0; i < cnt; i++) {
                a[i] = (i % 5) + 1;
            }

            // 수포자 2의 패턴: 2, 1, 2, 3, 2, 4, 2, 5 반복
            int[] bPattern = {2, 1, 2, 3, 2, 4, 2, 5};
            for (int i = 0; i < cnt; i++) {
                b[i] = bPattern[i % bPattern.length]; // 패턴 길이 넘어가면 처음부터 반복
            }

            // 수포자 3의 패턴: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 반복
            int[] cPattern = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
            for (int i = 0; i < cnt; i++) {
                c[i] = cPattern[i % cPattern.length]; // 마찬가지로 패턴 반복
            }

            // 각각 맞힌 문제 수 카운트 변수
            int cnt_a = 0, cnt_b = 0, cnt_c = 0;

            // 정답과 수포자들의 답을 비교하여 맞힌 문제 수 증가
            for (int i = 0; i < cnt; i++) {
                if (answers[i] == a[i]) cnt_a++;
                if (answers[i] == b[i]) cnt_b++;
                if (answers[i] == c[i]) cnt_c++;
            }

            // 세 사람 중 가장 많이 맞힌 개수 구함
            int max = Math.max(cnt_a, Math.max(cnt_b, cnt_c));

            // 가장 많이 맞힌 사람(들)을 저장할 리스트
            ArrayList<Integer> list = new ArrayList<>();

            // 최고 점수와 같은 사람을 리스트에 추가
            if (cnt_a == max) list.add(1); // 수포자 1
            if (cnt_b == max) list.add(2); // 수포자 2
            if (cnt_c == max) list.add(3); // 수포자 3

            // 결과를 배열로 변환
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }

            // 정답 리턴
            return answer;
        }
    }

}
