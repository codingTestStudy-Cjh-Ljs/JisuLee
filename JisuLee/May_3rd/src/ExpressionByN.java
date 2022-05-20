import java.util.*;

/*
    프로그래머스 동적프로그래밍 문제 - N으로 표현
    https://programmers.co.kr/learn/courses/30/lessons/42895?language=java
    pm11:45 ~ 12:45

    와.. 접근 자체를 못하겠음
    결국 힌트를 봤고, 규칙을 찾는 것(특히 점화식)이 관건.
    문제를 많이 접해봐야 함.
 */

public class ExpressionByN {
    public static void main(String[] args) {
        int N = 2;
        int number = 11;

        System.out.println(solution(N, number));
    }

    public static int solution(int N, int number){
        if(N == number){
            return 1;
        }

        Set[] list = new Set[9];

        for(int i = 0; i < 9; i++) {
            list[i] = new HashSet<Integer>();
        }

        list[1].add(N);

        //i : N 사용 개수
        for(int i = 2; i < 9; i++) {
            StringBuilder simple = new StringBuilder();
            for(int k = 0; k < i;k++) {
                simple.append(Integer.toString(N));
            }
            if(Integer.parseInt(simple.toString()) == number) return i;
            list[i].add(Integer.parseInt(simple.toString()));
            //j : 집합 U 집합에 이용되는 수
            for(int j = 1; j < 9; j++) {
                for(Object x: list[j]){
                    for(Object y: list[i-j]){
                        if((int)x+(int)y == number){
                            return i;
                        }
                        if((int)x-(int)y == number){
                            return i;
                        }
                        if((int)x*(int)y == number){
                            return i;
                        }
                        if((int)y != 0 && (int)x/(int)y == number){
                            return i;
                        }
                        list[i].add((int)x+(int)y);
                        list[i].add((int)x-(int)y);
                        list[i].add((int)x*(int)y);
                        if((int) y != 0){
                            list[i].add((int) x / (int) y);
                        }
                    }

                }
            }

        }

        return -1;
    }

}
