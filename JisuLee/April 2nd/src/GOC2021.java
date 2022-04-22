import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    22.04.06
    GOCC 2021 bfs/dfs 문제
    https://codeforces.com/blog/entry/93178

    미완료
 */

public class GOC2021 {

    static boolean[][] dp = new boolean[26][26];

    public static void main(String args[] ) throws Exception {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i< T; i++){
            int length = Integer.parseInt(br.readLine());
            int p = Integer.parseInt(br.readLine());
            ArrayList<char[]> pairs = new ArrayList<>();

            for(int j = 0; j < p; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char a = (st.nextToken()).charAt(0);
                char b = (st.nextToken()).charAt(0);

                char[] one = {a, b};
                char[] two = {b, a};
                pairs.add(one);
                pairs.add(two);
            }
        }
    }

    static void makeDP(ArrayList<char[]> pairs) {

        for(char[] pair: pairs) {
            char start = pair[0];

        }
    }

    static void solution(int length) {

    }
}