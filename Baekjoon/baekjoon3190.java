package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class baekjoon3190 {
    static int N; // 보드 크기
    static Deque<int[]> snake = new ArrayDeque<>(); // 뱀의 몸 좌표를 저장 (머리: 앞, 꼬리: 뒤)
    static HashMap<Integer, Character> change = new HashMap<>(); // 방향 전환 정보 저장 (시간 -> 'L' 또는 'D')
    static int[][] board; // 보드 상태 (0: 빈 칸, 1: 사과, 2: 뱀 몸)

    // 이동 방향 (오른쪽, 아래, 왼쪽, 위 순서)
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 보드 크기 입력
        board = new int[N][N]; // 보드 초기화

        int K = Integer.parseInt(br.readLine()); // 사과 개수
        for (int i = 0; i < K; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]); // 행
            int y = Integer.parseInt(input[1]); // 열
            board[x - 1][y - 1] = 1; // 사과 위치 표시 (1-based → 0-based 변환)
        }

        int L = Integer.parseInt(br.readLine()); // 방향 전환 횟수
        for (int i = 0; i < L; i++) {
            String[] input = br.readLine().split(" ");
            int X = Integer.parseInt(input[0]); // X초 후
            char C = input[1].charAt(0);        // 'L' 또는 'D'
            change.put(X, C); // 시간과 회전 방향 저장
        }

        System.out.println(test()); // 시뮬레이션 실행
    }

    static int test() {
        int time = 0; // 게임 시간
        int move = 0; // 현재 방향 인덱스 (0: 오른쪽)
        int x = 0, y = 0; // 뱀의 시작 위치

        snake.add(new int[]{x, y}); // 시작 위치 뱀 몸에 추가
        board[x][y] = 2; // 보드에 뱀 표시

        while (true) {
            time++; // 1초 경과

            // 다음 위치 계산
            int nx = x + dx[move];
            int ny = y + dy[move];

            // 벽 또는 자기 몸과 부딪히면 게임 종료
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 2) {
                break;
            }

            if (board[nx][ny] == 1) { // 사과가 있으면
                board[nx][ny] = 2; // 사과 먹고 뱀 몸으로 표시
                snake.addFirst(new int[]{nx, ny}); // 머리 위치 추가 (길이 증가)
            } else { // 사과가 없으면
                board[nx][ny] = 2; // 이동한 칸을 뱀 몸으로 표시
                snake.addFirst(new int[]{nx, ny}); // 머리 위치 추가
                int[] tail = snake.removeLast(); // 꼬리 위치 제거 (길이 유지)
                board[tail[0]][tail[1]] = 0; // 보드에서 꼬리 제거
            }

            // 현재 시간이 방향 전환 시각이면
            if (change.containsKey(time)) {
                char c = change.get(time);
                if (c == 'D') {
                    move = (move + 1) % 4; // 오른쪽 회전
                } else {
                    move = (move + 3) % 4; // 왼쪽 회전 (== -1 + 4 % 4)
                }
            }

            // 현재 위치 갱신
            x = nx;
            y = ny;
        }

        return time; // 게임이 종료된 시간 반환
    }
}
