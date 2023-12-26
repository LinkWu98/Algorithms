package cn.link.leetcode.多指针;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案
 */
public class 两数之和 {
    /**
     * 双指针，一次循环内左右开弓，分别在map内寻找对应的target数值，找不到就把当前的put进map，继续下一个数的寻找，循环结束的标识是左右相遇
     */
    public static int[] twoSum(int[] nums, int target) {
        int leftIdx = 0;
        int rightIdx = nums.length - 1;
        Map<Integer, Integer> numIdxMap = new HashMap<>();
        while (leftIdx <= rightIdx) {
            int lTarget = target - nums[leftIdx];
            if (numIdxMap.get(lTarget) == null) {
                numIdxMap.put(nums[leftIdx], leftIdx++);
            } else {
                return new int[]{leftIdx, numIdxMap.get(lTarget)};
            }

            int rTarget = target - nums[rightIdx];
            if (numIdxMap.get(rTarget) == null) {
                numIdxMap.put(nums[rightIdx], rightIdx--);
            } else {
                return new int[]{rightIdx, numIdxMap.get(rTarget)};
            }
        }

        return new int[2];
    }

    /**
     * 单指针简单解法
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] returnArr = new int[2];
        Map<Integer, Integer> numIdxMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int currentNum = nums[i];
            int findNum = target - currentNum;
            Integer findIdx = numIdxMap.get(findNum);
            if (findIdx == null){
                numIdxMap.put(currentNum, i);
                continue;
            }
            returnArr[0] = i;
            returnArr[1] = findIdx;
            break;
        }
        return returnArr;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        System.out.println(Arrays.toString(twoSum2(nums, 9)));
    }
}
