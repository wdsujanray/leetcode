import java.util.*;

class Solution {
    public long maxProduct(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return 0;

        // We need to track the smallest and largest elements
        // Since we can replace one element, we need to consider various combinations

        // Find the 3 smallest and 3 largest elements
        int[] min = new int[3]; // smallest elements
        int[] max = new int[3]; // largest elements

        Arrays.fill(min, Integer.MAX_VALUE);
        Arrays.fill(max, Integer.MIN_VALUE);

        for (int num : nums) {
            // Update smallest elements
            if (num < min[0]) {
                min[2] = min[1];
                min[1] = min[0];
                min[0] = num;
            } else if (num < min[1]) {
                min[2] = min[1];
                min[1] = num;
            } else if (num < min[2]) {
                min[2] = num;
            }

            // Update largest elements
            if (num > max[0]) {
                max[2] = max[1];
                max[1] = max[0];
                max[0] = num;
            } else if (num > max[1]) {
                max[2] = max[1];
                max[1] = num;
            } else if (num > max[2]) {
                max[2] = num;
            }
        }

        long result = Long.MIN_VALUE;
        int MAX_VAL = 100000;
        int MIN_VAL = -100000;

        // Case 1: Don't replace any of the top 3 largest elements
        // Replace some other element with MAX_VAL
        long case1 = (long) max[0] * max[1] * Math.max(max[2], MAX_VAL);
        result = Math.max(result, case1);

        // Case 2: Replace one of the top 3 largest with MAX_VAL
        long case2a = (long) MAX_VAL * max[1] * max[2];
        long case2b = (long) max[0] * MAX_VAL * max[2];
        long case2c = (long) max[0] * max[1] * MAX_VAL;
        result = Math.max(result, Math.max(case2a, Math.max(case2b, case2c)));

        // Case 3: Use two smallest (negative) and one largest (positive)
        // Replace one element with MAX_VAL
        long case3a = (long) min[0] * min[1] * MAX_VAL; // Replace third element with MAX_VAL
        long case3b = (long) min[0] * MAX_VAL * max[0]; // Replace middle element with MAX_VAL
        long case3c = (long) MAX_VAL * min[1] * max[0]; // Replace first element with MAX_VAL
        result = Math.max(result, Math.max(case3a, Math.max(case3b, case3c)));

        // Case 4: Use MIN_VAL to create large positive product with two negatives
        long case4a = (long) min[0] * min[1] * MIN_VAL; // Two negatives * large negative = large positive
        long case4b = (long) min[0] * MIN_VAL * max[0];
        long case4c = (long) MIN_VAL * min[1] * max[0];
        result = Math.max(result, Math.max(case4a, Math.max(case4b, case4c)));

        // Case 5: Original maximum products without replacement
        long original1 = (long) max[0] * max[1] * max[2];
        long original2 = (long) min[0] * min[1] * max[0]; // Two negatives * one positive
        result = Math.max(result, Math.max(original1, original2));

        return result;
    }
}