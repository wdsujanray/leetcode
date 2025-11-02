
import java.util.*;

class Solution {

    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // Find min and max values
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // Create a set for quick lookup
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Check all numbers in the range [min, max]
        for (int i = min; i <= max; i++) {
            if (!numSet.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }
}
©leetcode