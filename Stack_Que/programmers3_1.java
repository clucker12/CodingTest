package Stack_Que;

public class programmers3_1 {
    // 개선된 버젼

    // charAt
    //  **문자열(String)**에서
    //  0부터 시작하는 인덱스 위치에 있는 **단일 문자(char)**를 반환합니다.
    // length()는 문자열의 문자 개수를 반환
    boolean solution(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // 문자 하나 가져오기
            char c = s.charAt(i);

            if (c == '(') {
                count++;
            } else {
                count--;
            }

            // 닫는 괄호가 더 많아지면 false
            if (count < 0) return false;
        }

        // 여는 괄호, 닫는 괄호 수가 딱 맞으면 true
        return count == 0;
    }

}
