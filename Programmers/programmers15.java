package Programmers;

import java.util.*;

public class programmers15 {
    // 티켓 사용 여부를 기록하는 배열
    static boolean[] visited;

    // 여행 경로를 저장하는 리스트
    static List<String> answer = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        // 1. 도착지를 기준으로 사전순 정렬
        //    -> DFS에서 먼저 선택된 티켓이 알파벳 순서가 앞서도록 보장
        Arrays.sort(tickets,(a,b) -> a[1].compareTo(b[1]));

        // 2. 방문 배열 초기화
        visited = new boolean[tickets.length];

        // 3. 항상 "ICN"에서 출발
        answer.add("ICN");

        // 4. DFS 호출: 현재 위치 "ICN", depth 0
        dfs("ICN", 0, tickets);

        // 5. List를 배열로 변환하여 반환
        return answer.toArray(new String[0]);
    }

    // DFS 함수: 현재 위치(ticket), 현재 사용한 티켓 수(depth), 전체 티켓 배열
    static boolean dfs(String ticket, int depth, String[][] tickets){
        // 6. 모든 티켓을 다 사용했으면 성공
        if(depth == tickets.length){
            return true;
        }

        // 7. 모든 티켓을 순회하며 선택 가능한 티켓 탐색
        for (int i = 0; i < tickets.length; i++) {
            // 8. 조건: 아직 사용하지 않았고, 출발지가 현재 위치와 일치하는 티켓
            if(!visited[i] && tickets[i][0].equals(ticket)){
                // 9. 티켓 사용 체크
                visited[i] = true;
                // 10. 경로에 목적지 추가
                answer.add(tickets[i][1]);

                // 11. 재귀 DFS 호출: 다음 위치, 사용 티켓 +1
                if(dfs(tickets[i][1], depth + 1, tickets)){
                    // 12. 성공 경로 발견 시 바로 true 반환하여 상위 DFS 종료
                    return true;
                }

                // 13. DFS 실패 시 백트래킹: 방문 체크 원복, 경로 제거
                visited[i] = false;
                answer.remove(answer.size() - 1);
            }
        }

        // 14. 모든 티켓을 시도해도 실패하면 false 반환
        return false;
    }
}
