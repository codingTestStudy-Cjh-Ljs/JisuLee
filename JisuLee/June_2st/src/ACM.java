import java.util.*;
import java.io.*;
/*
    백준 1005 ACM Craft

    위상정렬, dp를 쓰는 문제
    알고리즘 유형을 둘 다 캐치하지 못했음.
    더 공부할 것.
 */

public class ACM {
    static int n, k, dst;
    static Node[] nodes = null;
    static int[] vertexes = null;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            nodes = new Node[n+1];
            vertexes = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n+1; j++) {
                nodes[j] = new Node(j, Integer.parseInt(st.nextToken()));
            }

            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                Node from = nodes[Integer.parseInt(st.nextToken())];
                Node to = nodes[Integer.parseInt(st.nextToken())];
                from.add(to);
                vertexes[to.num] += 1;
            }

            dst = Integer.parseInt(br.readLine());
            long answer = solution();
            System.out.println(answer);
        }
    }

    public static long solution() {
        Queue<Node> queue = new LinkedList<>();
        long[] dp = new long[n+1];
        long count = 0;

        //정점이 0인 노드 삽입
        for(int i = 1; i < n+1; i++) {
            if(vertexes[i] == 0) {
                queue.add(nodes[i]);
            }
        }

        while(!queue.isEmpty()){
            LinkedList<Node> next = new LinkedList<>();

            while(!queue.isEmpty()) {
                Node now = queue.poll();

                LinkedList<Node> list = now.next;

                //다음턴 가져오기
                for(Node node: list) {
                    vertexes[node.num] -= 1;
                    long next_dp = dp[now.num] + now.time;
                    if(next_dp > dp[node.num]) dp[node.num] = next_dp;
                    if(vertexes[node.num] == 0) {
                        queue.add(node);
                    }
                }
            }
        }

        return dp[dst] + nodes[dst].time;
    }
}

class Node {
    int num;
    int time;
    LinkedList<Node> next = new LinkedList<>();

    public Node(int num, int time) {
        this.num = num;
        this.time = time;
    }

    public void add(Node node) {
        next.add(node);
    }
}
