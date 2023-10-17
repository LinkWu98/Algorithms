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

}
