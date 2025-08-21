package Baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon14502 {
    // 연구소의 크기
    static int N, M;

    // 연구소 지도: 0(빈 칸), 1(벽), 2(바이러스)
    static int[][] lab;

    // 시뮬레이션용 임시 배열 (lab 복사본)
    static int[][] temp;

    // 빈 칸 좌표 저장 리스트
    static List<int[]> empty = new ArrayList<>();

    // 바이러스 좌표 저장 리스트
    static List<int[]> virus = new ArrayList<>();

    // 최대 안전 영역 크기 저장 변수
    static int maxsafe = 0;

    // 4방향 탐색 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N, M 입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M]; // 지도 배열 초기화

        // 연구소 지도 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

                if (lab[i][j] == 0) {
                    empty.add(new int[]{i, j}); // 빈 칸 위치 저장
                } else if (lab[i][j] == 2) {
                    virus.add(new int[]{i, j}); // 바이러스 위치 저장
                }
            }
        }

        // 3개의 벽을 세우는 모든 조합을 시도
        combination(0, 0, new int[3][]);

        // 최대 안전 영역 크기 출력
        System.out.println(maxsafe);
    }

    /**
     * 빈 칸들 중에서 3개를 조합으로 선택하여 벽을 세우는 함수
     * @param start - 조합의 시작 인덱스
     * @param depth - 현재까지 선택한 벽의 개수
     * @param selected - 선택된 벽 3개의 좌표 배열
     */
    static void combination(int start, int depth, int[][] selected) {
        if (depth == 3) {
            simulate(selected); // 3개 선택 완료 → 시뮬레이션 실행
            return;
        }

        // 조합 방식으로 3개 좌표 선택
        for (int i = start; i < empty.size(); i++) {
            selected[depth] = empty.get(i); // 현재 좌표 선택
            combination(i + 1, depth + 1, selected); // 다음 좌표 선택
        }
    }

    /**
     * 선택된 좌표에 벽을 세우고, 바이러스를 퍼뜨려서
     * 안전 영역의 크기를 계산하고 최대값 갱신
     */
    static void simulate(int[][] walls) {
        // 원본 lab을 temp로 복사 (deep copy)
        temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(lab[i], 0, temp[i], 0, M);
        }

        // 선택된 좌표에 벽(1) 세우기
        for (int[] wall : walls) {
            temp[wall[0]][wall[1]] = 1;
        }

        // 바이러스 퍼뜨리기
        spreadVirus();

        // 안전 영역 크기 계산
        int safe = countSafe();

        // 최대값 갱신
        maxsafe = Math.max(maxsafe, safe);
    }

    /**
     * BFS로 바이러스를 확산시키는 함수
     */
    static void spreadVirus() {
        Queue<int[]> queue = new LinkedList<>();

        // 초기 바이러스 위치들을 큐에 추가
        for (int[] v : virus) {
            queue.offer(new int[]{v[0], v[1]});
        }

        // 큐가 빌 때까지 바이러스 확산
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            // 상하좌우로 퍼지기
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 범위 내에 있고, 빈 칸이면 바이러스 전파
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && temp[nx][ny] == 0) {
                    temp[nx][ny] = 2; // 바이러스 전파
                    queue.offer(new int[]{nx, ny}); // 다음 위치 큐에 추가
                }
            }
        }
    }

    /**
     * 바이러스가 퍼진 후 남아있는 안전 영역(0)의 개수 계산
     */
    static int countSafe() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
