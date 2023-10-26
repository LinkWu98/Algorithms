package cn.link.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找
 * <p>
 * 复杂度O(log2n)
 *
 * @author link
 * @version 1.0
 * @date 2021/5/6 9:33 下午
 */
public class BinarySearch {

    /**
     * 在指定的范围内进行二分查找，找到一个就结束，返回下标
     *
     * @param arr       有序数组
     * @param searchNum
     * @return
     */
    public static int search(int[] arr, int start, int end, int searchNum) {

        int mid = (start + end) / 2;
        //这里不用递归，while里操作
        while (arr[mid] != searchNum) {

            //小于中间数，继续往左查找
            if (arr[mid] > searchNum && mid > start) {
                mid = (start + mid - 1) / 2;
            } else if (arr[mid] < searchNum && mid < end) {
                //大于，往右查找
                mid = (mid + 1 + end) / 2;
            } else {
                return -1;
            }

        }

        return mid;

    }

    /**
     * 查找所有的数，返回它们的下标
     *
     * @param arr       有序数组
     * @param searchNum
     * @return
     */
    public static List<Integer> searchAll(int[] arr, int searchNum) {

        int currentIdx = 0;
        int searchNumIdx = -1;
        List<Integer> searchNumIdxList = new ArrayList<>();

        //先二分找一个，后续的因为是有序数组所以相邻，左右找一下就行
        if ((searchNumIdx = search(arr, 0, arr.length - 1, searchNum)) != -1) {

            int leftSearchNumIdx = searchNumIdx;
            //往左往右找就行
            while (leftSearchNumIdx - 1 > 0 && arr[--leftSearchNumIdx] == searchNum) {
                searchNumIdxList.add(leftSearchNumIdx);
            }

            searchNumIdxList.add(searchNumIdx);

            int rightSearchNumIdx = searchNumIdx;
            //往左往右找就行,然后放入数组就行
            while (rightSearchNumIdx + 1 < arr.length - 1 && arr[++rightSearchNumIdx] == searchNum) {
                searchNumIdxList.add(rightSearchNumIdx);
            }

        }

        return searchNumIdxList;

    }

    /**
     * 看上去简单点，返回查找的下标
     */
    public int binarySearchV2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        //边界很重要，往左往右移动时，start可以等于end，此时是最后一轮比较
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            //主要就是控制边界移动，比target大往左、比target小往右移动
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
