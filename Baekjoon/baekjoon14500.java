package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14500 {
    // 종이의 크기 (행: N, 열: M)
    static int N, M;

    // 각 칸에 적힌 숫자
    static int[][] point;

    // DFS 탐색 시 방문 여부 체크용
    static boolean[][] visited;

    // 최대 합을 저장하는 변수
    static int max = 0;

    // 상, 하, 좌, 우 방향 이동
    static int[] dx = {-1, 1, 0, 0};  // x축 방향
    static int[] dy = {0, 0, -1, 1};  // y축 방향

    // DFS로 만들 수 없는 예외 모양 (ㅗ, ㅜ, ㅓ, ㅏ)
    static int[][][] shapes = {
            {{0, 0}, {-1, 0}, {0, -1}, {0, 1}}, // ㅗ
            {{0, 0}, {1, 0}, {0, -1}, {0, 1}},  // ㅜ
            {{0, 0}, {-1, 0}, {1, 0}, {0, -1}}, // ㅓ
            {{0, 0}, {-1, 0}, {1, 0}, {0, 1}}   // ㅏ
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: N(행), M(열)
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 배열 초기화
        point = new int[N][M];
        visited = new boolean[N][M];

        // 각 칸에 숫자 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                point[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 좌표에서 테트로미노 하나를 놓는 시도
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 시작점 방문 처리 후 DFS 탐색
                visited[i][j] = true;
                dfs(i, j, 1, point[i][j]);
                visited[i][j] = false;

                // DFS로 만들 수 없는 예외 모양 체크 (ㅗ 모양 계열)
                checkExtraShapes(i, j);
            }
        }

        // 최대합 출력
        System.out.println(max);
    }

    // DFS로 깊이 4까지 탐색 (연결된 4칸)
    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            // 4칸 다 채웠으면 최대값 갱신
            max = Math.max(sum, max);
            return;
        }

        // 상하좌우 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 벗어나면 무시
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }

            // 이미 방문한 칸은 무시
            if (visited[nx][ny]) continue;

            // 방문 표시 후 재귀 호출
            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + point[nx][ny]);
            visited[nx][ny] = false; // 백트래킹
        }
    }

    // DFS로 만들 수 없는 예외 모양들(ㅗ, ㅜ, ㅓ, ㅏ)을 체크
    static void checkExtraShapes(int x, int y) {
        for (int[][] shape : shapes) { // 각 모양에 대해
            int sum = 0;
            boolean isValid = true;

            for (int[] block : shape) { // 각 블록 좌표 확인
                int nx = x + block[0];
                int ny = y + block[1];

                // 종이 밖이면 이 모양은 무시
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    isValid = false;
                    break;
                }

                // 합산
                sum += point[nx][ny];
            }

            // 유효한 모양일 경우 최대값 갱신
            if (isValid) {
                max = Math.max(max, sum);
            }
        }
    }
}
