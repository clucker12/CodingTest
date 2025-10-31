package Baekjoon;

import java.util.Scanner;

public class baekjoon16637 {
    // 수식의 길이 (항상 홀수)
    static int N;

    // 수식을 문자 배열로 저장 (숫자와 연산자가 번갈아 존재)
    static char[] sik;

    // 결과의 최댓값 (초기값은 최소 정수)
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력: 수식의 길이 N
        N = Integer.parseInt(sc.next());
        // 입력: 수식 문자열 (예: "3+8*7-9*2")
        String input = sc.next();

        // 수식을 문자 배열에 저장
        sik = new char[N];
        for (int i = 0; i < N; i++) {
            sik[i] = input.charAt(i);
        }

        // 첫 번째 숫자를 현재 값으로 두고, 두 번째 문자(첫 연산자)부터 탐색 시작
        // 즉, "왼쪽부터 차례로 계산"을 DFS로 시도함
        dfs(1, sik[0] - '0');

        // 최댓값 출력
        System.out.println(max);
    }

    /**
     * DFS 탐색 함수
     * @param idx 현재 처리 중인 연산자의 위치
     * @param current 지금까지 계산된 결과값
     */
    static void dfs(int idx, int current) {
        // [기저 조건] 수식 끝까지 탐색했으면 최댓값 갱신
        if (idx >= N) {
            max = Math.max(max, current);
            return;
        }

        // 현재 연산자
        char op = sik[idx];
        // 다음 숫자
        int nextNum = sik[idx + 1] - '0';

        // ----------------------------------------------------
        // ① 괄호 없이 계산하는 경우
        // ex) current=3, op='+', nextNum=8  → 3+8 = 11
        // 다음 연산자는 idx+2 위치에 있으므로 idx+2로 이동
        // ----------------------------------------------------
        dfs(idx + 2, calc(current, op, nextNum));

        // ----------------------------------------------------
        // ② 괄호를 사용하는 경우 (다음 연산자 포함)
        // ex) 현재 위치가 '+'라면, 다음 연산자('*')와 숫자까지 묶어 "(8*7)" 형태로 먼저 계산
        // 괄호 안에 연산자가 하나만 들어가야 하므로
        // 다음 연산자와 그 뒤 숫자가 모두 있어야 함 → idx+2 < N-1 조건
        // ----------------------------------------------------
        if (idx + 2 < N - 1) {
            // 괄호 안의 결과 계산: (nextNum op2 nextNextNum)
            int bracketVal = calc(nextNum, sik[idx + 2], sik[idx + 3] - '0');
            // 현재 current와 op로, 위 괄호 결과를 이용해 계산
            // ex) current=3, op='+', bracketVal=(8*7)=56 → 3+(8*7)=59
            dfs(idx + 4, calc(current, op, bracketVal));
        }
    }

    /**
     * 실제 계산 수행 함수
     * @param a 첫 번째 피연산자
     * @param op 연산자 (+, -, *)
     * @param b 두 번째 피연산자
     * @return 계산 결과
     */
    static int calc(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0; // 이 경우는 사실상 발생하지 않음
    }
}
