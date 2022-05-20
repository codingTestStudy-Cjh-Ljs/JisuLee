import java.util.*;
import java.io.*;

/*
    프로그래머스 dfs/bfs 문제 - 단어 변환
 */

class ChangeWords {

    static Node1[] node1s;
    static int count = 99;

    public int solution(String begin, String target, String[] words) {

        //그래프 만들기
        node1s = new Node1[words.length+1];
        node1s[0] = new Node1(begin, 0);
        for(int i = 1; i < words.length+1; i++) {
            node1s[i] = new Node1(words[i-1], i);
        }

        setGraph(begin, words);

        //dfs 실행
        boolean[] visited = new boolean[node1s.length+1];
        visited[0] = true;
        dfs(node1s[0], target, visited, 0);

        if(count == 99) { return 0; }

        return count;
    }

    public static void setGraph(String begin, String[] words) {

        for(int j = 0; j < words.length; j++) {
            int diff = 0;

            for(int k = 0; k < begin.length(); k++) {
                if(begin.charAt(k) != words[j].charAt(k)) { diff += 1; }
                if(diff > 1) break;
            }

            if(diff == 1) {
                node1s[0].add(node1s[j+1]);
            }
        }

        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words.length; j++) {
                if(i == j) continue;

                int diff = 0;

                for(int k = 0; k < words[i].length(); k++) {
                    if(words[i].charAt(k) != words[j].charAt(k)) { diff += 1; }
                    if(diff > 1) break;
                }

                if(diff == 1) {
                    node1s[i+1].add(node1s[j+1]);
                }
            }
        }
    }

    public static int dfs(Node1 now, String target, boolean[] visited, int length) {
        int total = 99;

        if(length > count) return 99;

        for(Node1 next : now.nextWords) {
            if(next.word.equals(target)){
                count = count > length+1 ? length+1 : count;
                return length+1;
            }

            if(visited[next.idx] == false) {
                visited[next.idx] = true;
                int nextCount = dfs(next, target, visited, length+1);
                total = total > nextCount ? nextCount : total;
                visited[next.idx] = false;
            }
        }

        return total;
    }
}

class Node1 {
    String word;
    int idx;
    LinkedList<Node1> nextWords = new LinkedList<>();

    public Node1(String word, int idx){
        this.word = word;
        this.idx = idx;
    }

    public void add(Node1 word) {
        nextWords.add(word);
    }
}