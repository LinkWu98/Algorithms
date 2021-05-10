package cn.link.search;

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
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {

            arr[i] = arr[i - 1] + arr[i - 2];

        }

        return arr;

    }

    public static int search(int[] arr, int searchNum) {

        //偷懒随便初始化一个指定长度的
        int[] f = initFibonacciArr(arr.length);
        int totalLength = arr.length;
        int fibIdx = 0;
        while (totalLength > f[fibIdx]) {
            fibIdx++;
        }

        int low = 0;
        int high = arr.length - 1;

        //mid = low + f(n - 1)
        int mid = low + f[fibIdx - 1];
        while (arr[mid] != searchNum) {

            if (searchNum > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            if (low > high) {
                return -1;
            }

            fibIdx = 0;
            while ((high - low + 1) > f[fibIdx]) {
                fibIdx++;
            }
            //mid = fibIdx == 0 ? low : low + f[fibIdx - 1] - 1; 初始化斐波那契数列
            mid = low + f[fibIdx - 1];

        }

        return mid;

    }

}
