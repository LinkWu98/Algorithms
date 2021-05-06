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

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        BubbleSort.sort(arr);
        ArrayUtil.printArray(arr);

        int[] arr2 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        SelectSort.sort(arr2);
        ArrayUtil.printArray(arr2);

        int[] arr3 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
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

        int[] arr7 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        MergeSort.sort(arr7);
        ArrayUtil.printArray(arr7);
        
        int[] arr8 = {18, 29, 111, 2222, 412, 13, 5, 4, 6, 0};
        RadixSort.sort(arr8);
        ArrayUtil.printArray(arr8);

    }


    @Test
    public void test() {


    }


}
