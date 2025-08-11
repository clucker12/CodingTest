package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class baekjoon11478 {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자열 S 입력 받기
        String S = br.readLine();

        // HashSet을 사용하여 중복 없는 부분 문자열 저장
        HashSet<String> set = new HashSet<>();

        // 문자열의 길이
        int len = S.length();

        // 부분 문자열의 길이 i를 1부터 len까지 반복
        for (int i = 1; i <= len; i++) {
            // 문자열 S의 시작 인덱스 j를 0부터 시작
            // 부분 문자열의 끝 인덱스 (j + i)가 len 이하일 때까지만 반복
            for (int j = 0; j + i <= len; j++) {
                // 부분 문자열을 잘라내어 set에 추가 (중복은 자동으로 제거됨)
                set.add(S.substring(j, j + i));
            }
        }

        // HashSet에 들어있는 서로 다른 부분 문자열의 개수 출력
        int result = set.size();
        System.out.println(result);
    }
}
