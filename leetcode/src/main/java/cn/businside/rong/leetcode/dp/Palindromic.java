package cn.businside.rong.leetcode.dp;

public class Palindromic {
    // public static ;

    static long simpleStart = 0l;
    static long start = 0l;

    private static int lo, maxLen;

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i); // assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); // assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private static void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
    
    public static String getPalindromicSimple(String target) {
     
        //long s = System.currentTimeMillis();
        if (target == null || target.equals(""))
            return target;

        int len = target.length();
        int currentSize = 1;

        int finalStart = 0, finalEnd = 1;

        for (int i =1; i< target.length(); i++)
        {
            //Already scan the whole string OR the left string is small than half of current size.
            if (currentSize == len ||
                    ((len - i) < (currentSize/2 -1)))
                break;
            //Handle duplciate chars
            //case, aaaaa
            int dup = i -1;
            while (dup + 1 < len && target.charAt(dup) == target.charAt(dup+1))
            {
                dup++;
            }

            int dupSize = dup -i + 2;
            if (dupSize > currentSize)
            {
                currentSize = dupSize;
                finalStart = i-1;
                finalEnd = dup + 1;
            }

             //check two sides to match 
             int left = i- 1, right=i+1;
             if (dupSize > 1)
             {
                //saas, saaaas
                left = i-2; 
                right = dup + 1;
                i = dup;
             }
            
             boolean matched = false;
             while( left>=0 && right < len && target.charAt(left) == target.charAt(right))
             {
                 left--;
                 right++;
                 matched = true;
             }

            if (matched)
            {
                int matchedSize = right - left -2;
                
                if (matchedSize  > currentSize )
                {
                    currentSize = matchedSize;
                    finalStart = left+1;
                    finalEnd = right;
                }
            }

             
            
        }
        System.out.println(target.substring(finalStart, finalEnd));
        return target.substring(finalStart, finalEnd);

    }

    public static String getPalindromicSimpleBug(String target) {
        long s = System.currentTimeMillis();

        if (target == null || target.equals(""))
            return target;

        int len = target.length();
        int currentSize = 1;

        int finalStart = 0, finalEnd = 1;

        // boolean[][] palindromic = new boolean[len][len];

        for (int i = 1; i < len;) {

            // Adding new char, also adding duplciation; this migth extend longer
            int dup = i - 1;
            while (dup + 1 < len && target.charAt(dup) == target.charAt(dup + 1)) {
                dup++;
            }

            if (dup >= i) {
                if (dup - i + 2 > currentSize) {
                    currentSize = dup - i + 2;
                    finalStart = i - 1;
                    finalEnd = dup + 1;

                }

                // in case exactly matching on even string,
                // "tattar rattat"
                // in case two side match, sooos, soos

                int skip = 0;
                while (i - skip - 2 >= 0 && dup + skip + 1 < len
                        && target.charAt(i - skip - 2) == target.charAt(dup + skip + 1)) {
                    skip++;
                }

                if (skip > 0) {
                    if (2 * skip + (dup - i + 2) > currentSize) {
                        currentSize = 2 * skip + (dup - i + 2);
                        finalStart = i - skip - 1;
                        finalEnd = dup + skip + 1;
                    }
                    i = dup + skip;
                } else {
                    i = dup + 1;
                }

                continue;
            }

            // Case, [1,pos] is palidromic
            if (finalStart == 1 && finalEnd == i && target.charAt(0) == target.charAt(i)) {
                currentSize = i + 1;
                // palindromic[i][0] = true;
                finalStart = 0;
                finalEnd = i + 1;
                i++;
                continue;
            }

            // case, scan palindromic as pos is center
            int skip = 0;
            for (; i + 1 + skip < len && i - 1 - skip >= 0; skip++) {
                if (target.charAt(i - skip - 1) != target.charAt(i + skip + 1))
                    break;
            }
            if (skip > 0) {
                if (2 * skip + 1 > currentSize) {
                    currentSize = 2 * skip + 1;
                    finalStart = i - skip;
                    finalEnd = i + skip + 1;
                }
            }
            i++;

        }

        simpleStart = simpleStart + (System.currentTimeMillis() - s);
        System.out.println(target.substring(finalStart, finalEnd));
        return target.substring(finalStart, finalEnd);
    }

    public static String getPalindromic(String target) {

        long s = System.currentTimeMillis();

        if (target == null || target.equals(""))
            return target;

        int len = target.length();
        int currentSize = 0;

        int finalStart = 0, finalEnd = 1;

        boolean[][] palindromic = new boolean[len][len];

        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (target.charAt(i) == target.charAt(j) && (i - j <= 2 || palindromic[j + 1][i - 1])) {
                    palindromic[j][i] = true;

                    if ((i - j > currentSize)) {
                        currentSize = i - j;
                        finalStart = j;
                        finalEnd = i + 1;

                    }

                }
            }

        }

        start = start + (System.currentTimeMillis() - s);

        System.out.println(target.substring(finalStart, finalEnd));
        System.out.println();

        return target.substring(finalStart, finalEnd);

    }

    public static String getPalindromicBackward(String target) {
        if (target == null || target.equals(""))
            return target;

        int len = target.length();
        int currentSize = 0;
        int finalStart = 0, finalEnd = 1;

        boolean[][] palindromic = new boolean[len][len];

        for (int i = len; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (target.charAt(i) == target.charAt(j) && (j - i <= 2 || palindromic[i + 1][j - 1])) {
                    palindromic[i][j] = true;

                    if ((j - i > currentSize)) {
                        currentSize = j - i;
                        finalStart = i;
                        finalEnd = j + 1;
                    }

                }
            }

        }

        return target.substring(finalStart, finalEnd);

    }

    public static void main(String[] args) {

        for (long i = 0; i < 1; i++) {

            getPalindromicSimple("ababababababa");
            getPalindromic("ababababababa");

            getPalindromicSimple("abb");
            getPalindromic("abb");

            getPalindromicSimple("aaaabaaa");
            getPalindromic("aaaabaaa");


            

            getPalindromicSimple(
                    "iptmykvjanwiihepqhzupneckpzomgvzmyoybzfynybpfybngttozprjbupciuinpzryritfmyxyppxigitnemanreexcpwscvcwddnfjswgprabdggbgcillisyoskdodzlpbltefiz");
            getPalindromic(
                    "iptmykvjanwiihepqhzupneckpzomgvzmyoybzfynybpfybngttozprjbupciuinpzryritfmyxyppxigitnemanreexcpwscvcwddnfjswgprabdggbgcillisyoskdodzlpbltefiz");

            
            getPalindromicSimple("tattarrattat");
            getPalindromic("tattarrattat");
            /*
             * System.out.println(getPalindromicSimple("baaaab"));
             * System.out.println(getPalindromic("baaaab")); System.out.println();
             * 
             * System.out.println(getPalindromicSimple("aaa");
             * System.out.println(getPalindromic("aaa"); System.out.println();
             * 
             * System.out.println(getPalindromicSimple("aaaa");
             * System.out.println(getPalindromic("aaaa"); System.out.println();
             * 
             * System.out.println(getPalindromicSimple("aaabaaaa");
             * System.out.println(getPalindromic("aaabaaaa"); System.out.println();
             * System.out.println(getPalindromicSimple("ababababababa");
             * System.out.println(getPalindromic("ababababababa"); System.out.println();
             * 
             * System.out.println(getPalindromicSimple("abacdfgdcabac");
             * System.out.println(getPalindromic("abacdfgdcabac"); System.out.println();
             * System.out.println(getPalindromicSimple("babad");
             * System.out.println(getPalindromic("babad"); System.out.println();
             * System.out.println(getPalindromicSimple("baaaaaaaab");
             * System.out.println(getPalindromic("baaaaaaaab"); System.out.println();
             * System.out.println(getPalindromicSimple("babad");
             * System.out.println(getPalindromic("babad");
             */

            getPalindromicSimple("baaaab");
            getPalindromic("baaaab");

            getPalindromicSimple("aaa");
            getPalindromic("aaa");

            getPalindromicSimple("aaaa");
            getPalindromic("aaaa");

            getPalindromicSimple("aaabaaaa");
            getPalindromic("aaabaaaa");

            getPalindromicSimple("abacdfgdcabac");
            getPalindromic("abacdfgdcabac");

            getPalindromicSimple("babad");
            getPalindromic("babad");

            getPalindromicSimple("baaaaaaaab");
            getPalindromic("baaaaaaaab");

            getPalindromicSimple("babad");
            getPalindromic("babad");
        }

        System.out.println(
                String.format("Simple time is %fs;  Normal time is %fs", simpleStart / 1000d, simpleStart / 1000d));
    }

}