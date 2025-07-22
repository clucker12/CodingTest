// 패키지 선언: 이 파일이 속한 패키지 이름
package Baekjoon;

// 자바에서 자주 쓰이는 클래스들을 가져옴 (특히 Scanner, PriorityQueue, Collections 등)
import java.util.*;

// Main 클래스 선언 (자바 프로그램의 시작점)
class Main {
    // main 메서드: 자바 프로그램이 시작되는 지점
    public static void main(String[] args) {
        // 우선순위 큐(PriorityQueue) 생성, 내림차순 정렬 (최대 힙처럼 사용)
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());

        // 사용자로부터 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 첫 번째 입력: 수행할 연산의 개수 n
        int n = sc.nextInt();

        // n번 반복해서 입력을 받음
        for(int i = 0; i < n; i++){
            // 정수 a 입력 받음
            int a = sc.nextInt();

            // a가 0이 아닌 경우 → 우선순위 큐에 a를 삽입 (offer = add)
            if(a != 0){
                pQ.offer(a);
            } else {
                // a가 0인 경우 → 큐가 비어 있지 않으면 최대값 출력 및 제거
                if(!pQ.isEmpty()){
                    System.out.println(pQ.poll()); // poll()은 가장 큰 값을 반환하고 제거함
                } else {
                    // 큐가 비어 있으면 0 출력
                    System.out.println(0);
                }
            }
        }
    }
}
