package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon2529 {
    static int N;
    static char[] sign;
    static boolean[] visited = new boolean[10];
    static List<String> result = new ArrayList<>();

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sign = new char[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sign[i] = st.nextToken().charAt(0);
        }
        dfs(0,"");
        Collections.sort(result);
        System.out.println(result.get(result.size()-1));
        System.out.println(result.get(0));

    }

    static void dfs(int depth,String num){
        if(depth == N+1){
            result.add(num);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if(!visited[i]){
                if(depth == 0 || check(num.charAt(depth-1) - '0',i,sign[depth-1])){
                    visited[i] = true;
                    dfs(depth +1,num+i);
                    visited[i] = false;
                }
            }
        }

    }

    static boolean check(int a,int b, char op){
        if(op == '<'){
            return a<b;
        }
        if (op == '>') return a>b;
        return false;
    }
}
