package cn.link.leetcode.二分查找;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **中位数** 。
 */
public class 寻找两个正序数组的中位数 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1.length + nums2.length) % 2 == 1) {
            //奇数
            int n = (nums1.length + nums2.length) / 2 + 1;
            return binarySearchN(nums1, nums2, n);
        } else {
            //偶数
            int n1 = (nums1.length + nums2.length) / 2;
            int n2 = (nums1.length + nums2.length) / 2 + 1;
            //有小数的情况，所以除以 2.0
            return (binarySearchN(nums1, nums2, n1) + binarySearchN(nums1, nums2, n2)) / 2.0;
        }
    }

    /**
     * 两个有序数组中寻找第n个数
     */
    public int binarySearchN(int[] nums1, int[] nums2, int n) {
        int start1 = 0;
        int start2 = 0;
        while (true) {
            //二分法，以寻找第n个为基础，每次比较两个数组中第n/2位置的数，也就是一次排除
            int mid = n / 2;
            //比较后，如果一边数组内的元素都被排除了，返回另一边对应的n个数
            if (start1 == nums1.length) return nums2[start2 + n - 1];
            if (start2 == nums2.length) return nums1[start1 + n - 1];
            //多轮比较后最终寻找最后一个数，小的那个就是
            if (n == 1) return Math.min(nums1[start1], nums2[start2]);
            //计算此次比较的位置，（第一次超过最大长度）如果二分后比较的位置超过最大长度，以最大长度为准
            int currentIdx1 = Math.min(start1 + mid - 1, nums1.length - 1);
            int currentIdx2 = Math.min(start2 + mid - 1, nums2.length - 1);
            //大于另一边，另一边start后移一位（也就是start之前的都已排除）
            if (nums1[currentIdx1] > nums2[currentIdx2]) {
                n -= currentIdx2 - start2 + 1;
                start2 = currentIdx2 + 1;
            } else {
                n -= currentIdx1 - start1 + 1;
                start1 = currentIdx1 + 1;
            }
        }
    }

    @Test
    public void test(){
        寻找两个正序数组的中位数 a = new 寻找两个正序数组的中位数();
        System.out.println(a.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        //System.out.println();
        //System.out.println((a.binarySearchN(new int[]{1, 2}, new int[]{3, 4}, 3) + a.binarySearchN(new int[]{1, 2}, new int[]{3, 4}, 2)) /2);
    }
}
