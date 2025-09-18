package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1107 {
    // 목표 채널 (N), 고장난 버튼 수 (M)
    static int N, M;

    // 고장난 버튼 정보를 저장하는 배열
    // 예: errNum[3] = true → 버튼 3은 고장
    static boolean[] errNum = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 목표 채널 N 입력
        N = Integer.parseInt(br.readLine());

        // 2. 고장난 버튼 수 M 입력
        M = Integer.parseInt(br.readLine());

        // 3. 고장난 버튼들 입력받기
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int n = Integer.parseInt(st.nextToken());
                errNum[n] = true; // 해당 숫자 버튼이 고장남
            }
        }

        // 4. 초기 최소 버튼 수는 (+/-)만 사용해서 이동할 경우
        // 현재 채널 100에서 목표 채널 N까지 거리
        int min = Math.abs(N - 100);

        // 5. 0 ~ 999,999까지 모든 채널 번호를 시도해보며
        // 숫자 버튼으로 만들 수 있는 채널을 찾음
        for (int i = 0; i <= 999999; i++) {
            int len = dfs(i); // 해당 채널 번호를 숫자 버튼으로 누를 수 있는지 확인
            if (len > 0) {
                // 숫자로 입력한 채널 i에서 목표 채널 N까지 거리 + 숫자 누른 횟수
                int cnt = Math.abs(i - N) + len;

                // 최소 횟수 갱신
                min = Math.min(cnt, min);
            }
        }

        // 6. 최소 횟수 출력
        System.out.println(min);
    }

    /**
     * dfs 함수: 특정 채널 번호 n을 숫자 버튼만으로 누를 수 있는지 검사
     * @param n: 시도할 채널 번호
     * @return 누를 수 있으면 자릿수(길이), 누를 수 없으면 0
     */
    static int dfs(int n) {
        // n이 0일 경우 따로 처리
        if (n == 0) {
            return errNum[0] ? 0 : 1; // 0번 버튼이 고장났으면 0, 아니면 1자리
        }

        int len = 0;

        // 각 자리수의 숫자가 고장났는지 확인
        while (n > 0) {
            if (errNum[n % 10]) return 0; // 한 자리라도 고장났으면 불가능
            n /= 10;
            len++; // 자릿수 카운트
        }

        return len; // 입력 가능한 숫자의 자리 수 (버튼 누름 횟수)
    }
}
