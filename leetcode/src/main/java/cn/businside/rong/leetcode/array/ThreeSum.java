package cn.businside.rong.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        HashSet<Integer> hashNums = new HashSet<Integer>(nums.length);
        HashMap<Integer,List<List<Integer>>> twoSum = new HashMap<Integer,List<List<Integer>>>(nums.length-1);

        //Scan once
        List<List<Integer>> threeSum = new ArrayList<List<Integer>>();
        for(int i =0;i < nums.length; i++)
        {
            List<List<Integer>> result = twoSum.get(0-nums[i]);
            if (result!=null && result.size()>0)
            {
                for(List<Integer>)
            }
           
        }

        return null;
    }
    
}
