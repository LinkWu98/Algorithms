package cn.link.sort;

import cn.link.common.ArrayUtil;
import org.junit.Test;

/**
 * @author Link50
 * @version 1.0
 * @date 2021/4/25 11:13
 */
public class SortTest {

    @Test
    public void testBubbleSorting() {

        int[] arr = {2, 1, 4, 3};
        BubbleSort.sort(arr);
        ArrayUtil.printArray(arr);

        int[] arr2 = {2, 1, 3, 4};
        SelectSort.sort(arr2);
        ArrayUtil.printArray(arr2);

        int[] arr3 = {2, 1, 4, 3};
        ArrayUtil.printArray(InsertionSort.sort(arr3));

        int[] arr4 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        ShellSort.swapSort(arr4);
        ArrayUtil.printArray(arr4);

        int[] arr5 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        ShellSort.moveSort(arr5);
        ArrayUtil.printArray(arr5);

        int[] arr6 = {1, 7, 2, 3, 5, 4, 6, 0, 8, 9};
        QuickSort.sort(arr6, 0, arr6.length - 1);
        ArrayUtil.printArray(arr6);

    }

}
