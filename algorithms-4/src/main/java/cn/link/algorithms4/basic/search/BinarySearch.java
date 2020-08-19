package cn.link.algorithms4.basic.search;

import org.junit.Test;

/**
 * 二分查找
 *
 * @Author: Link
 * @Date: 2020/7/10 9:33
 * @Version 1.0
 */
public class BinarySearch {

    private static int[] arr = {1, 3, 4, 7, 9, 10};

    /**
     * 常规的二分查找
     */
    @Test
    public void bs1() {

        int key = 3;

        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {

            int mid = (lo + hi) / 2;

            if (key < arr[mid]) {

                hi = mid - 1;
                continue;
            }

            if (key > arr[mid]) {

                lo = mid + 1;
                continue;

            }

            System.out.println("查找到的索引：" + mid);
            return;

        }

        System.out.println("该数字不存在");

    }

    /**
     * 递归的二分查找
     */
    @Test
    public void bs2() {

        int key = 9;

        int lo = 0;
        int hi = arr.length - 1;
        int mid = (lo + hi) / 2;

        if (arr[mid] == key) {

            System.out.println("该元素在数组中的索引为：" + mid);

        } else {

            System.out.println("该元素在数组中的索引为：" + doBs(lo, hi, mid, key));

        }

    }

    private static int doBs(int lo, int hi, int mid, int key) {

        //超出边界
        if (lo > hi) {
            return -1;
        }

        if (arr[mid] == key) {

            return mid;

        } else if (arr[mid] > key) {

            hi = mid - 1;

        } else {

            lo = mid + 1;

        }

        mid = (lo + hi) / 2;

        return doBs(lo, hi, mid, key);


    }
}
