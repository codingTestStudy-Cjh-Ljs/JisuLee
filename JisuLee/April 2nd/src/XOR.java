import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    22.04.06
    GOC2021 - 1번 XOR 문제

    example 이해를 못하겠음..
 */

public class XOR {
    public static void main(String args[] ) throws Exception {

        //Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        //Test case
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            Integer[] array = new Integer[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                array[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(array, Collections.reverseOrder());

            int q = Integer.parseInt(br.readLine());

            for(int j = 1; j < q; j++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int m = Integer.parseInt(st2.nextToken());

                //solution
                System.out.println(findXOR(array, x, m));
            }
        }
    }

    static String findXOR(Integer[] array, int x, int m) {

        //가장 큰 수부터 차례로 비교
        for(Integer compare : array) {

            if(compare > m) continue;

            if((x & compare) == 0) {
                return Integer.toString(compare);
            }
        }

        return "-1";
    }
}
