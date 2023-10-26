package cn.link.leetcode.二分查找;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * nums 为 无重复元素 的 升序 排列数组
 * <p>
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 */
public class 搜索插入位置 {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        //边界很重要，往左往右移动时，start可以等于end，此时是最后一轮比较
        while (start <= end) {
            mid = start + ((end - start) / 2);
            //主要就是控制边界移动，比target大往左、比target小往右移动
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                //target存在的情况
                return mid;
            }
        }
        //target不存在的情况，start不会小于0，最大也就length+1；因为二分里经过比较，找的位置就是它应该在的位置
        return start;
    }

}
