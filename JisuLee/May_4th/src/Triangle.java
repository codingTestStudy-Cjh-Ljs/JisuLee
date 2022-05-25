import java.util.*;

/*
    프로그래머스 동적프로그래밍 - 정수 삼각형
    https://programmers.co.kr/learn/courses/30/lessons/43105

    너무너무 유명한 문제.
    저번에 특강에서 풀었던 기억이 있는데 이번에는 혼자서 잘 풀었음
 */

public class Triangle {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int answer = count(triangle);

        return answer;
    }

    public static int count(int[][] tri) {
        int[][] dp = new int[tri.length][tri.length+1];
        dp[0][1] = tri[0][0];

        for(int i = 1; i < tri.length; i++){
            for(int j = 1; j < tri[i].length+1;j++) {
                int now = tri[i][j-1];
                dp[i][j] = Math.max(dp[i-1][j-1] + now, dp[i-1][j]+now);
            }
        }

        int answer = 0;
        for(int i = 1; i < tri.length; i++) {
            answer = Math.max(answer, dp[tri.length-1][i]);
        }

        return answer;
    }
}
