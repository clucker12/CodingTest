package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class baekjoon1038 {
    static int N; // 입력으로 주어지는 N번째 감소하는 수
    static List<Long> list = new ArrayList<>(); // 감소하는 수를 저장할 리스트

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // N 입력 받기

        // 0부터 9까지 한 자리 수를 시작으로 DFS 호출
        for (int i = 0; i <= 9; i++) {
            dfs(i, 1); // 현재 숫자 i를 시작으로 DFS 탐색 시작
        }

        // 감소하는 수를 모두 수집한 후 오름차순 정렬
        Collections.sort(list);

        // N번째 감소하는 수가 존재하지 않으면 -1 출력
        if (N >= list.size()) {
            System.out.println(-1);
        } else {
            // 존재한다면 N번째 감소하는 수 출력
            System.out.println(list.get(N));
        }
    }

    /**
     * 감소하는 수를 재귀적으로 생성하는 DFS 함수
     * @param current 현재까지 만들어진 숫자
     * @param length  현재 자릿수 (사용되지는 않지만 일반적으로 깊이 정보로 포함 가능)
     */
    static void dfs(long current, int length) {
        // 현재 숫자를 리스트에 추가
        list.add(current);

        // current의 마지막 자리 숫자보다 작은 숫자만 다음 자리에 붙일 수 있음
        // (엄격한 감소 조건이므로 같은 숫자나 큰 숫자는 안 됨)
        for (int i = 0; i < current % 10; i++) {
            // 다음 자리 숫자를 붙이고 재귀 호출
            // 예: current = 3 → 32, 31, 30 시도 (i = 2, 1, 0)
            dfs(current * 10 + i, length + 1);
        }
    }
}
