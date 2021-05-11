package cn.link.search;

import cn.link.common.ArrayUtil;
import org.junit.Test;

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

}
