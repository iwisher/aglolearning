package cn.businside.rong.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.validator.constraints.Length;

public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        HashSet<Integer> hashNums = new HashSet<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i : nums) {
            hashNums.add(i);
        }
        // Hashset<Integer> hashNums = new Hashset<Integer>(nums.length);

        int lastSet1 = Integer.MIN_VALUE, lastSet2 = Integer.MIN_VALUE;
        int numsLength = nums.length;
        for (int i = 0; i <= numsLength - 3; i++) {
            // Sorted already, will bigger than 0 for sure
            if (nums[i] > 0)
                break;
            
            if (nums[i] == lastSet1 && nums[i]!=0)
                continue;

            for (int j = i + 1; j <= numsLength - 2; j++) {
                

                int sum = nums[i] + nums[j];
                if (-sum < nums[j])
                    break;

                if (hashNums.contains(-sum)) {
                    if (-sum == nums[j]) {
                        if (-sum != nums[j + 1])
                            continue;
                    }
                    if (nums[i] != lastSet1 || nums[j] != lastSet2) {
                        lastSet1 = nums[i];
                        lastSet2 = nums[j];
                        List<Integer> uniqueResult = new ArrayList<Integer>();
                        uniqueResult.add(lastSet1);
                        uniqueResult.add(lastSet2);
                        uniqueResult.add(-sum);
                        result.add(uniqueResult);
                    }
                }

                if (-sum < nums[j + 1]) {
                    break;
                }
            }

        }

        return result;
    }

}
