
class Solution {

    public long minimumTime(int[] d, int[] r) {
        long left = d[0] + d[1];
        long right = (long) (d[0] + d[1]) * 3;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (isFeasible(mid, d[0], d[1], r[0], r[1])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isFeasible(long T, int d1, int d2, int r1, int r2) {
        long a1 = T - (T / r1);
        long a2 = T - (T / r2);
        long a12 = T - (T / r1) - (T / r2) + (T / lcm(r1, r2));

        return a1 >= d1 && a2 >= d2 && (a1 + a2 - a12) >= (d1 + d2);
    }

    private long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}