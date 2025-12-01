package Programmers;
import java.util.*;

public class programmers16 {

    // 보드 크기(N x N)
    static int N;

    // 상하좌우 이동 벡터
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 메인 solution 함수
    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;

        // 1. game_board에서 "빈칸(0)" 영역들을 모두 추출
        List<List<int[]>> blanks = extractShapes(game_board, 0);

        // 2. table에서 "조각(1)" 영역들을 모두 추출
        List<List<int[]>> blocks = extractShapes(table, 1);

        int answer = 0;

        // table 조각이 이미 사용되었는지 체크하는 배열
        boolean[] used = new boolean[blocks.size()];

        // 3. 빈칸들과 조각을 하나씩 매칭
        for (List<int[]> blank : blanks) {

            // 빈칸 좌표를 정렬 (비교를 위해 항상 정렬된 상태 유지)
            Collections.sort(blank, comparator);

            // 모든 조각을 순회하며 매칭되는 것 찾기
            for (int i = 0; i < blocks.size(); i++) {
                if (used[i]) continue;   // 이미 사용된 조각이면 패스

                List<int[]> block = blocks.get(i);

                // 조각 칸 개수 다르면 애초에 불가능
                if (blank.size() != block.size()) continue;

                // 회전 4번 시도해서 모양이 같으면 매칭 성공
                if (match(blank, block)) {
                    used[i] = true;              // 조각 사용 처리
                    answer += blank.size();      // 빈칸 크기만큼 정답 증가
                    break;
                }
            }
        }
        return answer;
    }

    // ========================================================
    // board에서 target(0 또는 1)으로 이루어진 연결 영역을 모두 추출
    // ========================================================
    private List<List<int[]>> extractShapes(int[][] board, int target) {
        boolean[][] visited = new boolean[N][N];
        List<List<int[]>> shapes = new ArrayList<>();

        // 보드 전체 탐색
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {

                // 아직 방문 안했고 target 값이면 BFS로 shape 추출
                if (!visited[x][y] && board[x][y] == target) {
                    shapes.add(bfs(board, visited, x, y, target));
                }
            }
        }
        return shapes;
    }

    // ========================================================
    // BFS로 하나의 영역(도형)을 추출
    // ========================================================
    private List<int[]> bfs(int[][] board, boolean[][] visited, int sx, int sy, int target) {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        // 도형을 이루는 좌표들의 리스트
        // 첫 좌표를 기준점으로 삼기 때문에 (0,0) 시작
        List<int[]> shape = new ArrayList<>();
        shape.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            // 사방 탐색
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 범위 밖이면 건너뛰기
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                // 방문 안했고 target 값이면 영역 확장
                if (!visited[nx][ny] && board[nx][ny] == target) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});

                    // 기준점 (sx,sy)로부터 상대 좌표값 넣기
                    shape.add(new int[]{nx - sx, ny - sy});
                }
            }
        }

        // (0,0)부터 시작하도록 정규화
        normalize(shape);

        // 좌표 정렬 (비교를 위해 항상 일정한 순서)
        Collections.sort(shape, comparator);

        return shape;
    }

    // ========================================================
    // 도형 좌표들을 (0,0)을 시작점으로 만드는 normalize 과정
    // ========================================================
    private void normalize(List<int[]> shape) {

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        // 가장 작은 x, y 찾기
        for (int[] p : shape) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
        }

        // 모든 좌표를 minX, minY만큼 평행 이동시키기
        for (int[] p : shape) {
            p[0] -= minX;
            p[1] -= minY;
        }
    }

    // ========================================================
    // 도형 90도 회전 (x,y)->(y,-x)
    // 후 normalize + sort
    // ========================================================
    private List<int[]> rotate(List<int[]> shape) {

        List<int[]> newShape = new ArrayList<>();

        for (int[] p : shape) {
            // 90도 회전 공식
            newShape.add(new int[]{p[1], -p[0]});
        }

        // 회전 후 다시 (0,0) 기준으로 정규화
        normalize(newShape);

        // 비교를 위해 정렬
        Collections.sort(newShape, comparator);

        return newShape;
    }

    // ========================================================
    // blank와 block이 회전 4번 중 하나라도 같으면 true
    // ========================================================
    private boolean match(List<int[]> blank, List<int[]> block) {

        List<int[]> temp = block;

        for (int r = 0; r < 4; r++) {   // 0도, 90도, 180도, 270도
            if (isSame(blank, temp)) return true;

            // 90도 회전
            temp = rotate(temp);
        }
        return false;
    }

    // ========================================================
    // 두 좌표 리스트가 완전히 같은지 비교
    // ========================================================
    private boolean isSame(List<int[]> a, List<int[]> b) {

        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] ||
                    a.get(i)[1] != b.get(i)[1])
                return false;
        }
        return true;
    }

    // ========================================================
    // 좌표 정렬 기준: x 오름차순 → y 오름차순
    // ========================================================
    Comparator<int[]> comparator = (o1, o2) -> {
        if (o1[0] == o2[0]) return o1[1] - o2[1];
        return o1[0] - o2[0];
    };
}
