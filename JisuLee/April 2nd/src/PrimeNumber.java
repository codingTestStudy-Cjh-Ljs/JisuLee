import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    22.04.06
    GOCC 2021 1번 Prime number 개수 구하는 문제

    https://www.geeksforgeeks.org/google-online-challenge-for-summer-internship-2021/?ref=rp

    소수 구하기 - 에라토스테네스의 체
 */

public class PrimeNumber {

    static int[] array;
    static LinkedList<Integer> prime = new LinkedList<>();

    public static void main(String args[] ) throws Exception {

        //Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        array = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        //소수 집합 만들기
        setPrimeArray();

        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i < q; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st2.nextToken())-1;
            int R = Integer.parseInt(st2.nextToken())-1;

            System.out.println(solution(L, R));
        }
    }

    static void setPrimeArray() {

        int max = 0;
        for(int i = 0; i< array.length; i++){
            if(max < array[i]) max = array[i];
        }

        Number[] base = new Number[max+1];
        for(int i = 0; i <= max; i++) {
            base[i] = new Number(i);
        }

        for(Number i: base) {

            if(i.num < 2) continue;
            if(i.notPrime == true) continue;

            int primeNum = i.num;
            prime.add(primeNum);

            int multiple = 2;
            while(max >= primeNum * multiple) {
                base[primeNum * multiple].notPrime = true;
                multiple++;
            }
        }
    }

    public static int solution(int L, int R) {

        int count = 0;

        for(int num: prime) {
            boolean canDivide = true;

            for(int i = L; i <= R; i++) {
                int item = array[i];
                if(num > item || item % num != 0) {
                    canDivide = false;
                    break;
                }
            }
            if(canDivide) count++;
        }

        return count;
    }
}

class Number {
    int num;
    boolean notPrime = false;

    public Number(int num) {
        this.num = num;
    }
}