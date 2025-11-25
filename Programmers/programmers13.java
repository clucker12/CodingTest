package Programmers;

public class programmers13 {
    // 최단 횟수를 저장하는 변수 (기본값은 Integer.MAX_VALUE)
    static int min = Integer.MAX_VALUE;

    // solution 메서드 - 시작 단어(begin)와 목표 단어(target), 가능한 단어 리스트(words)를 받아
    // 시작 단어에서 목표 단어로 변환하는 최소 횟수를 구하는 함수
    public int solution(String begin, String target, String[] words) {
        int n = words.length;  // words 배열의 길이 (단어의 개수)
        boolean[] visited = new boolean[n];  // 방문한 단어를 체크하는 배열

        // dfs 호출: 시작 단어(begin), 목표 단어(target), 단어 리스트(words), 방문 배열(visited), 변환 횟수(cnt) 전달
        dfs(begin, target, words, visited, 0);

        // 만약 min이 Integer.MAX_VALUE로 남아있다면, 변환이 불가능하다는 의미이므로 0을 반환
        // 그렇지 않으면 최소 변환 횟수(min)를 반환
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // dfs 메서드 - 깊이 우선 탐색을 이용하여 단어 변환의 최소 횟수를 찾는 함수
    public static void dfs(String begin, String target, String[] words, boolean[] visited, int cnt) {
        // 현재 begin 단어가 target과 같다면, 변환이 완료된 것이므로 최소 횟수 갱신
        if (begin.equals(target)) {
            min = Math.min(min, cnt);  // min과 현재 cnt 값 중 더 작은 값을 min에 저장
            return;  // 종료
        }

        // words 배열을 순차적으로 탐색
        for (int k = 0; k < words.length; k++) {
            // 이미 방문한 단어는 건너뛰기
            if (visited[k]) continue;

            int count = 0;  // begin과 words[k]의 일치하는 문자 개수를 셈
            int n = words[k].length();  // 단어의 길이 (begin과 words[k]는 길이가 같음)

            // begin과 words[k]를 비교하여 일치하는 문자의 개수를 셈
            for (int i = 0; i < n; i++) {
                if (words[k].charAt(i) == begin.charAt(i)) {
                    count++;
                }
            }

            // 만약 두 단어가 딱 하나만 다르면, 즉 일치하는 문자 개수가 begin.length() - 1일 경우
            // 해당 단어를 선택하고 dfs를 진행
            if (count == begin.length() - 1) {
                visited[k] = true;  // 해당 단어는 방문했다고 표시
                dfs(words[k], target, words, visited, cnt + 1);  // words[k]로 변환한 뒤 재귀 호출
                visited[k] = false;  // dfs 호출 후 다시 방문 처리 해제 (백트래킹)
            }
        }
    }
}
