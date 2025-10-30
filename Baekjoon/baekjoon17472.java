package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon17472 {
    static int N, M;                  // 지도 크기 (N: 세로, M: 가로)
    static int[][] board;             // 지도 정보 (0: 바다, 1: 땅)
    static boolean[][] visited;       // 섬 라벨링 시 방문 체크
    static int[] dx = {-1, 1, 0, 0};  // 상, 하, 좌, 우 이동 방향
    static int[] dy = {0, 0, -1, 1};

    static int islandCount = 2;       // 섬 번호는 2부터 시작 (1은 원래 입력값)
    static ArrayList<Edge> edges = new ArrayList<>(); // 가능한 모든 다리 목록

    static int[] parent;              // Union-Find 부모 배열

    // 🌉 다리를 표현하는 클래스
    static class Edge implements Comparable<Edge> {
        int from, to, len; // 시작 섬, 도착 섬, 다리 길이
        Edge(int f, int t, int l) {
            from = f;
            to = t;
            len = l;
        }

        // 다리 길이를 기준으로 오름차순 정렬 (Kruskal 알고리즘용)
        public int compareTo(Edge o) {
            return this.len - o.len;
        }
    }

    // ⚙️ 메인 함수
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1️⃣ 입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2️⃣ 섬을 각각 번호로 라벨링
        labelIslands();

        // 3️⃣ 가능한 모든 다리 후보 탐색
        findBridges();

        // 4️⃣ MST (Kruskal 알고리즘으로 최소 다리 길이 합 구하기)
        int answer = kruskal();

        // 5️⃣ 결과 출력 (불가능하면 -1)
        System.out.println(answer);
    }

    // ---------------------------------------------------------
    // 🏝️ 1. BFS로 섬 라벨링
    // ---------------------------------------------------------
    static void labelIslands() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 아직 방문하지 않은 땅이라면 새로운 섬 시작
                if (board[i][j] == 1 && !visited[i][j]) {
                    bfsLabel(i, j, islandCount++); // BFS로 연결된 땅 모두 라벨링
                }
            }
        }
    }

    // BFS로 하나의 섬을 모두 같은 번호로 바꿔줌
    static void bfsLabel(int x, int y, int label) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        board[x][y] = label; // 해당 위치를 섬 번호로 표시

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                // 범위 벗어나면 무시
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                // 땅(1)이고 아직 방문 안했으면 같은 섬으로 처리
                if (!visited[nx][ny] && board[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    board[nx][ny] = label; // 섬 번호 지정
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    // ---------------------------------------------------------
    // 🌉 2. 각 섬에서 다른 섬으로 갈 수 있는 다리 찾기
    // ---------------------------------------------------------
    static void findBridges() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 현재 위치가 섬의 일부라면
                if (board[i][j] >= 2) {
                    int from = board[i][j]; // 출발 섬 번호

                    // 4방향으로 다리 건설 시도
                    for (int d = 0; d < 4; d++) {
                        int len = 0; // 다리 길이
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        while (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                            if (board[nx][ny] == from) break; // 같은 섬 만나면 중단
                            if (board[nx][ny] == 0) { // 바다라면 다리 연장
                                len++;
                                nx += dx[d];
                                ny += dy[d];
                                continue;
                            }

                            // 다른 섬을 만났을 때 다리 길이가 2 이상이면 유효
                            if (len >= 2) {
                                int to = board[nx][ny];
                                edges.add(new Edge(from, to, len));
                            }
                            break; // 섬을 만났으니 이 방향은 중단
                        }
                    }
                }
            }
        }
    }

    // ---------------------------------------------------------
    // 🪢 3. MST - Kruskal 알고리즘
    // ---------------------------------------------------------
    static int kruskal() {
        parent = new int[islandCount];
        // 각 섬의 부모 초기화 (자기 자신)
        for (int i = 2; i < islandCount; i++) parent[i] = i;

        // 다리 길이 오름차순으로 정렬
        Collections.sort(edges);

        int result = 0;    // 총 다리 길이
        int connected = 0; // 연결된 섬 간선 수

        // 짧은 다리부터 연결 시도
        for (Edge e : edges) {
            if (union(e.from, e.to)) { // 두 섬이 아직 연결되지 않았다면
                result += e.len;
                connected++;

                // 모든 섬이 연결되면 조기 종료
                if (connected == islandCount - 3) break;
            }
        }

        // 연결된 간선 수가 부족하면 모든 섬을 연결할 수 없음
        if (connected != islandCount - 3) return -1;
        return result;
    }

    // ---------------------------------------------------------
    // 🔗 Union-Find (서로소 집합)
    // ---------------------------------------------------------
    static int find(int x) {
        if (parent[x] == x) return x;
        // 경로 압축 (재귀로 루트 찾아서 parent 갱신)
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false; // 이미 같은 그룹이면 연결 불필요
        parent[pb] = pa;            // 다르면 합침
        return true;
    }
}
