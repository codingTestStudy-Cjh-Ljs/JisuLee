import java.util.*;
/*
    프로그래머스 힙 - 디스크 컨트롤러

    라인에 유사 문제가 나왔다길래 다시 풀어봄.
 */

public class DiskController {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>(){

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int now = 0;
        int idx = 0;
        int count = 0;
        while(true) {
            //현재 시간에 들어온 작업 힙에 넣어 정렬
            while(idx < jobs.length && jobs[idx][0] <= now) {
                heap.add(jobs[idx++]);
            }

            //수행시간이 가장 적은 작업 선정
            //만약 아직 들어온 작업이 없다면 now 시간을 수정하고 다시 루프
            if(heap.isEmpty()){
                if(idx >= jobs.length) break;
                now = jobs[idx][0];
                continue;
            }
            int[] job = heap.poll();

            now += job[1];
            count += now - job[0];
        }

        return count/jobs.length;
    }
}
