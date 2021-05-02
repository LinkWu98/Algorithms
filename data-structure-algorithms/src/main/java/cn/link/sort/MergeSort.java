package cn.link.sort;

/**
 * 归并排序
 * <p>
 * 归并排序就是不断两两合并两个有序序列的操作
 * <p>
 * 该算法采用经典的分治（divide-and-conquer）策略
 * 分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之。
 *
 * @author link
 * @version 1.0
 * @date 2021/5/2 11:21 上午
 */
public class MergeSort {

    public static void sort(int[] arr) {

        divideAndMerge(arr, 0, arr.length - 1);

    }

    /**
     * 分解然后合并
     * <p>
     * 核心思路:
     * 递归分解到最小单位不能再分解为止，就是程序的出口
     * 然后开始合并、排序，一步步回来
     *
     * @param arr
     * @param start
     * @param end
     */
    private static void divideAndMerge(int[] arr, int start, int end) {

        if (start < end) {

            int mid = (end + start) / 2;

            //递归向左分解
            divideAndMerge(arr, start, mid);

            //递归向右分解
            divideAndMerge(arr, mid + 1, end);

            merge(arr, start, mid, end);

        }

    }


    /**
     * 合并两个数组并排序
     *
     * @param arr   源数组
     * @param start 前一个数组的第一个下标
     * @param mid   前一个数组的最后一个下标
     * @param end   后二个数组的最后一个下标
     */
    private static void merge(int[] arr, int start, int mid, int end) {

        //用于储存排好序的临时数组
        int[] temp = new int[end - start + 1];
        int currentTempIdx = 0;
        int currentLeftIdx = start;
        int currentRightIdx = mid + 1;

        //1. 比较两个数组，小那一边先放到临时数组，下标后移继续比较，直到一边数组都放完
        while (currentLeftIdx <= mid && currentRightIdx <= end) {

            //左 <= 右，左放临时数组
            if (arr[currentLeftIdx] <= arr[currentRightIdx]) {
                temp[currentTempIdx++] = arr[currentLeftIdx++];
                continue;
            }

            //左 > 右，右放临时数组
            temp[currentTempIdx++] = arr[currentRightIdx++];

        }

        //2. 将其中一边剩下的都放到临时数组
        if (currentLeftIdx <= mid) {
            //说明左边还有比右边最大的元素还大的没放，现在放进去
            while (currentLeftIdx <= mid) {
                temp[currentTempIdx++] = arr[currentLeftIdx++];
            }
        } else {
            //说明右边还有比左边最大的元素还大的没放，现在放进去
            while (currentRightIdx <= end) {
                temp[currentTempIdx++] = arr[currentRightIdx++];
            }
        }

        //3. 最后把临时数组赋值给对应位置的源数组
        for (int i = 0; i < temp.length; i++) {

            arr[start + i] = temp[i];

        }

    }

}
