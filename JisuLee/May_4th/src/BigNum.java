import java.util.*;
import java.io.*;
/*
    백준 오큰수
    https://www.acmicpc.net/problem/17298

    역시 n^2으로 돌리면 시간초과 발생
    아이디어를 떠올리지 못했음. 스택 응용 문제 주의
 */

public class BigNum {

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        String answer = solution(num);
        System.out.println(answer);
    }

    public static String solution(int[] num){
        int[] answer = new int[num.length];
        Stack<Integer> stack = new Stack<>();
        stack.add(-1);

        for(int i = num.length-1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= num[i]) stack.pop();

            if(stack.isEmpty()) answer[i] = -1;
            else answer[i] = stack.peek();

            stack.add(num[i]);
        }

        StringBuilder answer_string = new StringBuilder();

        for(int i = 0; i < num.length-1; i++) {
            answer_string.append(answer[i] + " ");
        }
        answer_string.append(answer[num.length-1]);

        return answer_string.toString();
    }
}
