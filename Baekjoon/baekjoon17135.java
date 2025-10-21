package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon17135 {
    static int N, M, D;                   // 맵 크기 N행 M열, 공격 거리 D
    static int[][] originalMap;           // 초기 맵 정보 저장용
    static int maxKill = 0;               // 최대 처치한 적 수
    static int[] archerPos = new int[3];  // 3명의 궁수 위치(열 번호)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: N, M, D 값 읽기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        originalMap = new int[N][M];

        // 맵 정보 입력 받기 (0: 빈칸, 1: 적)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 3명의 위치 조합 뽑기 시작 (조합 함수 호출)
        conbination(0, 0);

        // 최대 처치 수 출력
        System.out.println(maxKill);
    }

    // 궁수 위치 조합 구하는 함수 (M열 중 3개 선택)
    static void conbination(int start, int depth){
        if(depth == 3){
            // 궁수 위치 3개 다 선택 완료하면 시뮬레이션 실행
            simulate();
            return;
        }

        // 조합 만들기: start부터 M-1까지 열 중 하나 선택
        for (int i = start; i < M; i++) {
            archerPos[depth] = i;            // 궁수 위치 설정
            conbination(i + 1, depth + 1);  // 다음 궁수 위치 선택하러 재귀 호출
        }
    }

    // 게임 시뮬레이션 함수
    static void simulate(){
        // 원본 맵을 복사해서 매 시뮬레이션마다 초기화
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = originalMap[i].clone();
        }

        int killcnt = 0;  // 이번 시뮬레이션에서 처치한 적 수

        // 게임 턴 진행 (최대 N번 적이 한 칸씩 내려옴)
        for (int turn = 0; turn < N; turn++) {
            List<int[]> targets = new ArrayList<>(); // 이번 턴에 공격할 적 위치 저장

            // 궁수 3명 각각 공격할 적 찾기
            for (int col : archerPos){
                int[] target = bfs(map, N, col);   // BFS로 공격 대상 찾기
                if(target != null && !isDuplicate(targets,target)){ // 중복 대상 아니면
                    targets.add(target);  // 타겟 리스트에 추가
                }
            }

            // 타겟 리스트에 있는 적들 제거 (죽임)
            for (int[] t : targets) {
                int r = t[0];
                int c = t[1];
                if (map[r][c] == 1) {   // 적이 실제 있으면
                    map[r][c] = 0;      // 제거
                    killcnt++;          // 처치 수 증가
                }
            }

            // 적이 한 칸 아래로 이동
            for (int i = N - 1; i > 0; i--) {
                map[i] = map[i - 1].clone(); // 위 행 복사해서 아래 행으로 이동
            }
            Arrays.fill(map[0], 0);          // 맨 윗줄은 새로 빈칸으로 초기화
        }

        // 이번 시뮬레이션에서 최대 처치 수 갱신
        maxKill = Math.max(maxKill, killcnt);
    }

    // BFS 탐색으로 궁수가 공격할 적 찾기
    // archerRow: 궁수 행 위치 (항상 N, 맵 바깥)
    // archerCol: 궁수 열 위치
    static int[] bfs(int[][] map, int archerRow, int archerCol){
        boolean[][] visited = new boolean[N][M];    // 방문 체크용
        Queue<int[]> queue = new LinkedList<>();

        // 궁수 바로 위 행부터 탐색 시작 (궁수는 N행, 맵 밖이므로 N-1행부터)
        queue.offer(new int[]{archerRow - 1, archerCol, 1});  // {행, 열, 거리}

        // 탐색 방향: 왼쪽(0,-1), 위(-1,0), 오른쪽(0,1) 순서로 탐색 (문제 조건에 따른 우선순위)
        int[] dr = {0, -1, 0};
        int[] dc = {-1, 0, 1};

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int row = cur[0], col = cur[1], dist = cur[2];

            if(dist > D) break;   // 공격 거리 넘으면 탐색 중단

            if(row >= 0 && col >= 0 && col < M && !visited[row][col]){
                visited[row][col] = true;

                if (map[row][col] == 1){   // 적 발견 시 바로 공격 대상 반환
                    return new int[]{row, col};
                }

                // 현재 위치 기준 3방향 탐색
                for (int i = 0; i < 3; i++) {
                    int nr = row + dr[i];
                    int nc = col + dc[i];
                    if(nr >= 0 && nc >= 0 && nc < M){
                        queue.offer(new int[]{nr, nc, dist + 1});
                    }
                }
            }
        }
        return null;  // 공격 대상 못 찾음
    }

    // targets 리스트 내에 같은 적 위치가 있는지 확인하는 함수 (중복 제거용)
    static boolean isDuplicate(List<int[]> list, int[] target) {
        for (int[] t : list) {
            if (t[0] == target[0] && t[1] == target[1]) return true;
        }
        return false;
    }
}
