package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon1941 {
    // 5x5 보드에 S(이다솜파), Y(임도연파)를 저장
    static char[][] board = new char[5][5];
    // 정답 (가능한 칠공주 조합의 수)
    static int answer = 0;
    // 상하좌우 이동 방향
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 5줄 입력받아 5x5 보드에 저장
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        // 25개의 좌석 중 7개를 선택하기 위한 boolean 배열
        boolean[] selected = new boolean[25];

        // 0번째 인덱스부터 25개 중 7개를 고르는 DFS 시작
        dfs(0, 0, selected);

        // 가능한 모든 칠공주의 경우의 수 출력
        System.out.println(answer);
    }

    /**
     * dfs(idx, depth, selected)
     * → 25칸 중 7칸을 선택하는 조합을 만드는 함수
     *
     * @param idx      현재 탐색 중인 인덱스 (0~24)
     * @param depth    지금까지 선택한 칸의 개수
     * @param selected 어떤 칸이 선택되었는지를 저장하는 배열
     */
    static void dfs(int idx, int depth, boolean[] selected){
        // 7개를 다 골랐다면 조건 검사
        if(depth == 7){
            if(isValid(selected)) answer++; // 조건을 만족하면 경우의 수 증가
            return;
        }

        // 25칸을 다 봤는데 7개를 못 골랐다면 종료
        if(idx >= 25) return;

        // 현재 idx 칸을 선택하는 경우
        selected[idx] = true;
        dfs(idx + 1, depth + 1, selected);

        // 현재 idx 칸을 선택하지 않는 경우
        selected[idx] = false;
        dfs(idx + 1, depth, selected);
    }

    /**
     * isValid(selected)
     * → 현재 선택된 7칸이 '이다솜파 4명 이상' & '모두 연결되어 있는지'를 확인
     */
    static boolean isValid(boolean[] selected){
        List<int[]> positions = new ArrayList<>(); // 선택된 칸의 좌표 저장
        int sCnt = 0; // S의 개수 (이다솜파 수)

        // 선택된 칸들 좌표 추출 및 S 개수 세기
        for (int i = 0; i < 25; i++) {
            if(selected[i]){
                int x = i / 5; // 행
                int y = i % 5; // 열
                positions.add(new int[]{x, y});
                if(board[x][y] == 'S') sCnt++; // 이다솜파 학생이면 카운트 증가
            }
        }

        // 이다솜파가 4명 미만이면 조건 불충족
        if(sCnt < 4) return false;

        // 선택된 7칸이 서로 연결되어 있는지 BFS로 검사
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];

        // 첫 번째 선택된 칸부터 탐색 시작
        q.add(positions.get(0));
        visited[positions.get(0)[0]][positions.get(0)[1]] = true;
        int connected = 1; // 연결된 칸 수

        // BFS 탐색 시작
        while(!q.isEmpty()){
            int[] cur = q.poll(); // 현재 좌표 꺼내기

            // 상하좌우 4방향 검사
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 1) 범위 벗어나면 무시
                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                // 2) 이미 방문한 곳이면 무시
                if(visited[nx][ny]) continue;

                // 3) 선택되지 않은 칸이면 무시
                if(!selected[nx * 5 + ny]) continue;

                // 위 조건을 통과했다면 같은 그룹에 속하는 인접 칸
                visited[nx][ny] = true;
                connected++;
                q.add(new int[]{nx, ny});
            }
        }

        // BFS 결과로 7칸이 모두 연결돼 있으면 true 반환
        return connected == 7;
    }
}
