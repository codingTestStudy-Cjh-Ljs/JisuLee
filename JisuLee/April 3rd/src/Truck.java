import java.util.*;
import java.io.*;

public class Truck {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};

        solution(bridge_length, weight, truck_weights);
    }

    public static void solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0, totalW = 0, idx = 0;
        Queue<Integer> bridge = new LinkedList<>();
        bridge.add(0);  //초기값

        while(idx < truck_weights.length) {
            time++;
            if(bridge.size() == bridge_length) {
                int out = bridge.poll();
                totalW -= out;
            }

            int truck = truck_weights[idx];

            if(totalW + truck > weight) {
                bridge.add(0);
            } else {
                bridge.add(truck);
                totalW += truck;
                idx++;
            }
        }

        time += bridge_length;

        System.out.println(time);
    }
}
