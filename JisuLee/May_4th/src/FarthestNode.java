import java.util.*;

/*
    프로그래머스 그래프 - 가장 먼 노드
    https://programmers.co.kr/learn/courses/30/lessons/49189

    다익스트라 알고리즘 연습 문제
 */

public class FarthestNode {

    static Node[] nodes;
    static Integer[] dp;

    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(solution(n, vertex));
    }

    public static int solution(int n, int[][] edge) {

        nodes = new Node[n];
        dp = new Integer[n];
        for(int i = 0; i < n; i++){
            nodes[i] = new Node(i+1);
        }
        Arrays.fill(dp, Integer.MAX_VALUE);

        //그래프 생성
        for(int[] node: edge) {
            nodes[node[0]-1].connect(nodes[node[1]-1]);
            nodes[node[1]-1].connect(nodes[node[0]-1]);
        }

        //다익스트라 알고리즘
        dijkstra();

        //dp정렬
        Arrays.sort(dp, Collections.reverseOrder());

        int max = dp[0];
        int count = 0;
        for(int i = 0; i < dp.length;i++){
            if(dp[i] < max) break;
            count++;
        }

        return count;
    }

    public static void dijkstra() {
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int[] start = {1, 0};
        dp[0] = 0;
        heap.add(start);
        while(!heap.isEmpty()){
            int[] now = heap.poll();
            int now_idx = now[0]-1;

            //큐에 있는 동안 해당 노드가 갱신되었다면 넘어감
            if(dp[now_idx] != now[1]) continue;

            Set<Node> nexts = nodes[now_idx].connected;
            for(Node next : nexts) {
                int idx = next.num-1;

                if(dp[idx] > dp[now_idx] + 1){
                    dp[idx] = dp[now_idx] + 1;
                    int[] tmp = {idx+1, dp[idx]};
                    heap.add(tmp);
                }
            }
        }
    }
}


class Node {
    int num;
    Set<Node> connected = new HashSet<>();

    public Node(int num) {
        this.num = num;
    }

    public void connect(Node node) {
        connected.add(node);
    }
}
