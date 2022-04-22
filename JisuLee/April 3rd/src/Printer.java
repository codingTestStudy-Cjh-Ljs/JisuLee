import java.util.*;
import java.io.*;

/*
    2022.04.10
    프로그래머스 스택/큐 - 프린터

    나는 점수대별로 몇개 존재하는지를 확인하는 방식이었는데
    다른사람 답안을 보니 큐에 먼저 집어넣고 priorities 배열을 정렬했음.
    이게 더 나은 방법인듯.
 */

public class Printer {
    public static void main(String[] args) {
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        solution(priorities, location);
    }

    public static void solution(int[] priorities, int location) {
        int[] howMany = new int[10];
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < priorities.length; i++) {
            howMany[priorities[i]]++;
            int[] item = {i, priorities[i]};
            queue.add(item);
        }

        int count = 0;
        while(!queue.isEmpty()){
            boolean retry = false;

            int[] item = queue.poll();
            for(int i = item[1]+1; i < 10; i++) {
                if(howMany[i] != 0) {
                    queue.add(item);
                    retry = true;
                    break;
                }
            }

            if(!retry) {
                howMany[item[1]]--;
                count++;
                if(item[0] == location) {
                    break;
                }
            }
        }

        System.out.println(count);
    }
}
