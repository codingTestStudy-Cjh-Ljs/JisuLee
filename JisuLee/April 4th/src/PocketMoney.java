import java.util.*;
import java.io.*;

/*
    2022.04.20
    백준 6236번 용돈관리
    https://www.acmicpc.net/problem/6236

    전형적인 이진 탐색 유형

    문제가 대체 무슨 말인지 모르겠어서 답을 먼저 봄.
    너무 어렵게만 접근하려하지 말고 생각의 전환이 필요
    디버깅은 적게, 테스트케이스 만드는 연습하기!!
 */

public class PocketMoney {

    static int n, m;
    static int[] days;
    static int left;
    static int right = (int) 1e9;

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st1.nextToken());
        m = Integer.parseInt(st1.nextToken());

        days = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++){
            int price = Integer.parseInt(br.readLine());
            days[i] = price;

            if(max < price) max = price;
        }

        left = max;

        int mid = (right - left) / 2;

        //이진 탐색
        while(left <= right) {
            int count = countWithdraw(mid);
            if(count == -1) { right = mid - 1; }
            else if(count == 1) { left = mid + 1; }
            mid = (left + right) / 2;
        }

        System.out.println(mid + 1);

    }

    static int countWithdraw(int mid) {
        int count = 0;
        int sum = 0;
        for(int i = 0; i < days.length; i++) {
            if(sum + days[i] > mid) {
                count++;
                sum = days[i];
            } else {
                sum += days[i];
            }

            if(count > m) return 1;
        }

        if (count + 1 > m){
            return 1;
        } else {
            return -1;
        }
    }
}
