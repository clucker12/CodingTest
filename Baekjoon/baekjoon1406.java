package Baekjoon;

import java.io.*;
import java.util.Stack;

public class baekjoon1406 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 커서 왼쪽, 오른쪽을 표현할 두 개의 스택
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        // 초기 문자열을 입력받아 한 글자씩 left 스택에 저장 (커서가 맨 뒤에 있다고 가정)
        String a = br.readLine();
        for (char b : a.toCharArray()) {
            left.push(b);  // 커서 왼쪽에 있는 문자들
        }

        // 명령어 개수 입력
        int n = Integer.parseInt(br.readLine());

        // n개의 명령어를 순차적으로 처리
        for (int i = 0; i < n; i++) {
            String m = br.readLine();

            // 명령어 종류에 따라 분기 처리
            if (m.equals("L")) {
                // 커서를 왼쪽으로 옮김: 왼쪽에서 pop → 오른쪽으로 push
                if (!left.isEmpty()) right.push(left.pop());
            } else if (m.equals("D")) {
                // 커서를 오른쪽으로 옮김: 오른쪽에서 pop → 왼쪽으로 push
                if (!right.isEmpty()) left.push(right.pop());
            } else if (m.equals("B")) {
                // 커서 왼쪽 문자 삭제: 왼쪽에서 pop
                if (!left.isEmpty()) left.pop();
            } else {
                // P x 명령: 왼쪽에 문자 x 추가
                left.push(m.charAt(2));  // 명령 문자열이 "P x" 형태이므로 index 2가 문자 x
            }
        }

        // 최종 문자열 출력을 위해 left → right로 모두 옮겨서 순서를 정방향으로 맞춤
        while (!left.isEmpty()) {
            right.push(left.pop());
        }

        // 출력 문자열을 StringBuilder에 저장 (효율적인 문자열 생성)
        StringBuilder sb = new StringBuilder();
        while (!right.isEmpty()) {
            sb.append(right.pop());  // 정방향 출력
        }

        // 결과 출력
        System.out.println(sb.toString());
    }
}
