package cn.businside.rong.leetcode.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 */
public class NoRepeatLongestSubstr {

    /**
     * Havn't consider reuse the not duplicate part "dvdf" - When 2nd d comes, shoud
     * recount from pos 1(v) instead of 2(d)
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringError(String s) {
        HashSet<Character> subStr = new HashSet<Character>();
        int longest = 0;
        int size = 0;
        int strLength = s.length();
        for (int i = 0; i < strLength; i++) {
            char c = s.charAt(i);
            if (subStr.contains(c)) {
                subStr.clear();
                // subStr.add(c);
                longest = longest < size ? size : longest;
                size = 0;
            }

            subStr.add(c);
            size++;
        }
        return size > longest ? size : longest;
    }

    public int lengthOfLongestSubstringSlideWindow(String s){
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /*
     * "abc" "dvdf" "vadfveavd"
     */
    /**
     * Runtime: 4 ms, faster than 89.35% of Java online submissions for Longest Substring Without Repeating Characters.
Memory Usage: 38.7 MB, less than 96.67% of Java online submissions for Longest Substring Without Repeating Characters.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> subStr = new HashMap<Character, Integer>();
        int sl = s.length();
        Integer startPos = 0;
        int longest = 0;
        for (int i = 0; i < sl; i++) {
            char c = s.charAt(i);
            Integer pos = subStr.put(c, i);
            if (pos != null && pos >= startPos) {
                // i is duplciate already, so skip +1
                int strSize = (i - startPos);
                longest = strSize > longest ? strSize : longest;
                // should be latest duplciate +1
                startPos = pos + 1;
            }

        }
        return (sl - startPos) > longest ? (sl - startPos) : longest;

    }

}
