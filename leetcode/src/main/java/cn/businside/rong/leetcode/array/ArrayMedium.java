public class ArrayMedium {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            
            
            
            if (nums1.length == 0)
            {
                return findMedianOfOneArray(nums2);
            }
            
            if (nums2.length == 0)
            {
                return findMedianOfOneArray(nums1);
            }
            //find middle
            int min1 = nums1[0], max1= nums1[nums1.length-1];
            int min2 = nums2[0], max2= nums2[nums2.length-1];
            
            int length1 = nums1.length;
            int length2 = nums2.length;
            int middle = (length1 + length2)/2;
            int middle1 =  middle + (length1 + length2) % 2;
            if (middle == middle1)
            {
                middle1 = middle1 +1;
            }else
                middle = middle1;
            
            if (min1 >= max2)
            {
               return findNoLapMedian(middle, middle1, nums1, nums2);
            }
            
            if (min2 >= max1)
            {
               return findNoLapMedian(middle, middle1, nums2, nums1);
            }
            
            int[] newNums = new int[nums1.length + nums2.length];
            
            int i=0, j=0;
            while( i< length1)
            {
                while( j < length2)
                {
                    if (nums1[i] <=nums2[j])
                    {
                        newNums[i+j] = nums1[i];
                        i++;
                        break;
                    }
                    else 
                    {
                        newNums[i+j] = nums2[j];
                        j++;
                    }
                }
                if (i == length1 || j == length2)
                    break;
            }
            
            while(i < length1)
            {
                newNums[i+j] = nums1[i];
                i++;
            }
            
            while(j < length2)
            {
                newNums[i+j] = nums2[j];
                j++;
            }
            
            return (newNums[middle-1] + newNums[middle1-1])/2.0;
        }
        
        public double findNoLapMedian(int middle, int middle1,int[] nums1, int[] nums2)
        {
             if ( middle > nums2.length)
                {
                    return (nums1[middle - nums2.length -1]+nums1[middle1 - nums2.length -1] ) /2.0;
                }
                else if (middle == nums2.length)
                {
                    return middle == middle1 ? nums2[middle -1]: (nums2[middle -1] + nums1[0])/2.0;
                } else
                {
                    if (middle1 == middle )
                        return nums2[middle -1]*2.0 /2.0;
                    else 
                        return (middle1 -1)== nums2.length? (nums2[middle -1]+nums1[0])/2.0:(nums2[middle-1] + nums2[middle1-1])/2.0;
                }
            //return Double.NaN;
        }
        
        public double findMedianOfOneArray(int[] nums)
        {
            int middle = nums.length%2;
            if( middle == 1 )
                return  nums[nums.length/2 ]*1.0;
            else
                return  (nums[nums.length/2-1] +  nums[nums.length/2]) /2.0;
        }

        /*
        标准答案
        */
        public double findMedianSortedArrays(int[] A, int[] B) {
            int m = A.length;
            int n = B.length;
            if (m > n) { // to ensure m<=n
                int[] temp = A; A = B; B = temp;
                int tmp = m; m = n; n = tmp;
            }
            int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = halfLen - i;
                if (i < iMax && B[j-1] > A[i]){
                    iMin = i + 1; // i is too small
                }
                else if (i > iMin && A[i-1] > B[j]) {
                    iMax = i - 1; // i is too big
                }
                else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) { maxLeft = B[j-1]; }
                    else if (j == 0) { maxLeft = A[i-1]; }
                    else { maxLeft = Math.max(A[i-1], B[j-1]); }
                    if ( (m + n) % 2 == 1 ) { return maxLeft; }
    
                    int minRight = 0;
                    if (i == m) { minRight = B[j]; }
                    else if (j == n) { minRight = A[i]; }
                    else { minRight = Math.min(B[j], A[i]); }
    
                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }
}
