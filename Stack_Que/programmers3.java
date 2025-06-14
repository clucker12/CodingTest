package Stack_Que;

public class programmers3 {
    boolean solution(String s) {
        boolean answer = true;

        // 여는 괄호와 닫는 괄호 개수를 셀 변수
        int cnt1 = 0; // 여는 괄호 '(' 개수
        int cnt2 = 0; // 닫는 괄호 ')' 개수

        // 문자열을 한 글자씩 나눠 배열로 저장
        String[] a = s.split("");

        // 배열을 순회하며 괄호 개수를 셈
        for (String i : a) {
            if (i.equals("(")) {
                cnt1++; // 여는 괄호면 cnt1 증가
            } else {
                cnt2++; // 닫는 괄호면 cnt2 증가
            }

            // 닫는 괄호가 여는 괄호보다 많아지면 올바르지 않음
            if (cnt1 < cnt2) {
                answer = false;
                break; // 더 볼 필요 없이 종료
            }
        }

        // 마지막까지 돌았는데 괄호 개수가 같지 않으면 false
        if (cnt1 != cnt2) {
            answer = false;
        }

        return answer;
    }
}
