package Baekjoon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class baekjoon11286 {
    public static void main(String[] args) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int absA = Math.abs(a);
                int absB = Math.abs(b);
                if (absA == absB) {
                    return a - b; // 절대값 같으면 실제 값이 작은 것 우선
                }
                return absA - absB; // 절대값 작은 것 우선
            }
        });

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=0; i<n; i++){
            int a = sc.nextInt();
            if(a!=0){
                pQ.offer(a);
            }else{
                if(!pQ.isEmpty()){
                    System.out.println(pQ.poll());
                }else{
                    System.out.println(0);
                }
            }
        }

    }
}
