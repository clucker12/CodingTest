package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon15684 {
    static int N, M, H;                 // N: 세로선 수, M: 가로선 수, H: 가로선 놓을 수 있는 위치 수
    static int min = Integer.MAX_VALUE; // 최소로 추가할 가로선 개수 저장
    static boolean[][] line;            // line[a][b] = true면 a번 줄에 b번 세로선과 b+1번 세로선 사이에 가로선 존재

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: N(세로선 수), M(기존 가로선 수), H(가로선 위치 수)
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 가로선 배열 초기화 (인덱스 안전하게 H+2 x N+2 크기로 설정)
        line = new boolean[H + 2][N + 2];

        // 기존 가로선 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 줄 번호 (행)
            int b = Integer.parseInt(st.nextToken()); // 세로선 번호 (열)
            line[a][b] = true; // a번 줄의 b세로선과 b+1세로선을 연결하는 가로선 존재
        }

        // 백트래킹 DFS 시작
        dfs(0, 1, 1);

        // 최소 가로선 수가 3보다 크면 -1 출력 (불가능)
        System.out.println(min > 3 ? -1 : min);
    }

    /**
     * 사다리 결과가 i → i로 연결되는지 확인하는 함수
     */
    static boolean check() {
        // 각 세로선마다 검사
        for (int start = 1; start <= N; start++) {
            int k = start; // 현재 위치 (세로선 번호)

            // 위에서 아래로 내려오며 이동
            for (int i = 1; i <= H; i++) {
                if (line[i][k]) {
                    k++; // 오른쪽 가로선 만나면 오른쪽으로 이동
                } else if (k > 1 && line[i][k - 1]) {
                    k--; // 왼쪽 가로선 만나면 왼쪽으로 이동
                }
            }

            // 시작과 도착이 다르면 실패
            if (k != start) return false;
        }
        return true; // 모두 i → i로 도착하면 true
    }

    /**
     * 백트래킹을 이용한 DFS
     * @param cnt 현재까지 추가한 가로선 개수
     * @param x   가로줄 시작 위치 (줄 번호)
     * @param y   세로선 시작 위치 (열 번호)
     */
    static void dfs(int cnt, int x, int y) {
        // 현재 가로선 개수가 이미 최소값 이상이면 탐색 중단
        if (cnt >= min) return;

        // 유효한 사다리 구조이면 최소값 갱신
        if (check()) {
            min = cnt;
            return;
        }

        // 최대 3개까지만 추가 가능
        if (cnt == 3) return;

        // 가로줄 i를 x부터 H까지 순회
        for (int i = x; i <= H; i++) {
            // j: 세로선 번호 (가로선을 놓을 수 있는 위치는 1~N-1)
            for (int j = 1; j < N; j++) {
                // 현재 위치나 양 옆에 가로선이 이미 있으면 놓을 수 없음
                if (line[i][j] || line[i][j - 1] || line[i][j + 1]) continue;

                // 가로선 추가
                line[i][j] = true;
                dfs(cnt + 1, i, j); // 다음 단계로 탐색
                line[i][j] = false; // 백트래킹: 다시 원상 복구
            }
        }
    }
}
