package Stack_Que;
import java.util.*;

public class programmers4 {
    class Solution {
        public int solution(int[] priorities, int location) {
            int answer=0;
            Queue<int[]> queue = new LinkedList<>();

            // 큐에 (인덱스, 우선순위)를 저장
            for (int i = 0; i < priorities.length; i++) {
                queue.offer(new int[] {i, priorities[i]});
            }

            int count = 0; // 실행 순서를 카운트

            while (!queue.isEmpty()) {
                int[] current = queue.poll(); // 현재 프로세스 꺼냄
                boolean hasHigherPriority = false;

                // 큐에 현재보다 높은 우선순위가 있는지 확인
                for (int[] q : queue) {
                    if (q[1] > current[1]) {
                        hasHigherPriority = true;
                        break;
                    }
                }

                if (hasHigherPriority) {
                    // 더 높은 우선순위가 있다면 다시 뒤로 보냄
                    queue.offer(current);
                } else {
                    // 실행
                    count++;
                    if (current[0] == location) {
                        return count; // 실행된 프로세스가 찾던 프로세스면 종료
                    }
                }
            }

            answer=count;

            return answer;
        }
    }
}
