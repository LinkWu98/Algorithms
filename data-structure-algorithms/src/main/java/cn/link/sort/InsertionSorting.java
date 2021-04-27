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
     * 核心思想：分组，每组排序，每组有序后，整体就相对有序了，然后最后一次效率就很高了
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {

        //最外部循环：计算每一轮的gap
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //外部循环：从第一组的第二个gap元素开始,然后依次遍历到第n组的第n个元素
            for (int i = gap; i < arr.length; i++) {
                /*
                    内部循环：从第n组的第n个元素往前推所有当前组的gap元素，进行比较并替换，
                            随着外部循环的增加，内部循环一组比较的元素会越来越多
                 */
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }

            System.out.println("一轮排序后:");
            ArrayUtil.printArray(arr);

        }

    }

}
