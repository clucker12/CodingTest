package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon2529 {
    // 부등호의 개수 (숫자의 개수는 N+1이 됨)
    static int N;

    // 부등호 기호들을 저장할 배열
    static char[] sign;

    // 숫자 0~9 중에서 사용 여부를 체크하기 위한 배열
    static boolean[] visited = new boolean[10];

    // 조건을 만족하는 모든 숫자 조합을 저장할 리스트
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 부등호 개수 입력
        N = Integer.parseInt(br.readLine());

        // 부등호 입력 받아 배열에 저장
        sign = new char[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sign[i] = st.nextToken().charAt(0);
        }

        // 깊이 우선 탐색 시작 (depth=0, 현재 숫자 문자열="")
        dfs(0, "");

        // 결과 리스트를 사전순 정렬
        Collections.sort(result);

        // 가장 큰 수 (마지막 원소)
        System.out.println(result.get(result.size() - 1));

        // 가장 작은 수 (첫 번째 원소)
        System.out.println(result.get(0));
    }

    /**
     * 깊이 우선 탐색 함수
     * @param depth 현재 깊이 (선택한 숫자의 개수)
     * @param num 현재까지 만든 숫자 문자열
     */
    static void dfs(int depth, String num) {
        // 숫자를 N+1개 선택했다면 결과 리스트에 추가
        if (depth == N + 1) {
            result.add(num);
            return;
        }

        // 0부터 9까지 숫자를 하나씩 시도
        for (int i = 0; i <= 9; i++) {
            // 아직 사용하지 않은 숫자라면
            if (!visited[i]) {
                // 첫 번째 숫자이거나, 이전 숫자와 부등호 조건을 만족할 경우만 진행
                if (depth == 0 || check(num.charAt(depth - 1) - '0', i, sign[depth - 1])) {
                    visited[i] = true;           // 숫자 사용 처리
                    dfs(depth + 1, num + i);     // 다음 자리로 재귀 호출
                    visited[i] = false;          // 백트래킹 (원상복구)
                }
            }
        }
    }

    /**
     * 부등호 조건을 검사하는 함수
     * @param a 이전 숫자
     * @param b 현재 숫자
     * @param op 부등호 문자 ('<' 또는 '>')
     * @return 조건을 만족하면 true, 아니면 false
     */
    static boolean check(int a, int b, char op) {
        if (op == '<') {
            return a < b;
        }
        if (op == '>') {
            return a > b;
        }
        return false;
    }
}
