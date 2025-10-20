package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon17471 {
    static int N;                     // 구역 개수
    static int[] population;          // 각 구역 인구수 저장 배열
    static List<Integer>[] adjList;   // 인접 리스트 (각 구역과 연결된 구역 번호들)
    static int minDiff = Integer.MAX_VALUE; // 인구 차이 최소값 초기화

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 구역 개수 입력
        N = Integer.parseInt(br.readLine());

        // 인구수 배열 크기 설정 (1번 구역부터 시작하므로 N+1)
        population = new int[N+1];

        // 인접 리스트 배열 생성
        @SuppressWarnings("unchecked")
        List<Integer>[] tempList = (List<Integer>[]) new ArrayList[N + 1];
        adjList = tempList;

        // 2. 각 구역의 인구 입력
        st= new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            adjList[i] = new ArrayList<>(); // 인접 리스트 초기화
        }

        // 3. 각 구역별 인접한 구역 정보 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());  // 인접 구역 개수
            for (int j = 0; j < cnt; j++) {
                int neighbor = Integer.parseInt(st.nextToken()); // 인접 구역 번호
                adjList[i].add(neighbor);                         // 인접 리스트에 추가
            }
        }

        // 4. 부분 집합을 이용하여 두 선거구로 나누기 (1부터 (2^N)-2까지)
        // (모든 구역을 포함하는 집합과 아무것도 포함하지 않는 집합 제외)
        for (int i = 1; i < (1<<N) - 1; i++) {
            List<Integer> groupA = new ArrayList<>(); // 첫 번째 선거구 구역 리스트
            List<Integer> groupB = new ArrayList<>(); // 두 번째 선거구 구역 리스트

            // 각 비트별로 포함 여부 확인
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    groupA.add(j + 1); // 비트가 1이면 groupA에 포함 (구역 번호는 j+1)
                } else {
                    groupB.add(j + 1); // 비트가 0이면 groupB에 포함
                }
            }

            // 5. 두 그룹 모두 연결되어 있으면 인구 차이 계산 및 최소값 갱신
            if(isConnected(groupA) && isConnected(groupB)){
                int diff = Math.abs(getPopulation(groupA) - getPopulation(groupB));
                minDiff = Math.min(minDiff, diff);
            }
        }

        // 6. 결과 출력: 가능한 분할 없으면 -1, 있으면 최소 인구 차이 출력
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    // 특정 그룹이 그래프상에서 연결되어 있는지 체크하는 함수
    static boolean isConnected(List<Integer> group) {
        if(group.isEmpty()) return false; // 그룹이 비어있으면 연결 불가

        boolean[] visited = new boolean[N+1];   // 방문 체크 배열
        Queue<Integer> queue = new LinkedList<>();

        // 그룹의 첫 구역을 시작점으로 BFS 수행
        queue.offer(group.get(0));
        visited[group.get(0)] = true;

        int cnt = 1; // BFS로 방문한 구역 개수

        while (!queue.isEmpty()){
            int current = queue.poll();

            // current 구역과 인접한 구역 탐색
            for (int neighbor : adjList[current]){
                // neighbor가 group에 포함되고 방문하지 않았다면
                if(group.contains(neighbor) && !visited[neighbor]){
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    cnt++; // 방문 구역 수 증가
                }
            }
        }

        // BFS 방문한 구역 수가 group 크기와 같으면 연결된 그룹임
        return cnt == group.size();
    }

    // 그룹에 속한 구역들의 인구 합을 구하는 함수
    static int getPopulation(List<Integer> group){
        int sum = 0;
        for (int i : group){
            sum += population[i];
        }
        return sum;
    }
}
