package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon2503 {

    // 민혁이의 질문과 영수의 답을 저장하는 클래스
    static class Question {
        String num;   // 민혁이 질문한 숫자 (세 자리 수 문자열 형태)
        int strike;   // 영수의 스트라이크 답변
        int ball;     // 영수의 볼 답변

        Question(String num, int strike, int ball) {
            this.num = num;
            this.strike = strike;
            this.ball = ball;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 민혁이 질문한 횟수
        List<Question> questions = new ArrayList<>();

        // N개의 질문을 입력 받아 리스트에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String num = st.nextToken(); // 민혁이 질문한 세 자리 수
            int strike = Integer.parseInt(st.nextToken()); // 스트라이크 수
            int ball = Integer.parseInt(st.nextToken());   // 볼 수
            questions.add(new Question(num, strike, ball)); // 리스트에 추가
        }

        int answer = 0; // 가능한 영수의 수를 셀 변수

        // 가능한 모든 3자리 수 (123 ~ 987) 완전탐색
        for (int i = 123; i <= 987; i++) {
            String candidate = String.valueOf(i); // 숫자를 문자열로 변환

            if (!isValid(candidate)) continue; // 0이 포함되거나 중복된 숫자는 제외

            boolean isPossible = true; // 이 후보가 모든 질문과 일치하는지 여부

            // 모든 질문에 대해 스트라이크, 볼 수를 계산하고 비교
            for (Question q : questions) {
                int[] result = getStrikeBall(candidate, q.num);

                // 만약 하나라도 스트라이크/볼 수가 다르면 후보 탈락
                if (result[0] != q.strike || result[1] != q.ball) {
                    isPossible = false;
                    break;
                }
            }

            // 모든 질문을 만족하는 경우, 정답 개수 증가
            if (isPossible) {
                answer++;
            }
        }

        // 최종 가능한 숫자 개수 출력
        System.out.println(answer);
    }

    // 후보 숫자가 유효한지 검사하는 함수
    static boolean isValid(String num) {
        // 0이 포함되어 있거나, 중복된 숫자가 있는 경우 false
        if (num.contains("0")) return false;
        if (num.charAt(0) == num.charAt(1)) return false;
        if (num.charAt(1) == num.charAt(2)) return false;
        if (num.charAt(0) == num.charAt(2)) return false;
        return true; // 조건을 모두 통과하면 유효한 숫자
    }

    // 스트라이크와 볼 수를 계산하는 함수
    static int[] getStrikeBall(String candidate, String guess) {
        int strike = 0, ball = 0;

        // 각 자리를 비교
        for (int i = 0; i < 3; i++) {
            if (candidate.charAt(i) == guess.charAt(i)) {
                // 같은 숫자가 같은 자리에 있으면 스트라이크
                strike++;
            } else if (candidate.contains(String.valueOf(guess.charAt(i)))) {
                // 숫자는 포함되어 있으나 자리가 다르면 볼
                // String.contains() 메서드는 String 또는 CharSequence 타입만
                // 인자로 받을 수 있기 때문에 char 타입을 넣으려면 변환이 반드시 필요합니다.
                ball++;
            }
        }

        // [스트라이크 수, 볼 수] 반환
        return new int[]{strike, ball};
    }
}
