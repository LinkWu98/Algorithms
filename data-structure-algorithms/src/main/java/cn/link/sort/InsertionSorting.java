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
public class InsertionSorting {

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

    /**
     * 希尔排序
     * <p>
     * 如果来了个最小的数，普通的插入排序也需要一个个往前找，效率太低，因此就有了希尔排序：
     * <p>
     * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
     * <p>
     * 我一开始的思路：以为每组都要排好序，但其实还是只把最小的放第一个 TODO 还是要排序啊
     * 核心思想：尽量将小的元素前置，大的元素后置，然后在一个相对有序的情况下再进行一次插入排序即可
     *
     * @param arr
     * @param prevGap 步长，第一次是数组的长度
     */
    public static int[] shellSort(int[] arr, int prevGap) {

        //1.计算当前步长
        int currentGap = prevGap / 2;

        //步长为 1，最后一次插入排序即可
        if (currentGap == 1) {
            return sort(arr);
        }

        //外部循环遍历每组最前的元素
        for (int i = 0; i < currentGap; i++) {
            //内部循环遍历每组步长的元素
            for (int j = i + currentGap; j < arr.length; j += currentGap) {
                //小于就交换到前面去
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("排序后:");
        ArrayUtil.printArray(arr);

        return shellSort(arr, currentGap);

    }

}
