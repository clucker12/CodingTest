package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2531 {
    // N: 접시 수, d: 초밥 종류 수, k: 연속해서 먹는 접시 수, c: 쿠폰 초밥 번호
    static int N, d, k, c;
    static int[] food;   // 회전 초밥 벨트 위의 초밥들
    static int[] count;  // 각 초밥 종류별 현재 윈도우 내 등장 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 1줄: N, d, k, c
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 접시 수
        d = Integer.parseInt(st.nextToken());  // 초밥 종류 수
        k = Integer.parseInt(st.nextToken());  // 연속으로 먹을 접시 수
        c = Integer.parseInt(st.nextToken());  // 쿠폰 초밥 번호

        // 회전 초밥 벨트 위의 초밥 정보를 입력받음
        food = new int[N];
        for (int i = 0; i < N; i++) {
            food[i] = Integer.parseInt(br.readLine());
        }

        // 각 초밥 종류별 현재 윈도우 내 초밥 개수를 저장하는 배열
        count = new int[d + 1]; // 초밥 번호가 1~d이므로 d+1 크기
        int distinct = 0;       // 현재 윈도우(연속 구간)에서의 서로 다른 초밥 수

        // ✅ 초기 윈도우 설정 (처음 k개의 접시)
        for (int i = 0; i < k; i++) {
            if (count[food[i]] == 0) distinct++; // 처음 등장하는 초밥이면 종류 +1
            count[food[i]]++;                    // 해당 초밥 카운트 증가
        }

        int max = distinct; // 현재까지의 최대 초밥 종류 수 저장

        // ✅ 슬라이딩 윈도우 시작 (회전벨트이므로 N번 반복)
        for (int i = 0; i < N; i++) {
            // 윈도우의 맨 앞 초밥 제거
            int remove = food[i];
            count[remove]--;
            if (count[remove] == 0) distinct--; // 초밥 종류가 0이 되면 종류 수 감소

            // 윈도우의 맨 뒤 초밥 추가 (회전하므로 인덱스 % N)
            int add = food[(i + k) % N];
            if (count[add] == 0) distinct++; // 새로운 종류의 초밥이면 +1
            count[add]++;

            // 현재 윈도우에서 먹을 수 있는 초밥 종류 수 계산
            int currentMax = distinct;

            // 쿠폰 초밥이 현재 윈도우에 포함되지 않은 경우, +1종류 추가 가능
            if (count[c] == 0) currentMax++;

            // 최대값 갱신
            if (currentMax > max) max = currentMax;
        }

        // ✅ 결과 출력: 먹을 수 있는 서로 다른 초밥의 최대 종류 수
        System.out.println(max);
    }
}
