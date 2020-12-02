package cn.businside.rong.leetcode.dp;

public class MaxAvgSubArray {

    // Time complex n^n
    public static double findMaxAverage(int[] nums, int k) {
        double max_avg = Double.NEGATIVE_INFINITY;
        // double[] stride_avg = new double[nums.length+1 -k];

        for (int stride = k; stride <= nums.length; stride++) {
            double stride_local = 0.0d;

            for (int i = 0; i < stride; i++) {
                stride_local += nums[i];
                max_avg = stride_local / stride > max_avg ? stride_local / stride : max_avg;
                // stride_avg[0] = stride_max/stride;
            }
            if (stride == nums.length)
                break;

            for (int i = stride; i < nums.length; i++) {
                stride_local = stride_local + nums[i] - nums[i - stride];

                // stride_avg[i-stride+1] = stride_max/stride;

                max_avg = stride_local / stride > max_avg ? stride_local / stride : max_avg;
            }
        }

        return max_avg;
    }

    // Time complex n
    public static double findMaxAverageSeek(int[] nums, int k) {
        double max_avg = Double.NEGATIVE_INFINITY;
        
        // double[] stride_avg = new double[nums.length+1 -k];

        double stride_local = 0.0d;

        /**
         * Assume Sum/K denote avg, to find longer than K. Formula as below (Sum +
         * (N1+N2...+Ni)) / (K +i) > Sum/K Simplified to (N1+...+Ni) * K > Sum * i Then
         * (N1+...+Ni) > (Sum/K) * i Scan the rest of array will give the result
         */

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                stride_local += nums[i];
                continue;
            }

            if (i == k - 1)
            {
                stride_local += nums[i];
                max_avg = stride_local / k > max_avg ? stride_local / k : max_avg;
            }
            else {

                stride_local = stride_local + nums[i] - nums[i - k];
                // stride_avg[i-stride+1] = stride_max/stride;
                if (stride_local / k > max_avg) {
                    max_avg = stride_local / k;
                }
            }

            // Test longer subarray
            if (i < nums.length - 1) {
                double extend_total = stride_local;
                for (int j = 1; i + j < nums.length; j++) {
                    extend_total += nums[i + j];
                    double new_avg = extend_total / (k + j);
                    // System.out.println("Extend max:" + max_avg + "\t stride:"+(k+j)
                    // +"\t pos:"+i);
                    if (new_avg > max_avg) {
                        max_avg = new_avg;

                    }
                }
            }
        }

        return max_avg;

    }

    public static double findMaxAverageSExtendOnlyMaxKAvg(int[] nums, int k) {
        double max_avg = Double.NEGATIVE_INFINITY;
        double max_K_avg = Double.NEGATIVE_INFINITY;
        // double[] stride_avg = new double[nums.length+1 -k];

        double stride_local = 0.0d;

        /**
         * Assume Sum/K denote avg, to find longer than K. Formula as below (Sum +
         * (N1+N2...+Ni)) / (K +i) > Sum/K Simplified to (N1+...+Ni) * K > Sum * i Then
         * (N1+...+Ni) > (Sum/K) * i Scan the rest of array will give the result
         */

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                stride_local += nums[i];
                continue;
            }

            double new_avg = Double.NEGATIVE_INFINITY;
            if (i == k - 1) {
                stride_local += nums[i];
                new_avg = stride_local / k;
                max_avg = new_avg > max_avg ? new_avg : max_avg;
                max_K_avg = max_avg;
            } else {

                stride_local = stride_local + nums[i] - nums[i - k];
                // stride_avg[i-stride+1] = stride_max/stride;
                new_avg = stride_local / k;
                if (new_avg > max_avg) {
                    max_avg = stride_local / k;
                }
                max_K_avg = new_avg > max_K_avg ? new_avg : max_K_avg;
            }

            // Test longer subarray
            //@ToDO Failed
            if (i < nums.length - 1 && Math.max(Math.abs( new_avg - max_K_avg), 0.0001) == 0.0001) {
                double extend_total = stride_local;
                for (int j = 1; i + j < nums.length; j++) {
                    extend_total += nums[i + j];
                    new_avg = extend_total / (k + j);
                    // System.out.println("Extend max:" + max_avg + "\t stride:"+(k+j)
                    // +"\t pos:"+i);
                    if (new_avg > max_avg) {
                        max_avg = new_avg;

                    }
                }
            }
        }

        return max_avg;
    }

    public static double findMaxAverageSeek2(int[] nums, int k) {
        double max_avg = Double.NEGATIVE_INFINITY;
        
        // double[] stride_avg = new double[nums.length+1 -k];

        double stride_local = 0.0d;

        /**
         * Assume Sum/K denote avg, to find longer than K. Formula as below (Sum +
         * (N1+N2...+Ni)) / (K +i) > Sum/K Simplified to (N1+...+Ni) * K > Sum * i Then
         * (N1+...+Ni) > (Sum/K) * i Scan the rest of array will give the result
         * 
         * If Avg increasing(Sum/K), aka, slope of line increasing, no chance you can bigger.
         * Unless zero or has neg
         *
         */

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                stride_local += nums[i];
                continue;
            }

            if (i == k - 1)
            {
                stride_local += nums[i];
                max_avg = stride_local / k > max_avg ? stride_local / k : max_avg;
            }
            else {

                stride_local = stride_local + nums[i] - nums[i - k];
                // stride_avg[i-stride+1] = stride_max/stride;
                if (stride_local / k > max_avg) {
                    max_avg = stride_local / k;
                }
            }

            // Test longer subarray
            if (i < nums.length - 1) {
                double extend_total = stride_local;
                for (int j = 1; i + j < nums.length; j++) {
                    extend_total += nums[i + j];
                    double new_avg = extend_total / (k + j);
                    // System.out.println("Extend max:" + max_avg + "\t stride:"+(k+j)
                    // +"\t pos:"+i);
                    if (new_avg > max_avg) {
                        max_avg = new_avg;

                    }
                }
            }
        }

        return max_avg;

    }

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[] { 1, 12, -5, -6, 50, 3 }, 4));
        System.out.println(findMaxAverage(new int[] { 1, 12, -5, -6, 50, 3 }, 2));
        System.out.println(findMaxAverage(new int[] { -1 }, 1));
        System.out.println(findMaxAverage(new int[] { 3, 3, 4, 3, 0 }, 3));
        System.out.println(findMaxAverage(new int[] { 8, 9, 3, 1, 8, 3, 0, 6, 9, 2 }, 8));

        System.out.println();
        System.out.println(findMaxAverageSeek(new int[] { 1, 12, -5, -6, 50, 3 }, 4));
        System.out.println(findMaxAverageSeek(new int[] { 1, 12, -5, -6, 50, 3 }, 2));
        System.out.println(findMaxAverageSeek(new int[] { -1 }, 1));
        System.out.println(findMaxAverageSeek(new int[] { 3, 3, 4, 3, 0 }, 3));
        System.out.println(findMaxAverageSeek(new int[] { 8, 9, 3, 1, 8, 3, 0, 6, 9, 2 }, 8));
    }

}
