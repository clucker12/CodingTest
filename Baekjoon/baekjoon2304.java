package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon2304 {
    static int N;           // 기둥 개수
    static int[][] arr;     // 각 기둥의 (x좌표, 높이)

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // ✅ 1. 입력받기
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // x좌표
            arr[i][1] = Integer.parseInt(st.nextToken()); // 높이
        }

        // ✅ 2. x좌표 기준 오름차순 정렬
        //    (기둥이 랜덤 순서로 들어오므로, 왼쪽→오른쪽 순서로 정렬 필요)
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        // ✅ 3. 가장 높은 기둥(지붕의 최고점) 찾기
        int maxH = 0;       // 최고 높이
        int maxIdx = 0;     // 최고 높이를 가진 기둥의 인덱스
        for (int i = 0; i < N; i++) {
            if(arr[i][1] > maxH){
                maxH = arr[i][1];
                maxIdx = i;
            }
        }

        int area = 0; // 전체 면적 누적 변수

        // ✅ 4. 왼쪽 → 최고 기둥까지 면적 계산
        //   왼쪽에서 오른쪽으로 진행하면서,
        //   현재까지의 '가장 높은 높이'를 유지하며 면적을 더함.
        int leftMax = arr[0][1];  // 현재까지 본 왼쪽 최고 높이
        int leftX = arr[0][0];    // 최고 높이의 x좌표
        for (int i = 1; i <= maxIdx; i++) {
            // 만약 현재 기둥이 지금까지의 최고 높이 이상이라면
            if (arr[i][1] >= leftMax) {
                // 직사각형 면적 = (x거리) × (현재 최고 높이)
                area += (arr[i][0] - leftX) * leftMax;

                // 최고 높이 및 기준 x 갱신
                leftMax = arr[i][1];
                leftX = arr[i][0];
            }
        }

        // ✅ 5. 오른쪽 → 최고 기둥까지 면적 계산
        //   오른쪽 끝에서 왼쪽으로 진행하면서,
        //   현재까지의 '가장 높은 높이'를 유지하며 면적을 더함.
        int rightMax = arr[N - 1][1]; // 현재까지 본 오른쪽 최고 높이
        int rightX = arr[N - 1][0];   // 최고 높이의 x좌표
        for (int i = N - 2; i >= maxIdx; i--) {
            // 현재 기둥이 지금까지의 최고 높이 이상이면
            if (arr[i][1] >= rightMax) {
                // 직사각형 면적 = (x거리) × (현재 최고 높이)
                area += (rightX - arr[i][0]) * rightMax;

                // 최고 높이 및 기준 x 갱신
                rightMax = arr[i][1];
                rightX = arr[i][0];
            }
        }

        // ✅ 6. 가장 높은 기둥(최고점) 한 칸 면적 추가
        //    왼쪽/오른쪽 스캔에서는 최고 기둥 자체는 계산되지 않았기 때문
        area += maxH;

        // ✅ 7. 결과 출력
        System.out.println(area);
    }
}
