import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    22.04.06
    GOC2020 - 1번 OR 문제

    DP 완전탐색 문제인듯
 */

public class OR {
    static int[] dp;

    public static void main(String args[] ) throws Exception {

        //Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = max|arr[i];
        }

        dp = new int[max];

    }
}
