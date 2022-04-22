import java.util.*;

/*
    2022.04.12
    프로그래머스 정렬 - 가장 큰 수

    스스로 해결 못함.
    (o1+o2).compareTo(o2+o1)이 포인트
    대체 왜 Comparator에서 에러가 나는지 아직도 모르겠음. 알아내야함
 */

public class BiggestNum {
    public static void main(String[] args) {

        Integer[] numbers = {0, 0, 0, 0};
        solution(numbers);
    }

    public static String solution(Integer[] numbers) {
        String[] num = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            num[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(num, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        String answer = "";
        for(int i = 0; i < num.length; i++) {
            answer += num[i];
        }

        if(answer.charAt(0) == '0') { // 0000 같은 경우 예외 처리
            return "0";
        }

        return answer;

    }
}
