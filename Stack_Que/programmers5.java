package Stack_Que;
import java.util.*;

public class programmers5 {
    class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;           // 시간(초)를 측정하는 변수
            int totalWeight = 0;      // 현재 다리 위에 있는 트럭들의 무게 합

            // 다리 위에 있는 트럭(혹은 빈칸)을 나타내는 큐
            Queue<Integer> bridge = new LinkedList<>();

            // 다리 위를 트럭이 지나갈 수 있도록, 처음에는 모두 빈 칸(0)으로 채워둠
            for (int i = 0; i < bridge_length; i++) {
                bridge.add(0);
            }

            // 대기 중인 트럭을 하나씩 처리
            for (int i : truck_weights) {
                while (true) {
                    answer++;               // 1초 경과

                    totalWeight -= bridge.poll(); // 다리 맨 앞의 트럭(또는 0)을 내보내고, 무게에서 빼줌

                    // 새 트럭을 다리에 올릴 수 있다면
                    if (totalWeight + i <= weight) {
                        bridge.add(i);     // 트럭을 다리에 올림
                        totalWeight += i;  // 총 무게 갱신
                        break;             // 다음 트럭으로 이동
                    } else {
                        bridge.add(0);     // 무게 초과로 트럭을 못 올리면 빈 칸(0) 추가
                    }
                }
            }

            // 마지막 트럭이 다리를 건너는 데 필요한 시간 추가
            return (answer + bridge_length);
        }
    }
}
