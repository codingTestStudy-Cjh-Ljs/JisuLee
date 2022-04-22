import java.util.*;

/*
    2022.04.22
    프로그래머스 탐욕법 단속카메라 문제
    https://programmers.co.kr/learn/courses/30/lessons/42884?language=java

    진입 기준으로 정렬하면 답은 다 맞아도 시간초과 발생
    진출 기준으로 정렬해야 한다는 것
    세그먼트 트리를 이용하는 방법도 있다는 듯.
 */

public class Camera {
    public static void main(String[] args) {
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        //int[][] routes = {{-20, -15}, {-18, -13}, {-14, -1}, {-12, 5}, {-9, 0}};
        //int[][] routes = {{-1, 0}};

        System.out.println(solution(routes));
    }

    public static int solution(int[][] routes) {
        int answer = 0;

        LinkedList<int[]> list = new LinkedList<>();

        for(int[] route : routes){
            list.add(route);
        }

        //routes 정렬(진출 오름차순으로)
        Collections.sort(list, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });


        answer = count(list);

        return answer;
    }

    public static int count(LinkedList<int[]> list) {
        int answer = 0;

        int idx = 0;
        while (!list.isEmpty()) {
            answer++;
            int[] firstOut = list.removeFirst();
            LinkedList<int[]> tmp = (LinkedList<int[]>) list.clone();
            for(int[] route : list){
                if(route[0] <= firstOut[1]) {
                    tmp.remove(route);
                }
            }

            list = tmp;
        }

        return answer;
    }
}
