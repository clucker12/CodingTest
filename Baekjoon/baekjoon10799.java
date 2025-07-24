package Baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class baekjoon10799 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 괄호 문자열 입력 받음 (공백 없는 한 줄 입력)
        String input = sc.next();

        // 여는 괄호 '('를 저장할 스택
        Stack<Character> stack = new Stack<>();

        // 잘려진 쇠막대기 조각 수를 저장할 변수
        int total = 0;

        // 문자열의 각 문자를 하나씩 확인
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);

            if(ch == '(') {
                // 여는 괄호: 새로운 쇠막대기의 시작일 수 있으므로 스택에 push
                stack.push(ch);
            } else {
                // 닫는 괄호 ')'를 만난 경우

                stack.pop();  // 일단 무조건 스택에서 하나 pop (짝 맞추기용)

                if(input.charAt(i - 1) == '('){
                    // 바로 앞 문자가 '('이면 => 레이저 '()' 패턴
                    // 레이저가 쏘이면 현재 열려 있는 쇠막대기 수만큼 조각 생김
                    total += stack.size();
                } else {
                    // 바로 앞 문자가 ')'이면 => 쇠막대기의 끝
                    // 끝 조각 하나 추가
                    total += 1;
                }
            }
        }

        // 결과 출력: 잘린 쇠막대기 조각의 총 수
        System.out.println(total);
    }
}
/*
🧠 간단 요약 (코드 로직 흐름)
'(' → 스택에 push
')' →
앞이 '('이면 → 레이저 → 현재 스택 크기만큼 조각 추가
앞이 ')'이면 → 쇠막대기 끝 → 조각 1개 추가
 */
/*
📌 주의할 점
input.charAt(i - 1)을 사용할 때는 i > 0이 보장되어야 함 → 이 코드는 처음에 ch == '('인 경우만 push하므로 괜찮음
레이저는 항상 '()' 형태로만 주어짐 → 입력이 올바른 괄호 문자열이라고 가정됨
 */