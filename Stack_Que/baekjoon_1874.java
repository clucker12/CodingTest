package Stack_Que;

import java.io.*;
import java.util.*;

public class baekjoon_1874 {
    // 전역 스택 선언 (정수를 저장)
    static Stack<Integer> st = new Stack<>();

    // 다음으로 push할 숫자 (1부터 시작)
    static int tmp = 1;

    public static void main(String[] args) throws IOException {

        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 수열의 길이 (1 <= C <= 100,000)
        int C = Integer.parseInt(br.readLine());

        // 결과(연산 순서)를 저장할 StringBuilder (성능 좋음)
        StringBuilder sb = new StringBuilder();

        // 입력된 수열을 한 줄씩 읽어서 처리
        for (int i = 0; i < C; i++) {
            int N = Integer.parseInt(br.readLine()); // 만들어야 하는 수

            // 현재 tmp부터 N까지 push (오름차순으로만 push 가능)
            for (; tmp <= N; tmp++) {
                st.push(tmp);        // tmp 값을 스택에 push
                sb.append("+\n");   // push 연산 기록
            }

            // 현재 스택의 top이 만들어야 하는 수 N이면 pop
            if (st.peek() == N) {
                st.pop();           // pop 수행
                sb.append("-\n");   // pop 연산 기록
            } else {
                // top이 N이 아니면, 더 이상 올바른 수열을 만들 수 없음
                System.out.println("NO");
                return; // 프로그램 종료
            }
        }

        // 모든 수열을 성공적으로 만들었으면 연산 출력
        System.out.println(sb);
    }
}
