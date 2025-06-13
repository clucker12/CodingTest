package Stack_Que;

import java.util.*;


public class programmers2 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();
        int n = progresses.length;
        int[] days = new int[n];

        // 1. 각 작업별로 완료까지 걸리는 일수 계산
        //  Math.ceil(double x)
        // x보다 크거나 같은 가장 작은 정수값을 double 타입으로 반환합니다.
        // 즉, 소수점이 조금이라도 있으면 올림합니다.
        for (int i = 0; i < n; i++) {
            int remain = 100 - progresses[i];
            days[i] = (int) Math.ceil((double) remain / speeds[i]);
        }

        // 2. 앞 기능 완료일보다 늦게 끝나는 기능들까지 묶어서 배포
        int maxDay = days[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (days[i] <= maxDay) {
                count++;
            } else {
                result.add(count);
                count = 1;
                maxDay = days[i];
            }
        }
        result.add(count); // 마지막 배포

        answer = new int[result.size()];
        for(int num=0; num<result.size();num++){
            answer[num] = result.get(num);
        }

        return answer;
    }
}
