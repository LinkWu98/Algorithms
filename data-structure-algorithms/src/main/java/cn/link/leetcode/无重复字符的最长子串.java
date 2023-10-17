package cn.link.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class 无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        int longestLength = 0;
        int start = 0;
        char[] charArray = s.toCharArray();
        //value记录下标，用于重复时start往后一格
        Map<Character, Integer> charIdxMap = new HashMap<>();
        for (int end = 0; end < charArray.length; end++) {
            Integer existCharIdx = charIdxMap.get(charArray[end]);
            if (existCharIdx != null && existCharIdx >= start) {
                //滑动窗口，遇到重复的，start=原有的重复的那个元素位置后移一个
                start = existCharIdx + 1;
            }
            charIdxMap.put(charArray[end], end);
            longestLength = Math.max(longestLength, end - start + 1);
        }
        return longestLength;
    }
}