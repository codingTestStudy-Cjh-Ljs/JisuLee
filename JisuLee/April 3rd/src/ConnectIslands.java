import java.util.*;

/*
    프로그래머스 탐욕법 '섬 연결하기' 문제
    https://programmers.co.kr/learn/courses/30/lessons/42861

    최소신장트리(최소스패닝트리) 크루스칼 알고리즘 유형 문제였음.
    프림 알고리즘으로 접근하려 했지만 괜히 너무 어렵게 접근하는 감이 있어서
    크루스칼로 변경했더니 한방에 통과함.
 */

public class ConnectIslands {

    static int[] head;

    public static void main(String[] args) {
        int n = 7;
        int[][] costs = {{2,3,7},{3,6,13},{3,5,23},{5,6,25},{0,1,29},{1,5,34},{1,2,35},{4,5,53},{0,4,75}};
        System.out.println(solution(n, costs));
    }

    public static int solution(int n, int[][] costs) {
        int total = 0;
        head = new int[n];
        for(int i = 0; i < n; i++) {
            head[i] = i;
        }

        Arrays.sort(costs, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for(int[] cost: costs) {
            if(find(cost[0]) == find(cost[1])){
                continue;
            }

            union(cost[0], cost[1]);
            total += cost[2];
        }

        return total;
    }

    public static void union(int a, int b) {
        int a_head = find(a);
        int b_head = find(b);

        head[b_head] = a_head;
    }

    public static int find(int n) {
        if(head[n] == n) return n;
        else {
            return find(head[n]);
        }
    }
}