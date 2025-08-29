package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon15686 {
    // 도시의 크기 N (NxN), 폐업시키지 않을 치킨집의 수 M
    static int N, M;

    // 도시 정보를 담는 2차원 배열 (1: 집, 2: 치킨집)
    static int[][] s;

    // 도시의 치킨 거리의 최소값을 저장할 변수
    static int sub = Integer.MAX_VALUE;

    // 치킨집 위치 리스트
    static ArrayList<int[]> chicken;

    // 집 위치 리스트
    static ArrayList<int[]> house;

    // 조합에서 선택된 치킨집을 체크하기 위한 방문 배열
    static boolean[] visited;

    // 각 집마다 선택된 치킨집 중 최소 거리
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N과 M 입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 도시 정보를 담을 배열 생성 (1-based index 사용)
        s = new int[N + 1][N + 1];

        // 치킨집과 집 위치를 저장할 리스트 초기화
        chicken = new ArrayList<>();
        house = new ArrayList<>();

        // 도시 정보를 입력받고, 치킨집과 집 위치 저장
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
                if (s[i][j] == 1) {
                    house.add(new int[]{i, j});  // 집 좌표 저장
                } else if (s[i][j] == 2) {
                    chicken.add(new int[]{i, j});  // 치킨집 좌표 저장
                }
            }
        }

        // 치킨집 개수만큼 방문 배열 초기화
        visited = new boolean[chicken.size()];

        // 치킨집 조합 선택 시작 (index 0부터 시작, 선택된 개수 0개)
        sol(0, 0);

        // 최소 도시 치킨 거리 출력
        System.out.println(sub);
    }

    // 치킨집 조합을 선택하는 백트래킹 함수
    static void sol(int idx, int depth) {
        // M개의 치킨집을 선택했을 경우
        if (depth == M) {
            int sum = 0;  // 현재 조합의 총 도시 치킨 거리

            // 모든 집에 대해 가장 가까운 치킨집 거리 구하기
            for (int[] h : house) {
                min = Integer.MAX_VALUE;

                for (int i = 0; i < chicken.size(); i++) {
                    if (visited[i]) {
                        // 치킨 거리 계산: |x1 - x2| + |y1 - y2|
                        int qu = Math.abs(h[0] - chicken.get(i)[0]) + Math.abs(h[1] - chicken.get(i)[1]);
                        min = Math.min(min, qu);  // 가장 가까운 치킨집 거리 저장
                    }
                }

                sum += min;  // 해당 집의 최소 치킨 거리 누적
            }

            // 현재 조합의 총 치킨 거리와 기존 최솟값 비교
            sub = Math.min(sum, sub);
            return;
        }

        // 치킨집 중에서 M개를 선택하는 조합 생성 (중복 없이)
        for (int i = idx; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;        // 현재 치킨집 선택
                sol(i + 1, depth + 1);    // 다음 치킨집 선택으로 재귀 호출
                visited[i] = false;       // 백트래킹 (선택 해제)
            }
        }
    }
}
