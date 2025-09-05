package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1182 {
    // 전역 변수 선언
    static int N;         // 수열의 길이
    static int S;         // 목표 합
    static int[] s;       // 입력된 수열 저장 배열
    static int cnt = 0;   // 조건을 만족하는 부분수열의 개수 카운트

    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 줄: N과 S 입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 수열의 길이
        S = Integer.parseInt(st.nextToken());  // 목표 합

        // 수열을 저장할 배열 생성
        s = new int[N];

        // 두 번째 줄: 수열 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        // 부분수열 탐색 시작 (인덱스 0부터 시작, 합은 0으로 시작)
        dfs(0, 0);

        // S == 0일 경우, 공집합도 sum == 0이 되기 때문에 1개 빼줌
        if (S == 0) cnt--;

        // 정답 출력
        System.out.println(cnt);
    }

    // dfs: 현재 인덱스와 현재까지의 합을 받아서 모든 부분수열을 탐색
    static void dfs(int idx, int sum) {
        // 종료 조건: 수열의 끝까지 도달했을 때
        if (idx == N) {
            // 현재까지의 합이 S와 같으면 조건 만족 → 카운트 증가
            if (sum == S) {
                cnt++;
            }
            return; // 현재 재귀 호출 종료, 한 단계 위로 되돌아감
        }

        // 현재 원소 s[idx]를 **포함하는 경우**
        dfs(idx + 1, sum + s[idx]);

        // 현재 원소 s[idx]를 **포함하지 않는 경우**
        dfs(idx + 1, sum);
    }
}
