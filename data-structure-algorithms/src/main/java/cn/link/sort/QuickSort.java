package cn.link.sort;

/**
 * 快速排序
 * <p>
 * 核心思想：
 * 基于pivot(中轴数)，交换比较左右的数，交叉移动下标，最后能确认下pivot的有序下标位置
 * pivot的左右边也重复此动作，直到左右边length<=1，则数组有序
 *
 * @author link
 * @version 1.0
 * @date 2021/5/1 12:53 上午
 */
public class QuickSort {

    /**
     * @param arr
     * @param left
     * @param right
     */
    public static void sort(int[] arr, int left, int right) {

        //为了方便，取最左边为pivot
        int pivot = arr[left];
        int currentLeft = left;
        int currentRight = right;

        //先从右边开始，左右交替比较，结束条件：left = right，则是该pivot的下标
        while (currentLeft < currentRight) {

            //<=右边,左移下标继续比较
            while (currentLeft != currentRight && pivot <= arr[currentRight]) {
                currentRight--;
            }

            //< pivot,到左边来
            arr[currentLeft] = arr[currentRight];

            //>=左边,右移下标继续比较
            while (currentLeft != currentRight && pivot >= arr[currentLeft]) {
                currentLeft++;
            }

            //> pivot，到右边去
            arr[currentRight] = arr[currentLeft];

        }

        //最终 left == right,也就是pivot有序时的下标
        arr[currentLeft] = pivot;

        //若left / right的元素超过1个，重复上述步骤
        if (currentLeft - left > 1) {
            //同一边的边下标是不变的，但是另一边的边界会随pivot的变化而变化
            sort(arr, left, currentLeft - 1);
        }

        if (right - currentRight > 1) {
            sort(arr, currentRight + 1, right);
        }

    }

}
