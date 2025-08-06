package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class baekjoon9375 {
    public static void main(String[] args)throws IOException {
        // 입력을 빠르게 처리하기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 수 t 입력
        int t = Integer.parseInt(br.readLine());

        // 각 테스트 케이스 처리
        for(int i = 0; i < t; i++){
            // 현재 테스트 케이스의 의상 개수 n 입력
            int n = Integer.parseInt(br.readLine());

            // 의상 종류별로 몇 개 있는지 저장할 HashMap 생성
            // key: 의상 종류, value: 해당 종류의 의상 개수
            HashMap<String, Integer> cloth = new HashMap<>();

            // n개의 의상 입력 처리
            for(int j = 0; j < n; j++){
                // 한 줄 입력을 공백으로 나누어 [0] = 의상 이름, [1] = 의상 종류
                String[] input = br.readLine().split(" ");
                // 해당 의상 종류의 개수를 1 증가, 없으면 0에서 시작
                cloth.put(input[1], cloth.getOrDefault(input[1], 0) + 1);
            }

            // 모든 조합 수를 계산하기 위한 변수, 1부터 시작
            int result = 1;
            // 각 의상 종류별 선택 가능한 경우 수 계산
            // (해당 종류를 안 입는 경우 1 포함)
            for(int cnt : cloth.values()){
                result *= (cnt + 1);
            }

            // 아무것도 입지 않는 경우(알몸) 제외
            System.out.println(result - 1);
        }
    }
}
