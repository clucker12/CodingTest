package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class baekjoon2493 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 탑의 개수 N을 읽어옴
        int N = Integer.parseInt(br.readLine());

        // 결과를 저장할 배열 (각 탑에서 발사된 레이저가 수신되는 탑의 번호를 저장)
        int[] result = new int[N];

        // 각 탑의 높이를 저장할 배열
        int[] height = new int[N];

        // 탑의 인덱스를 저장할 스택 초기화
        Stack<Integer> stack = new Stack<>();

        // 탑의 높이를 입력받아 height 배열에 저장
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(input[i]);  // 탑의 높이 저장
        }

        // 각 탑에 대해 레이저가 수신되는 탑을 찾는 과정
        for (int i = 0; i < N; i++) {
            // 스택이 비어 있지 않고, 현재 탑의 높이가 스택의 top에 있는 탑의 높이보다 클 때
            // 스택에서 top 값을 계속 pop하여 작은 탑들을 제거
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                stack.pop();  // 현재 탑보다 작은 탑을 제거
            }

            // 만약 스택에 탑이 남아 있으면, 그 탑이 현재 탑에서 발사된 레이저를 수신하는 탑
            if (!stack.isEmpty()) {
                // 수신 탑의 인덱스를 1-based로 기록
                result[i] = stack.peek() + 1;
            }

            // 현재 탑의 인덱스를 스택에 추가 (이 탑은 다른 탑의 레이저를 수신할 수 있을 수 있음)
            stack.push(i);
        }

        // 결과 배열을 StringBuilder에 추가하여 출력
        StringBuilder sb = new StringBuilder();
        for (int res : result) {
            sb.append(res).append(" ");  // 각 결과를 공백으로 구분하여 붙여줌
        }

        // 최종 결과 출력 (마지막 공백 제거)
        System.out.println(sb.toString().trim());
    }
}

