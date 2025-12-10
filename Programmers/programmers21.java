package Programmers;

import java.util.Stack;

public class programmers21 {
    public int solution(String s)
    {
        // 문자 비교를 위한 스택 생성
        Stack<Character> stack = new Stack<>();

        // 문자열을 처음부터 끝까지 반복
        for (char c : s.toCharArray()) {

            // 스택이 비어있지 않고,
            // 스택의 top(마지막 문자)이 현재 문자와 같으면 → 짝이므로 제거
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();      // 짝을 제거
            } else {
                stack.push(c);    // 짝이 아니면 스택에 넣음
            }
        }

        // 스택이 비어있으면 모든 문자 짝지어 제거 완료 → 1
        // 스택에 문자가 남아 있으면 제거 실패 → 0
        return stack.isEmpty() ? 1 : 0;
    }
}
