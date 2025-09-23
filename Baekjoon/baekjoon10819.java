package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10819 {

    // 전역 변수 선언
    static int N;              // 배열 A의 크기
    static int[] A;           // 입력받은 정수 배열
    static boolean[] visited; // 해당 인덱스를 이미 사용했는지 체크
    static int[] perm;        // 현재 생성 중인 순열 배열
    static int max = 0;       // 식의 최댓값 저장

    public static void main(String[] args)throws IOException {
        // 입력을 빠르게 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 줄: 배열의 길이 입력
        N = Integer.parseInt(br.readLine());

        // 배열과 기타 변수 초기화
        A = new int[N];
        perm = new int[N];
        visited = new boolean[N];

        // 둘째 줄: 배열 A 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 순열 생성 및 계산 시작
        dfs(0);

        // 결과 출력
        System.out.println(max);
    }

    // 깊이 우선 탐색 (DFS) 기반 순열 생성 함수
    static void dfs(int depth){
        // 순열 완성 (N개의 숫자를 모두 선택했을 때)
        if(depth == N){
            int sum = 0;
            // 완성된 perm 배열로 문제에서 주어진 식 계산
            for (int i = 0; i < N-1; i++) {
                sum += Math.abs(perm[i] - perm[i+1]);
            }
            // 현재 순열의 결과가 최댓값보다 크면 갱신
            max = Math.max(sum, max);
            return;
        }

        // 아직 선택하지 않은 숫자를 선택
        for (int i = 0; i < N; i++) {
            if(!visited[i]){ // 아직 A[i]를 사용하지 않았다면
                visited[i] = true;       // 사용 처리
                perm[depth] = A[i];      // 현재 순열의 해당 위치에 값 할당
                dfs(depth+1);            // 다음 깊이로 재귀 호출
                visited[i] = false;      // 백트래킹: 사용 해제 (다른 경우의 수 탐색)
            }
        }
    }
}
