package com.example.restapik8s.interviewPrep;

import java.util.HashMap;
import java.util.List;

public class LengthOfLongestSubString {

    public static int lengthOfLongestSubstring(String str) {

        int left = 0;
        int maxLen = 0;
        HashMap<Character, Integer> lastSeen = new HashMap<>();

        for (int right = 0; right < str.length(); right++) {
            Character ch = str.charAt(right);

            if (lastSeen.containsKey(ch) && lastSeen.get(ch) >= left)
                left = lastSeen.get(ch) + 1;
            lastSeen.put(ch, right);
            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;
    }

    public static void main(String[] args) {
       int total = 1 << 3;
       System.out.println(total);
    }
}
