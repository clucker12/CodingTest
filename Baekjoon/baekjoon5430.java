package Baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class baekjoon5430 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // 입력을 받기 위한 Scanner 생성

        int T = sc.nextInt();  // 테스트 케이스 개수 입력
        for(int i=0; i<T; i++){
            String p = sc.next();  // 수행할 함수 문자열 입력 (R, D 조합)
            int n = sc.nextInt();  // 배열 크기 입력
            String arr = sc.next(); // 배열 문자열 입력 (예: [1,2,3])

            Deque<Integer> deque = new ArrayDeque<>(); // 배열 데이터를 저장할 덱(양방향 큐) 생성
            boolean isError = false;  // 에러 발생 여부 표시용 변수
            boolean isReversed = false; // 뒤집힘 상태 표시용 변수

            if(n > 0){  // 배열이 비어있지 않으면
                // 배열 문자열에서 맨 앞 '[' 와 맨 뒤 ']' 제거하고 ',' 기준으로 분리
                String[] list = arr.substring(1, arr.length()-1).split(",");
                for(String s : list){
                    deque.add(Integer.parseInt(s)); // 각 숫자를 정수로 변환 후 덱에 추가
                }
            }

            // 함수 문자열을 한 문자씩 반복
            for(char a : p.toCharArray()){
                if(a == 'R'){
                    isReversed = !isReversed;  // R이 나오면 뒤집기 상태 토글 (true <-> false)
                } else if (a == 'D') {
                    // D가 나오면 배열에서 원소 제거
                    if(deque.isEmpty()){  // 제거할 원소가 없으면 에러 발생
                        isError = true;
                        break;  // 더 이상 함수 수행하지 않고 빠져나감
                    }
                    if(isReversed){
                        deque.removeLast();  // 뒤집힌 상태면 뒤쪽 원소 제거
                    } else {
                        deque.removeFirst(); // 아니면 앞쪽 원소 제거
                    }
                }
            }

            // 에러 발생 시
            if(isError){
                System.out.println("error");
            } else {
                // 에러가 없으면 결과 배열 출력
                StringBuilder sb = new StringBuilder();
                sb.append("[");  // 배열 출력 시작 대괄호 추가
                while (!deque.isEmpty()){
                    // 뒤집힌 상태에 따라 앞 또는 뒤에서 원소를 하나씩 꺼내 출력
                    if(isReversed){
                        sb.append(deque.removeLast());
                    } else {
                        sb.append(deque.removeFirst());
                    }
                    // 원소가 남아있으면 쉼표 추가
                    if(!deque.isEmpty()){
                        sb.append(",");
                    }
                }
                sb.append("]");  // 배열 출력 끝 대괄호 추가
                System.out.println(sb.toString());  // 완성된 문자열 한 번에 출력
            }
        }
    }
}
