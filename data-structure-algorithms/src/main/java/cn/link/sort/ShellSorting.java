package cn.link.sort;

import cn.link.common.ArrayUtil;

/**
 * 如果来了个最小的数，普通的插入排序也需要一个个往前找，效率太低，因此就有了希尔排序：
 * <p>
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。(所谓缩小增量就是，元素之间相对有序，无需比较太多次)
 * <p>
 * 核心思想：分组，每组排序，每组有序后，整体就相对有序了，不会出现一个很小的元素一个个往前交换的情况
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/28 11:58
 */
public class ShellSorting {

    /**
     * 基于移动的希尔排序，真正的希尔
     */
    public static void moveSort(int[] arr) {

        //外部循环：定义 gap
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //内部循环：倒序遍历每组元素，小于前者，前者往后移动
            for (int i = gap; i < arr.length; i++) {
                //记录下当前的元素、下标，以便于后续替换，往前递减步长
                int currentNum = arr[i];
                int currentIdx = i;
                while (currentIdx - gap >= 0 && arr[currentIdx - gap] > currentNum) {
                    //前者往后移动一个步长
                    arr[currentIdx] = arr[currentIdx - gap];
                    currentIdx -= gap;
                }
                if (currentIdx != i) {
                    arr[currentIdx] = currentNum;
                }
            }
        }

    }

    /**
     * 基于交换的希尔排序，效率还没普通插入高
     * <p>
     * 核心思想：计算步长分组，每组进行向前遍历交换排序
     *
     * @param arr
     */
    public static void swapSort(int[] arr) {

        //最外部循环：计算每一轮的gap
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //外部循环：从第一个gap开始(第一组的第二个元素)，往后就是下一组的第二元素，下下组的第二个元素，下一组的第n个元素
            for (int i = gap; i < arr.length; i++) {
                //内部循环：从右往左，比较每组内的元素并交换，最多遍历到每组的倒数第二个元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }

    }

}
