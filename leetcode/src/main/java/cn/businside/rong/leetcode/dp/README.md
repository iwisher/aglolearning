# Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

[Palindromic.java](Palindromic.java)

## Example 1:

> Input: "babad"
>
> Output: "bab"
>
> Note: "aba" is also a valid answer.

## Example 2:

> Input: "cbbd"
> 
> Output: "bb"


# Regular Expression Matching
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

>s could be empty and contains only lowercase letters a-z.
>p could be empty and contains only lowercase letters a-z, and characters like . or *.
## Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
## Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".