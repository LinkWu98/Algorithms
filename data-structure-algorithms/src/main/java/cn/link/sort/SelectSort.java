package cn.link.sort;

/**
 * 选择排序
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/25 11:47
 */
public class SelectSort {

    /**
     * 复杂度 O(n^2)
     *
     * 两两比较，小就记录下来，一轮全部比较完毕就是最小的，再与当前的交换（每轮最多只换一次）
     *
     * 正常情况下，选择排序相较于冒泡，虽然时间复杂度一致，但空间复杂度更低，无须频繁交换进行内存操作
     * 但若在比较有序的情况下，优化过的冒泡排序效率则更优
     *
     * @param arr
     */
    public static void sort(int[] arr) {

        //外部循环定义被比较的对象
        for (int i = 0; i < arr.length - 1; i++) {

            int min = arr[i];
            //记录最小值下标
            int minIdx = i;

            //内部循环定义比较的对象
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    //记录最小值
                    min = arr[j];
                    minIdx = j;
                }
            }

            //最小值下标非当前下标，交换（bug记录：原来只是替换，那原有元素就消失了）
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = min;
                arr[minIdx] = temp;
            }

        }

    }

}
