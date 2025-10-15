package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon1062 {
    // N: 단어 개수, K: 가르칠 수 있는 글자 수, max: 최대 읽을 수 있는 단어 수
    static int N, K, max = 0;

    // visited[i]: 알파벳 i (0 = a, 25 = z)를 가르쳤는지 여부
    static boolean[] visited = new boolean[26];

    // 전처리된 단어 리스트 ("anta" + (중간) + "tica" → (중간)만 저장)
    static List<String> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 줄 입력 받기: N(단어 수), K(가르칠 수 있는 알파벳 수)
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 단어 입력 및 "anta", "tica" 제거 후 리스트에 저장
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            // 모든 단어는 "anta"로 시작하고 "tica"로 끝나므로 중간 부분만 추출
            word = word.substring(4, word.length() - 4);
            words.add(word);
        }

        // 필수 알파벳 5개는 항상 가르쳐야 함
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        // 가르칠 수 있는 글자가 5개보다 적으면, 필수 글자도 못 배우므로 단어를 읽을 수 없음
        if (K < 5) {
            System.out.println(0);
        } else {
            // 5개는 고정이므로 나머지 K - 5개를 조합으로 선택
            dfs(0, 0);
            System.out.println(max);
        }
    }

    /**
     * DFS를 이용하여 가르칠 수 있는 알파벳 조합을 선택
     * @param index: 알파벳 인덱스 (a=0 ~ z=25), 현재 어떤 알파벳부터 볼지
     * @param depth: 지금까지 선택한 알파벳 개수 (필수 5개 제외한 개수)
     */
    static void dfs(int index, int depth) {
        // K-5개의 알파벳을 선택했다면, 현재 조합으로 몇 개 단어를 읽을 수 있는지 확인
        if (depth == K - 5) {
            max = Math.max(max, countReadableWords());
            return;
        }

        // 알파벳 a(0)부터 z(25)까지 돌면서 아직 선택하지 않은 알파벳을 선택
        for (int i = index; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;             // 알파벳 i 선택
                dfs(i + 1, depth + 1);         // 다음 알파벳으로 재귀 호출
                visited[i] = false;            // 백트래킹 (선택 취소)
            }
        }
    }

    /**
     * 현재 가르친 알파벳 조합으로 몇 개의 단어를 읽을 수 있는지 계산
     * @return 읽을 수 있는 단어 수
     */
    static int countReadableWords() {
        int count = 0;

        for (String word : words) {
            boolean readable = true;

            // 단어의 각 글자를 확인해서 가르친 글자인지 체크
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!visited[c - 'a']) {
                    // 가르치지 않은 글자가 있으면 이 단어는 못 읽음
                    readable = false;
                    break;
                }
            }

            // 모든 글자가 가르친 글자라면, 읽을 수 있는 단어로 카운트
            if (readable) count++;
        }

        return count;
    }
}
