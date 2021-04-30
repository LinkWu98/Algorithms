package cn.link.sort;

import cn.link.common.ArrayUtil;

import java.util.ArrayList;

/**
 * 插入排序
 * <p>
 * 插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/26 14:43
 */
public class InsertionSort {

    /**
     * 复杂度 O(n^2)
     * <p>
     * 从右往左遍历去比较，如果小于当前，当前直接右移，因此可以一边比较一边右移元素
     * （如果从左往右，比较和移动元素的动作只能分离，一次性移动完成）
     *
     * @param arr
     */
    public static int[] sort(int[] arr) {

        //一个原数组（无序），一个有序数组
        int[] orderedArr = new int[arr.length];

        //第一个先放进去
        orderedArr[0] = arr[0];
        int currentSize = 1;
        for (int i = 1; i < arr.length; i++) {

            //currentSize 也可以用 i 表示，但目前更好理解一点
            int currentOffset = currentSize;
            //从最后一个元素开始比较，大于就直接退出
            while (currentOffset > 0 && orderedArr[currentOffset - 1] > arr[i]) {
                //比它小，当前元素后移
                orderedArr[currentOffset] = orderedArr[currentOffset - 1];
                currentOffset--;
            }

            orderedArr[currentOffset] = arr[i];
            currentSize++;

        }

        return orderedArr;

    }

}
