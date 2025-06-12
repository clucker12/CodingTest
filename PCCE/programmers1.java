package PCCE;

import java.util.ArrayList;
import java.util.List;

public class programmers1 {
    //스택/큐
    //같은 숫자는 싫어

    public int[] solution(int[] arr) {
        // 결과를 담을 리스트 선언
        List<Integer> list = new ArrayList<>();

        // 이전 숫자 기억용 변수 (처음엔 arr에 나올 수 없는 값으로 초기화)
        int prev = -1;

        // 배열 순회하면서 연속 중복 제거
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] != prev) {
                list.add(arr[i]);  // 중복이 아니면 리스트에 추가
                prev = arr[i];     // 현재 값을 prev로 업데이트
            }
        }

        // 리스트 크기만큼 배열 생성
        int[] answer = new int[list.size()];

        // 리스트 → 배열 복사 (기초 for문 사용)
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        programmers1 sol = new programmers1();

        // 테스트용 입력 배열
        int[] input1 = {1, 1, 3, 3, 0, 1, 1};
        int[] input2 = {4, 4, 4, 3, 3};

        // solution 메서드 실행
        int[] result1 = sol.solution(input1);
        int[] result2 = sol.solution(input2);

        // 결과 출력
        System.out.println("Input1: " + java.util.Arrays.toString(input1));
        System.out.println("Result1: " + java.util.Arrays.toString(result1));

        System.out.println("Input2: " + java.util.Arrays.toString(input2));
        System.out.println("Result2: " + java.util.Arrays.toString(result2));
    }
}
