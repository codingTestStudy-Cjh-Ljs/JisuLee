/*
    leetcode - First Bad Version
    https://leetcode.com/problems/first-bad-version/

    이진탐색 기초.
    이진탐색 구현은 아직도 헷갈리는 거 같다.
    유튜브 영상 보면서 확실하게 매커니즘 이해하기
 */

public class FirstBadVersion {
    static int n = 5;
    static int bad = 4;

    public static void main(String[] args) {
        System.out.println(firstBadVersion(n));
    }

    public static int firstBadVersion(int n) {
        int low = 1, high = n;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(isBadVersion(mid) == true) {
                high = mid - 1;
            } else { low = mid + 1; }
        }

        return low;
    }

    public static boolean isBadVersion(int num) {
        if(num < bad) return false;
        else return true;
    }
}
