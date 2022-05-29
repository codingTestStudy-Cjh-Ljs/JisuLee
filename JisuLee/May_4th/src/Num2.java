import java.util.*;
import java.io.*;


public class Num2 {
    public static void main(String[] args) {
        int n = 6;
        int[] time = {1, 2, 3};

        System.out.println(solution(n, time));
    }

    public static int solution(int n, int[] times) {
        int max = times.length;
        int count = 1;
        int time = 0;

        while(count != n) {
            int left = n - count;
            int cut = max;

            if((left - max) < 0){
                cut = left;
            }

            if(count < cut) {
                cut = count;
            }

            count += cut;
            time += times[cut-1];
        }

        return time;
    }
}
