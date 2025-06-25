package Programmers;

public class programmers1 {
    class Solution {
        public int solution(int[][] sizes) {
            int answer = 0;

            // 지갑의 가로 길이와 세로 길이의 최댓값을 저장할 변수
            int maxw = 0;
            int maxh = 0;

            // 모든 명함에 대해 반복
            for (int i = 0; i < sizes.length; i++) {
                int w = sizes[i][0]; // 명함의 가로 길이
                int h = sizes[i][1]; // 명함의 세로 길이

                // 명함을 회전해서 항상 가로가 더 크거나 같게 만들기
                if (w < h) {
                    sizes[i][0] = h; // 가로를 큰 값으로
                    sizes[i][1] = w; // 세로를 작은 값으로
                }

                // 현재까지의 최대 가로, 최대 세로값 갱신
                maxw = Math.max(maxw, sizes[i][0]); // 회전 후 가로 길이
                maxh = Math.max(maxh, sizes[i][1]); // 회전 후 세로 길이
            }

            // 지갑 크기 = 최대 가로 * 최대 세로
            answer = maxw * maxh;

            return answer;
        }
    }

}
