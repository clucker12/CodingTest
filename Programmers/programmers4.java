package Programmers;

public class programmers4 {
    class Solution {
        public int[] solution(int brown, int yellow) {
            // 카펫의 전체 타일 수는 갈색 타일과 노란색 타일의 합
            int total = brown + yellow;

            // height는 최소 3부터 시작 (테두리를 만들기 위해 최소한의 높이 필요)
            // total / 3까지 반복 -> width >= height 이라는 조건에서 충분
            for (int height = 3; height <= total / 3; height++) {
                // 전체 타일 수가 해당 높이로 나누어떨어지지 않으면 해당 높이는 불가능
                if (total % height != 0) continue;

                // 가능한 너비 계산 (전체 타일 수 / 현재 높이)
                int width = total / height;

                // 내부의 노란색 타일 개수 확인
                // 갈색 테두리를 제외한 내부 면적이 노란색 타일 수와 같아야 함
                if ((width - 2) * (height - 2) == yellow) {
                    // 조건이 맞으면 [너비, 높이] 형태로 반환
                    return new int[]{width, height};
                }
            }

            // 조건을 만족하는 경우가 없으면 null 반환 (문제 조건상 이런 경우는 없음)
            return null;
        }
    }
}
