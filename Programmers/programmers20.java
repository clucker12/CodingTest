package Programmers;

public class programmers20 {

    // 모든 호출에서 누적해서 사용할 전역 변수
    static int cnt;    // 제거된 0의 총 개수
    static int depth;  // 이진 변환 횟수

    public int[] solution(String s) {
        // 초기화
        cnt = 0;
        depth = 0;

        // 재귀 함수 호출
        dfs(s);

        // depth(변환 횟수), cnt(제거된 0의 총 합) 반환
        return new int[] { depth, cnt };
    }

    public void dfs(String s) {
        // 종료 조건 : 문자열이 "1"이면 더 이상 변환할 필요 없음
        if (s.equals("1")) {
            return;
        }

        // 이진 변환을 한 번 수행하므로 횟수 증가
        depth++;

        // 문자열에서 0 제거하고 1만 모을 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 이번 변환에서 제거된 0 개수를 세기 위한 변수
        int count = 0;

        // 문자열을 순회하면서 0 제거 작업 수행
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                // 1이면 sb에 추가
                sb.append(s.charAt(i));
            } else {
                // 0이면 제거 ⇒ 카운트 증가만 함
                count++;
            }
        }

        // 전체 제거된 0 개수 누적
        cnt += count;

        // 제거 후 남은 1의 개수 → 문자열 길이
        int len = sb.length();

        // 길이를 2진법 문자열로 변환
        String next = Integer.toBinaryString(len);

        // 다음 단계 이진 변환 수행
        dfs(next);
    }
}
