import java.util.*;
import java.io.*;

/*
    백준 20057번 마법사 상어와 토네이도
    https://www.acmicpc.net/problem/20057

    구현문제
    나한테도 헷갈리면 계산보단 직관이 나을 때가 있다.
 */

public class SharkWizard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N+4][N+4];

        for(int i = 2; i < N+2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 2; j < N+2; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N, board));

    }

    public static int solution(int N, int[][] board) {

        int[] now = {N/2+2, N/2+2};
        double[] rate = {0.05, 0.1, 0.1, 0.02, 0.07, 0.07, 0.02, 0.01, 0.01};
        int[][] blow_x = {
                {0, 1, -1, 2, 1, -1, -2, -1, 1},
                {2, 1, 1, 0, 0, 0, 0, -1, -1},
                {0, -1, 1, -2, -1, 1, 2, -1, 1},
                {-2, -1, -1, 0, 0, 0, 0, 1, 1}
        };
        int[][] blow_y = {
                {-2, -1, -1, 0, 0, 0, 0, 1, 1},
                {0, 1, -1, -2, -1, 1, 2, -1, 1},
                {2, 1, 1, 0, 0, 0, 0, -1, -1},
                {0, -1, 1, -2, -1, 1, 2, -1, 1}
        };
        int[][] a = {
                {0, -1},
                {1, 0},
                {0, 1},
                {-1, 0}
        };
        int[] direct_x = {0, 1, 0, -1};
        int[] direct_y = {-1, 0, 1, 0};
        int count = -1;
        int move_count = 0;

        while(true) {
            if(move_count == 0) {
                count++;
                move_count = count/2+1;
            }
            int idx = count%4;

            //위치 변경
            now[0] = now[0] + direct_x[idx];
            now[1] = now[1] + direct_y[idx];

            if(now[0] <= 1 || now[1] <= 1) break;

            int left = board[now[0]][now[1]];
            for(int i = 0; i < 9; i++) {
                int x = now[0] + blow_x[idx][i];
                int y = now[1] + blow_y[idx][i];

                board[x][y] += Math.floor(board[now[0]][now[1]] * rate[i]);
                left -= Math.floor(board[now[0]][now[1]] * rate[i]);
            }

            int x = now[0] + a[idx][0];
            int y = now[1] + a[idx][1];
            board[x][y] += left;
            move_count--;
        }

        int answer = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(i < 2 || j < 2){ answer += board[i][j];}
                else if(i > board.length-3 || j > board.length-3) { answer += board[i][j]; }
            }
        }

        return answer;
    }
}

