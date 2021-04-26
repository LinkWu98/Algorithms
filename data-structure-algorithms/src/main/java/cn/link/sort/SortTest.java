package cn.link.sort;

import cn.link.common.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
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

    }

}
