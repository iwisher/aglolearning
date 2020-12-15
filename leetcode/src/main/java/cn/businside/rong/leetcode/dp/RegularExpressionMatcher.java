package cn.businside.rong.leetcode.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 *
 */
public class RegularExpressionMatcher {

    public boolean isMatch() {
        return false;
    }

    /**
     * DP 是简化版的 递归
     * 
     * 
"a"
"ab*"
     * 
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchBug2(String s, String p) {
        int j = 0;// pos of string
        int i = 0;// pos of pattern
        while (j < s.length() && i < p.length()) {
            // match the char and .
            char pChar = p.charAt(i);
            boolean currentMatch = false;
            if (pChar == s.charAt(j) || pChar == '.') {
                j++; // String move to next char
                currentMatch = true;
            }

            if ((i + 1) < p.length() && p.charAt(i + 1) == '*') {
                // Can be zero occur
                i++;
                if (currentMatch == false) {
                    i++;
                    continue;
                }
                /*
                 * .* .*c .*.* .*.*c find next none asterism pattern
                 */
                boolean hasNextPChar = (i + 1) < p.length() ? true : false;
                char nextPChar = pChar;
                if (hasNextPChar) {
                    i++;
                    nextPChar = p.charAt(i);
                }

                if (pChar == '.') {
                    while (i < p.length() && p.charAt(i) == '.' && p.charAt(i) == '*')
                        i++;
                    /**
                     * .* mapping to any chars
                     */
                    if (i == p.length())
                        return true;

                    nextPChar = p.charAt(i);

                }
                while (j < s.length() && (s.charAt(j) == pChar || s.charAt(j) == nextPChar || pChar == '.')) {
                    if (nextPChar != pChar && hasNextPChar && s.charAt(j) == nextPChar) {
                        break;
                    }

                    j++;
                }

                if (hasNextPChar) {
                    if (nextPChar == pChar)
                        j--;

                    if (j == s.length())
                        j--;

                } else if (j == s.length())
                    return true;

            } else if (currentMatch == false)
                return false;
            else
                i++;

        }

        return j == s.length() && i == p.length() ? true : false;

    }

    /**
     * Buggy version on '.*c' for 'ab'
     * 
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchBuggy1(String s, String p) {
        // Analyzing Patter, find the must have
        StringBuffer subP = new StringBuffer();
        int pos = 0;
        for (int i = 0; i < p.length() && pos < s.length(); i++) {
            char c = p.charAt(i);
            int subL = subP.length();
            if (c == '.') {
                if ((pos + subL + 1) > s.length())
                    return false;
                if (!s.substring(pos, pos + subL).equals(subP.toString()))
                    return false;
                pos = pos + subL + 1;
                subP = new StringBuffer();
            } else if (c == '*') {
                if ((pos + subL) > s.length())
                    return false;
                if (subL == 0) {
                    // ".*c" This mapped to anything
                    if ((i + 1) == p.length()) {
                        return true;
                    } else {
                        while (pos < s.length() && s.charAt(pos) != p.charAt(i + 1)) {
                            pos++;
                        }

                        if (pos == s.length()) {
                            return false;
                        }

                        while (pos < s.length() && s.charAt(pos) == p.charAt(i + 1)) {
                            pos++;
                        }
                        continue;
                    }

                }
                if (!s.substring(pos, pos + subL - 1).equals(subP.substring(0, subL - 1)))
                    return false;
                pos = pos + subL - 1;
                // * can skip, so it mightbe the key of next pattern
                char charAt = subP.charAt(subL - 1);
                while (pos < s.length() && s.charAt(pos) == charAt)
                    pos++;
                if ((i + 1) < p.length() && p.charAt(i + 1) == charAt)
                    pos = pos - 1;

                subP = new StringBuffer();
            } else if (i == p.length() - 1) {
                subP = subP.append(c);
                // match the length and end
                if (s.substring(pos).equals(subP.toString()))
                    return true;
                else
                    return false;
            } else // Still need match the end str
                subP = subP.append(c);
        }

        // pos < s.length, false

        return pos < s.length() ? false : true;

    }

}