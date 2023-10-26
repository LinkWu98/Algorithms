package cn.link.test;

import cn.link.common.ArrayUtil;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 90762
 * @version 1.0
 * @date 2023/6/13 下午 2:29
 */
public class TestTemplate {

    public void bubbleSort(int[] arr) {}

    public void selectSort(int[] arr) {

    }

    public void insertionSort(int[] arr) {

    }

    public void shellSort(int[] arr) {

    }

    public void quickSort(int[] arr) {

    }

    @Test
    public void test() {
        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
        }
    }

    public int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void testBinarySearch(){
        System.out.println(binarySearch(new int[]{-3, 1, 3, 4, 5, 6, 9, 10}, 9));
    }

}
