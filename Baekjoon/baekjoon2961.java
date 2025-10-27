package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2961 {
    // 재료의 개수 N
    static int N;

    // 각 재료의 신맛(s)과 쓴맛(b)을 저장할 배열
    static int[] s, b;

    // 신맛과 쓴맛의 최소 차이를 저장할 변수 (초기값은 가장 큰 정수)
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 재료 개수 입력
        N = Integer.parseInt(br.readLine());
        s = new int[N];
        b = new int[N];

        // 각 재료의 신맛(s)과 쓴맛(b)을 입력받음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken()); // 신맛
            b[i] = Integer.parseInt(st.nextToken()); // 쓴맛
        }

        // DFS 탐색 시작 (초기값: 신맛=1, 쓴맛=0, 선택한 재료 개수=0)
        dfs(0, 1, 0, 0);

        // 최소 차이 출력
        System.out.println(min);
    }

    /**
     * 깊이 우선 탐색 (DFS)으로 모든 재료 조합을 확인
     *
     * @param idx 현재 검사 중인 재료 인덱스 (0 ~ N-1)
     * @param S   지금까지 선택한 재료들의 신맛의 곱
     * @param B   지금까지 선택한 재료들의 쓴맛의 합
     * @param cnt 지금까지 선택한 재료의 개수
     */
    static void dfs(int idx, int S, int B, int cnt) {
        // 모든 재료를 다 확인했다면 (기저 조건)
        if (idx == N) {
            // 재료를 하나라도 선택한 경우만 계산
            if (cnt > 0) {
                // 신맛과 쓴맛의 차이 계산
                int diff = Math.abs(S - B);
                // 최소값 갱신
                min = Math.min(min, diff);
            }
            return; // 탐색 종료
        }

        // ----------------------------
        // ① 현재 idx 번째 재료를 선택하는 경우
        //    → 신맛은 곱해지고, 쓴맛은 더해짐
        dfs(idx + 1, S * s[idx], B + b[idx], cnt + 1);

        // ----------------------------
        // ② 현재 idx 번째 재료를 선택하지 않는 경우
        dfs(idx + 1, S, B, cnt);
    }
}
