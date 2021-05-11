package cn.link.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 * <p>
 * 1 1 2 3 5
 * <p>
 * 用黄金分割法将数组分成两部分f(k) = f(k - 1) + f(k - 2)
 * 由此公式得出 f(k) - 1 = f(k - 1) - 1 + f(k - 2) - 1 + 1
 * <p>
 * mid = low + f(k - 1) - 1
 *
 * @author link
 * @version 1.0
 * @date 2021/5/10 11:19 下午
 */
public class FibonacciSearch {

    /**
     * 初始化一个指定长度的斐波那契数列
     *
     * @param length
     * @return
     */
    public static int[] initFibonacciArr(int length) {

        int[] arr = new int[length];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr;

    }

    /**
     * 大致原理：
     * 总长 : f[n] = f[n - 1] + f[n - 2]
     * mid = f[n - 1] - 1(数组下标需要长度 - 1)
     * 以此来计算mid
     *
     * @param arr
     * @param searchNum
     * @returnd
     */
    public static int search(int[] arr, int searchNum) {

        //偷懒随便初始化一个指定长度的
        int[] f = initFibonacciArr(arr.length);
        int fIndex = 0;
        while (arr.length > f[fIndex]) {
            fIndex++;
        }

        //由于会有长度超过原数组的情况，初始化一个长度一致的数组，原数组长度后的用最后一个元素补足，防止下标越界
        int[] temp = Arrays.copyOf(arr, f[fIndex]);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[arr.length - 1];
        }

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {

            //到边界时，没法分割了，low = mid
            int mid = fIndex == 0 ? low : low + f[fIndex - 1] - 1;
            if (temp[mid] > searchNum) {
                //此时总长 = f[n-1], mid在 low + f[n-1-1] - 1
                high = mid - 1;
                fIndex--;
            } else if (temp[mid] < searchNum) {
                //此时总长 = f[n-2], mid在 low + f[n - 2 - 1] - 1
                low = mid + 1;
                fIndex -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    //虽然符合了，但是超过边界，返回边界即可
                    return arr.length - 1;
                }
            }

        }

        return -1;

    }
}