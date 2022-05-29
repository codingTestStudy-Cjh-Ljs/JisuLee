import java.util.*;
import java.io.*;

public class Num1 {
    public static void main(String[] args) {
        String[] logs = {"kate sqrt"};

        String[] answer = solution(logs);
        for(String a : answer){
            System.out.print(a + " ");
        }
    }

    public static String[] solution(String[] logs) {
        HashMap<String, Set> hash = new HashMap<>();
        Set<String> users = new HashSet<>();

        for(String log : logs) {
            String[] names = log.split(" ");
            users.add(names[0]);

            if(!hash.containsKey(names[1])){
                hash.put(names[1], new HashSet<String>());
            }

            hash.get(names[1]).add(names[0]);
        }

        int limit = (users.size()+1) / 2;
        LinkedList<String> list = new LinkedList<>();
        for(String pro: hash.keySet()){
            if(hash.get(pro).size() >= limit) {
                list.add(pro);
            }
        }
        Collections.sort(list, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
