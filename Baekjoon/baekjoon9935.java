package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon9935 {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄: 전체 입력 문자열
        String input = br.readLine();

        // 두 번째 줄: 폭발 문자열
        String boom = br.readLine();

        // 폭발 문자열의 길이 저장
        int boomLen = boom.length();

        // 결과를 저장할 StringBuilder (일종의 스택처럼 사용)
        StringBuilder sb = new StringBuilder();

        // 입력 문자열을 처음부터 끝까지 순회
        for (int i = 0; i < input.length(); i++) {
            // 현재 문자를 결과 문자열에 추가
            sb.append(input.charAt(i));

            // 현재 결과 문자열의 길이가 폭발 문자열 길이 이상이면 폭발 가능성 있음
            if (sb.length() >= boomLen) {
                boolean explode = true; // 폭발 문자열인지 판단하는 플래그

                // 폭발 문자열과 결과 문자열의 끝 부분을 비교
                for (int j = 0; j < boomLen; j++) {
                    // 현재 결과 문자열의 끝에서 boom 문자열과 다르면 폭발 아님
                    if (sb.charAt(sb.length() - boomLen + j) != boom.charAt(j)) {
                        explode = false; // 폭발 문자열 아님
                        break; // 비교 중단
                    }
                }

                // 폭발 문자열이 일치하면 삭제
                if (explode) {
                    // 폭발 문자열 길이만큼 삭제 (문자열 폭발)
                    sb.delete(sb.length() - boomLen, sb.length());
                }
            }
        }

        // 모든 폭발이 끝난 후 문자열이 비었으면 "FRULA" 출력
        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            // 아니면 남은 문자열 출력
            System.out.println(sb.toString());
        }
    }
}
