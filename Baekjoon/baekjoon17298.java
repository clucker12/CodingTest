package Baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon17298 {
    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 처리하기 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 수열의 크기 N 입력 받기
        int n = Integer.parseInt(br.readLine());

        // 수열과 결과를 저장할 배열 생성
        int[] a = new int[n];
        int[] result = new int[n];

        // 수열 입력 받기 (공백으로 구분된 문자열을 split하여 배열로 만듦)
        String[] input = br.readLine().split(" ");

        // 문자열 배열을 정수 배열로 변환
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(input[i]);
        }

        // 결과 배열 초기값을 모두 -1로 설정
        // 오큰수가 없으면 기본값 -1을 출력하기 때문
        Arrays.fill(result, -1);

        // 인덱스를 저장할 스택 생성 (오른쪽에 더 큰 수를 찾기 위한 인덱스 관리용)
        Deque<Integer> stack = new ArrayDeque<>();

        // 수열을 왼쪽에서 오른쪽으로 순회
        for (int i = 0; i < n; i++) {
            // 스택이 비어있지 않고, 현재 수 a[i]가
            // 스택 맨 위에 있는 인덱스의 수 a[stack.peek()]보다 크면
            // 오큰수를 찾은 것이므로 결과 배열에 현재 수 a[i]를 저장
            while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
                // 오큰수를 찾은 인덱스 꺼내서 결과 배열에 저장
                result[stack.pop()] = a[i];
            }
            // 현재 인덱스를 스택에 추가 (아직 오큰수를 못 찾았으므로)
            stack.push(i);
        }

        // StringBuilder를 사용하여 결과를 한 줄로 만들기
        StringBuilder sb = new StringBuilder();
        for (int val : result) {
            sb.append(val).append(" ");  // 각 결과 뒤에 공백 추가
        }

        // 마지막 공백을 제거하고 결과 출력
        System.out.println(sb.toString().trim());
    }
}
