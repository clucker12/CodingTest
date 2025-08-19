package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon14888 {
    // 변수 선언
    static int N; // 숫자의 개수
    static int[] A; // 숫자 배열 (1부터 시작)
    static int[] operators = new int[5]; // 연산자 개수: [1] +, [2] -, [3] *, [4] /
    static int max = Integer.MIN_VALUE; // 최댓값 초기화
    static int min = Integer.MAX_VALUE; // 최솟값 초기화

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 숫자 개수 입력
        N = Integer.parseInt(br.readLine());
        A = new int[N+1]; // 1-based index를 쓰기 위해 크기를 N+1로

        // 숫자 입력 (A[1] ~ A[N])
        String[] input = br.readLine().split(" ");
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(input[i-1]);
        }

        // 연산자 개수 입력: + - * /
        String[] op = br.readLine().split(" ");
        for(int i = 1; i <= 4; i++){
            operators[i] = Integer.parseInt(op[i-1]);
        }

        // DFS 탐색 시작 (두 번째 숫자부터, 현재 결과는 A[1])
        dfs(2, A[1]);

        // 결과 출력
        System.out.println(max);
        System.out.println(min);
    }

    // DFS 메서드: index번째 숫자를 계산에 포함시키기 위한 재귀 함수
    public static void dfs(int index, int result){
        // 종료 조건: 모든 숫자를 사용했을 때
        if(index > N){
            max = Math.max(max, result); // 최대값 갱신
            min = Math.min(min, result); // 최소값 갱신
            return;
        }

        // 연산자 4종류에 대해 하나씩 시도
        for(int i = 1; i <= 4; i++){
            if(operators[i] > 0){ // 해당 연산자가 남아 있다면
                operators[i]--; // 연산자 하나 사용

                int next = 0; // 다음 계산 결과 저장용

                // i에 따라 해당 연산 수행
                if(i == 1) next = result + A[index];           // +
                else if(i == 2) next = result - A[index];      // -
                else if(i == 3) next = result * A[index];      // *
                else if(i == 4){                               // /
                    // 음수 나눗셈은 C++14 방식으로 처리
                    if(result < 0){
                        next = -(-result / A[index]);
                    } else {
                        next = result / A[index];
                    }
                }

                // 다음 숫자로 넘어가며 재귀 호출
                dfs(index + 1, next);

                // 백트래킹: 사용한 연산자 복구
                operators[i]++;
            }
        }
    }
}
