import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    22.04.05
    Practice Test용 아주 쉬운 알고리즘 테스트

    단순히 0에 제일 가까운 숫자를 찾는거였음
 */

public class PracticeTest {
    public static void main(String args[] ) throws Exception {
        //Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer A = new StringTokenizer(br.readLine());
        br.close();

        int[] array = new int[n];

        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(A.nextToken());
        }

        Arrays.sort(array);

        int idx = -1;
        for(int i = 0; i < n; i++) {
            if(array[i] >= 0) {
                idx = i;
                break;
            }
        }

        if(array[idx] == 0 ) System.out.println(0); //0을 찾았을 경우

        else if(idx < 0) { //양수가 하나도 없을 경우
            System.out.println(array[array.length - 1]);
        }
        else {
            if(idx != 0) {
                int Neg = Math.abs(array[idx - 1]);
                int Pos = array[idx];

                if(Neg == Pos) System.out.println(Pos);
                else if(Neg < Pos) System.out.println(Neg);
                else System.out.println(Pos);
            }
            else System.out.println(array[idx]);
        }
    }
}
