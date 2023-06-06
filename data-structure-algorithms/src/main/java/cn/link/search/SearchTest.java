package cn.link.search;

import cn.link.common.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author link
 * @version 1.0
 * @date 2021/5/6 9:23 下午
 */
public class SearchTest {

    @Test
    public void testSearch() {

        int[] arr = new int[]{0, 1, 1, 1, 4, 4, 6, 7, 8, 9};
        int i = SeqSearch.search(arr, 1);
        System.out.println(i);
        i = BinarySearch.search(arr, 0, arr.length - 1, 1);
        System.out.println(i);
        List<Integer> searchAll = BinarySearch.searchAll(arr, 4);
        System.out.println(searchAll);
        System.out.println(InsertValueSearch.search(arr, 6));
        int[] arr2 = new int[]{0, 1, 2, 3, 42, 51, 64, 71, 88, 90, 101, 102, 301};
        System.out.println(FibonacciSearch.search(arr2, 64));

    }


    public int[] shellSortTest(int[] arr) {

        //控制步长
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //按步长往前排序
                int currentIdx = i;
                int prevIdx;
                int currentNum = arr[i];
                while ((prevIdx = currentIdx - gap) >= 0 && currentNum < arr[prevIdx]) {
                    arr[currentIdx] = arr[prevIdx];
                    currentIdx -= gap;
                }
                arr[currentIdx] = currentNum;
            }
        }

        return arr;

    }

    @Test
    public void test() {
        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println(Arrays.toString(shellSortTest(arr)));
    }

}
