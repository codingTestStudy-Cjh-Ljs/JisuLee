import java.util.*;
import java.io.*;

/*
    프로그래머스 dfs/bfs 문제 - 여행경로
 */


class Trip {

    static HashMap<String, Node2> node2s = new HashMap<>();
    static LinkedList<String> answer = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        //그래프 만들기
        setGraph(tickets);

        //dfs
        boolean[] visited = new boolean[tickets.length];
        LinkedList<String> tripList = new LinkedList<>();
        tripList.add("ICN");
        dfs("ICN", tripList, 1, tickets, visited);

        String[] answerArray = new String[answer.size()];
        for(int i = 0; i < answer.size(); i++) {
            answerArray[i] = answer.get(i);
        }

        return answerArray;
    }

    public static void setGraph(String[][] tickets) {
        for(int i = 0; i < tickets.length; i++) {
            if (node2s.containsKey(tickets[i][0])) {
                Node2 node2 = node2s.get(tickets[i][0]);
                node2.add(i);
                node2s.put(tickets[i][0], node2);
            } else {
                Node2 newNode2 = new Node2(tickets[i][0]);
                newNode2.add(i);
                node2s.put(tickets[i][0], newNode2);
            }
        }
    }

    public static void dfs(String now, LinkedList<String> tripList, int count, String[][] tickets, boolean[] visited) {
        LinkedList<Integer> list = null;

        if(node2s.containsKey(now)){
            list = node2s.get(now).to;
        }

        //모든 티켓을 사용했을 경우
        if(count == tickets.length) {
            if(list != null) {
                for(Integer idx: list) {
                    if(visited[idx] == false){
                        tripList.add(tickets[idx][1]);
                        break;
                    }
                }
            }

            if(!answer.isEmpty() && !isFirst(tripList)) {
                return;
            } else {
                if(!answer.isEmpty()) answer.clear();
                answer = (LinkedList<String>) tripList.clone();
                return;
            }
        }

        if(list == null) return;

        for(int i = 0; i < list.size(); i++) {
            Integer ticket = list.get(i);

            if(visited[ticket] == false){
                LinkedList<String> newList = (LinkedList<String>) tripList.clone();
                newList.add(tickets[ticket][1]);
                visited[ticket] = true;
                dfs(tickets[ticket][1], newList, count+1, tickets, visited);
                visited[ticket] = false;
            }
        }
    }

    public static boolean isFirst(LinkedList<String> list) {
        for(int i = 0; i < list.size(); i++) {
            if(answer.get(i).compareTo(list.get(i)) < 0) return false;
            else if(answer.get(i).compareTo(list.get(i)) > 0) {
                return true;
            }
        }

        return false;
    }
}

class Node2{
    String from;
    LinkedList<Integer> to = new LinkedList<>();

    public Node2(String from) {
        this.from = from;
    }

    public void add(int idx) {
        to.add(idx);
    }
}