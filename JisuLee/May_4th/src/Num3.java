import java.util.*;
import java.io.*;

public class Num3 {
    public static void main(String[] args) {
        int fuel = 5;
        int[] powers = {1, 2, 3, 4, 5};
        int[] distances = {50, 40, 30, 20, 10};

        System.out.println(solution(fuel, powers, distances));
    }

    public static int solution(int fuel, int[] powers, int[] distances) {
        int longtime = 0;
        int idx = 0;
        int answer = 0;

        for(int i = 0; i < powers.length; i++) {
            int divide = (int) Math.ceil(distances[i] / powers[i]);
            if(longtime < divide) {
                longtime = divide;
                idx = i;
            }
        }

        List<int[]> others = new LinkedList<>();
        for(int i = 0; i < powers.length; i++){
            if(i == idx) continue;
            int[] tmp = {powers[i], distances[i], (int) Math.ceil(distances[i] / powers[i])};
            others.add(tmp);
        }

        Collections.sort(others, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2] - o1[2];
            }
        });

        for(int i = fuel - others.size(); i > 0; i--) {
            int l_speed = i * powers[idx];
            int l_time = (int) Math.ceil(((distances[idx] - ((l_speed * i) / 2))/l_speed) + i);
            int left = fuel - i;

            boolean check = true;
            int left_ships = others.size()-1;
            for(int[] ship : others) {
                int smallest = 0;
                for(int f = left - left_ships; f > 0; f--) {
                    int speed = f * ship[0];
                    int time = (int) Math.ceil((ship[1] - ((speed * f)/ 2))/speed + f);

                    if(time > l_time) {
                        break;
                    }
                    smallest = f;
                }

                if(smallest == 0) {
                    check = false;
                    break;
                }
                left-=smallest;
                left_ships--;
            }
            if(check) {
                answer = l_time;
                break;
            }
        }

        return answer;
    }
}
