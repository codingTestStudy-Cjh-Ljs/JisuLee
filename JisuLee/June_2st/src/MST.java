import java.util.*;
import java.io.*;

/*
    백준 1197 최소 스패닝 트리

    최소신장트리를 복습하는 차원에서 풀어봄.
 */

public class MST {
    static int[] head;
    static PriorityQueue<Vertex> heap = new PriorityQueue<>(new Comparator<Vertex>() {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            if(o1.price >= o2.price) return 1;
            else return -1;
        }
    });

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        head = new int[v];
        for(int i = 0; i < v; i++) {
            head[i] = i+1;
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            heap.add(new Vertex(first, second, price));
        }

        int answer = kruskal();
        System.out.println(answer);
    }

    public static int kruskal(){
        int count = 0;

        while(!heap.isEmpty()){
            Vertex v = heap.poll();
            int first = v.nodes[0];
            int second = v.nodes[1];

            if(find(first) != find(second)) {
                count += v.price;
                union(first, second);
            }
        }
        return count;
    }

    public static void union(int first, int second) {
        int fHead = find(first);
        int sHead = find(second);

        head[sHead-1] = fHead;
    }

    public static int find(int num) {
        if(head[num-1] == num) return num;

        return find(head[num-1]);
    }

    public static int prim(int v, Node[] nodes){

        PriorityQueue<Vertex> heap = new PriorityQueue<>(new Comparator<Vertex>(){

            @Override
            public int compare(Vertex o1, Vertex o2) {
                if(o1.price > o2.price) {
                    return 1;
                } else if(o1.price < o2.price) {
                    return -1;
                } else return 0;
            }
        });
        boolean[] visited = new boolean[v];
        int count = 0;

        int now = 1;
        visited[0] = true;
        heap.add(new Vertex(1, 1, 1000001));    //더미 데이터
        while(!heap.isEmpty()){
            List<Vertex> vertexes = nodes[now-1].vertexes;
            for(Vertex vertex: vertexes){
                heap.add(vertex);
            }

            while(!heap.isEmpty()) {
                Vertex vx = heap.poll();
                int[] nds = vx.nodes;
                if(!visited[nds[0]-1]){
                    count += vx.price;
                    now = nds[0];
                    visited[now-1] = true;
                    break;
                } else if(!visited[nds[1]-1]){
                    count += vx.price;
                    now = nds[1];
                    visited[now-1] = true;
                    break;
                }
            }
        }
        return count;
    }
}

class Node{
    int num;
    List<Vertex> vertexes = new LinkedList<>();

    public Node(int num) {
        this.num = num;
    }

    public void add(Vertex v) {
        vertexes.add(v);
    }
}

class Vertex {
    int[] nodes = new int[2];
    int price;

    public Vertex(int first, int second, int price) {
        nodes[0] = first;
        nodes[1] = second;
        this.price = price;
    }
}