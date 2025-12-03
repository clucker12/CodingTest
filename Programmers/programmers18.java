package Programmers;

public class programmers18 {
    public int solution(int n, int[][] results) {

        // wins[a][b] = true  →  a 선수가 b 선수를 이겼다는 뜻
        boolean[][] wins = new boolean[n+1][n+1];

        // 1. 직접 주어진 경기 결과 입력
        for (int[] r : results){
            wins[r[0]][r[1]] = true; // r[0] → r[1]
        }

        // 2. Floyd-Warshall 알고리즘
        //    중간 선수 k를 거쳐가면서
        //    i → k, k → j 라면 i → j 도 true 로 만든다.
        //    즉 모든 승패 관계를 전파함
        for (int k = 1; k <= n; k++) {        // k = 중간 선수
            for (int i = 1; i <= n; i++) {    // i = 출발 선수
                for (int j = 1; j <= n; j++) { // j = 도착 선수

                    // i가 k를 이기고 && k가 j를 이기면
                    // i는 j를 확실히 이긴 것이므로 표시
                    if(wins[i][k] && wins[k][j]){
                        wins[i][j] = true;
                    }
                }
            }
        }

        int answer = 0; // 순위를 확정할 수 있는 선수 수

        // 3. 각 선수 i 에 대해 순위가 확정되는지 확인
        for (int i = 1; i <= n; i++) {
            int cnt = 0;

            // i번 선수와 j번 선수의 승패가 확실한가?
            // wins[i][j] → i가 j를 이김
            // wins[j][i] → i가 j에게 짐
            // 둘 중 하나라도 true면 승패가 확정된 것
            for (int j = 1; j <= n; j++) {
                if(i == j) continue; // 자기 자신은 제외

                if(wins[i][j] || wins[j][i]){
                    cnt++; // j와의 승패 관계가 확실히 정해짐
                }
            }

            // i 선수는 총 n-1명과의 승부가 모두 확정돼야
            // 순위를 정확히 알 수 있음
            if(cnt == n-1){
                answer++;
            }
        }

        return answer;
    }
}
