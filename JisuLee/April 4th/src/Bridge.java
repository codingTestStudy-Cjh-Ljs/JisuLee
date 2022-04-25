import java.lang.reflect.Array;
import java.util.*;

/*
    2022.04.24
    프로그래머스 이분탐색 징검다리
    https://programmers.co.kr/learn/courses/30/lessons/43236

    단순한 비교보다 덧셈뺄셈이 생각보다 훨씬 시간이 더 오래걸림.
    접근은 맞았는데 아직도 분기를 나누는데 어려움이 많음.
    이 부분 확실하게 이해하고 넘어갈 것.
 */

public class Bridge {
    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;

        System.out.println(solution(distance, rocks, n));
    }

    public static int solution(int distance, int[] rocks, int n) {

        //거리 정렬
        Arrays.sort(rocks);

        //최소거리 이분탐색
        int left = 1, right = distance;
        while(right - left > 1){
            int mid = (left + right)/2;

            //앞 돌과 비교해서 설정한 거리보다 거리가 작은 돌은 제거.
            int front = 0, count = 0;
            for(int i = 0; i < rocks.length; i++) {
                if(mid > rocks[i] - front) {
                    count++;
                }
                else {
                    front = rocks[i];
                }
            }

            //돌 개수 세기 - 제거된 돌 수가 작거나 같으면 left를, 크면 right를 옮기기
            if(count > n) right = mid;
            else left = mid;
        }

        return (left + right) / 2;

    }
}

