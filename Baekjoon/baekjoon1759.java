package Baekjoon; // 패키지 이름 (문제에 따라 생략 가능)

// 필요한 라이브러리 임포트
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjoon1759 {
    // 암호의 길이 (선택할 문자 수)
    static int L;
    // 주어진 문자 수
    static int C;

    // 전체 문자 배열 (C개의 문자 저장)
    static char[] chars;
    // 선택된 문자(암호) 저장 배열 (길이 L)
    static char[] password;

    // 모음 저장 집합 (빠른 검사 위해 Set 사용)
    static Set<Character> mo = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 줄 입력: L과 C 읽기
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 선택할 문자 수
        C = Integer.parseInt(st.nextToken()); // 전체 주어진 문자 수

        // 두 번째 줄: C개의 문자 읽기
        chars = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            // 각 단어의 첫 글자(charAt(0))를 char로 저장
            chars[i] = st.nextToken().charAt(0);
        }

        // 문자 배열을 오름차순 정렬 → 사전순 조합 생성을 위해
        Arrays.sort(chars);

        // 암호를 저장할 배열 초기화
        password = new char[L];

        // 조합 생성 시작 (start index = 0, depth = 0)
        backtrack(0, 0);
    }

    // 백트래킹 함수
    static void backtrack(int start, int depth) {
        // 길이가 L인 조합이 완성된 경우
        if (depth == L) {
            // 모음 1개 이상, 자음 2개 이상인지 검사
            if (isValid(password)) {
                // 유효한 조합이면 출력
                System.out.println(new String(password));
            }
            return; // 조합 하나 마쳤으므로 백트래킹
        }

        // start부터 C까지 순차적으로 문자 선택
        for (int i = start; i < C; i++) {
            password[depth] = chars[i]; // 현재 위치에 문자 저장
            backtrack(i + 1, depth + 1); // 다음 문자 선택 (재귀)
        }
    }

    // 유효성 검사 함수: 모음 1개 이상, 자음 2개 이상인지 확인
    static boolean isValid(char[] pwd) {
        int mocnt = 0, jacnt = 0; // 모음/자음 개수

        for (char c : pwd) {
            if (mo.contains(c)) {
                mocnt++; // 모음 개수 증가
            } else {
                jacnt++; // 자음 개수 증가
            }
        }

        // 모음 1개 이상, 자음 2개 이상일 때 true
        return mocnt >= 1 && jacnt >= 2;
    }
}
