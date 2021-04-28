package cn.link.sort;

import cn.link.common.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/25 11:13
 */
public class SortTest {

    @Test
    public void testBubbleSorting(){

        int[] arr = {2, 1, 4, 3};
        BubbleSorting.sort(arr);
        ArrayUtil.printArray(arr);

        int[] arr2 = {2, 1, 3, 4};
        SelectSorting.sort(arr2);
        ArrayUtil.printArray(arr2);

        int[] arr3 = {2, 1, 4, 3};
        ArrayUtil.printArray(InsertionSorting.sort(arr3));

        int[] arr4 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        ShellSorting.swapSort(arr4);
        ArrayUtil.printArray(arr4);

        int[] arr5 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        ShellSorting.moveSort(arr5);
        ArrayUtil.printArray(arr5);

    }

}
