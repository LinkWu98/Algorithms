package cn.link.sort;

/**
 * 快速排序
 *
 * @author link
 * @version 1.0
 * @date 2021/5/1 12:53 上午
 */
public class QuickSort {

    /**
     * 不交换，而是改一个下标就行
     *
     * 核心思想：
     * 基于pivot(中轴数)，找它的有序下标
     * 左右的数轮流与pivot比较，交换后换边继续比较，直到left=right时，就是pivot的有序下标位置
     * pivot的左右边也重复此动作，直到左右边length<=1，则数组有序
     *
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

    /**
     * 基于交换
     *
     * 老师的做法：
     * 1. 从中间拿中轴数，左右分别往中间找不符合规律的，找到就停止寻找
     * 2. 停止寻找后，如果lr不相等，就交换，继续寻找，交换
     * 3. 当lr相等，就退出寻找，说明此次pivot下标已找到，开始寻找pivot左右的中轴数对应下标
     * 4. 重复上述步骤
     *
     * @param left
     * @param right
     * @param arr
     */
    public static void sortByMiddlePivot(int left, int right, int[] arr) {

        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        //比 pivot 小的放到左边，比pivot大的放到右边
        while (l < r) {
            // 在pivot的左边直到找到一个大于等于pivot的数，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边直到找到一个小于等于pivot的数，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 如果 l>=r 说明pivot 的左右两边已按照规律，pivot下标已确定，退出此次循环
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果换了中轴数的位置，中轴往哪边换，另一边要移一位
            if (arr[l] == pivot) {
                r -= 1;
            } else {
                l += 1;
            }

        }
        // 如果 l==r，说明中轴数的下标已找到，可以排中轴左右的数了，因此让 l++， r--
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            sortByMiddlePivot(left, r, arr);
        }
        // 向右递归
        if (right > l) {
            sortByMiddlePivot(l, right, arr);
        }
    }


}
