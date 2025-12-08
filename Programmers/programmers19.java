package Programmers;

public class programmers19 {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isStart = true;  // 단어의 첫 글자인지 여부

        for (char c : s.toCharArray()) {

            if (c == ' ') { // 공백이면
                sb.append(c);
                isStart = true; // 다음 문자는 단어의 첫 글자
            } else {
                if (isStart) { // 단어 첫 글자
                    sb.append(Character.toUpperCase(c));
                    isStart = false;
                } else { // 단어 첫 글자 아닐 때
                    sb.append(Character.toLowerCase(c));
                }
            }
        }

        return sb.toString();
    }
}
