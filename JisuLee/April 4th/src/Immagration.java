import java.util.*;

/*
    2022.04.22
    프로그래머스 이분탐색 입국심사
    https://programmers.co.kr/learn/courses/30/lessons/43238

    이분탐색시, mid를 포괄할지를 왼쪽, 오른쪽 각각 다 고려할 것.
    그리고 mid를 포함하는게 하나라도 있다면 왼, 오른쪽은 엇갈릴 수 없음 주의
 */

public class Immagration {


    public static void main(String[] args) {
        int n = 10;
        int[] times = {6, 8, 10};

        System.out.println(solution(n, times));
    }

    public static long solution(int n, int[] times) {

        //mid를 총 시간으로 산정
        //각 심사시간을 나눠 총 인원을 구함
        //mid의 최소시간을 구함
        long left = 1, right = (long) 1e14;

        while(left != right) {
            long mid = (left+right)/2;
            long total = 0;

            for(int i = 0; i < times.length; i++) {
                total += mid/(long)times[i];
            }

            if(total >= n) right = mid;
            else left = mid + 1;

        }

        return right;
    }
}
