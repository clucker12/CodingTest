package PCCE;

public class PCCE5 {
    // 메서드: CPR 순서를 숫자로 변환
    public int[] solution(String[] cpr) {
        int[] answer = new int[cpr.length];
        String[] basic_order = {"check", "call", "pressure", "respiration", "repeat"};

        for (int i = 0; i < cpr.length; i++) {
            for (int j = 0; j < basic_order.length; j++) {
                if (cpr[i].equals(basic_order[j])) {
                    answer[i] = j + 1;
                    break;
                }
            }
        }
        return answer;
    }

    // main() 메서드: 테스트 실행
    public static void main(String[] args) {
        PCCE5 pcce = new PCCE5();

        String[] testInput = {"call", "respiration", "repeat", "check", "pressure"};
        int[] result = pcce.solution(testInput);

        System.out.print("결과: ");
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();  // 줄바꿈
    }
}
