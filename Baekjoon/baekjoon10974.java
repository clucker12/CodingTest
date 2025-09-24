package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon10974 {
    // 전역 변수 선언
    static int N;              // 숫자의 개수 (1부터 N까지)
    static int[] perm;         // 순열을 저장할 배열
    static boolean[] visited;  // 숫자 사용 여부를 체크하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력 받기
        N = Integer.parseInt(br.readLine());

        // 배열 초기화
        perm = new int[N];         // 순열 배열 (길이 N)
        visited = new boolean[N];  // 숫자 사용 여부 체크 (0~N-1까지)

        // 순열 생성 시작 (깊이 0부터 시작)
        dfs(0);
    }

    // 깊이 우선 탐색 (백트래킹)으로 순열 생성
    static void dfs(int depth) {
        // 종료 조건: 순열이 완성되었을 경우 (depth == N)
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(perm[i] + " ");
            }
            System.out.println();  // 한 줄 출력 후 줄 바꿈
            return;
        }

        // 1부터 N까지 숫자 중 하나 선택 (i는 0부터 N-1까지)
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {              // 아직 사용하지 않은 숫자라면
                visited[i] = true;          // 해당 숫자 사용 처리
                perm[depth] = i + 1;        // 순열 배열에 숫자 저장 (i+1이 실제 숫자)
                dfs(depth + 1);             // 다음 깊이로 재귀 호출
                visited[i] = false;         // 백트래킹: 숫자 사용 해제
            }
        }
    }
}
