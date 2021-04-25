package cn.link.sort;

/**
 * 冒泡排序
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/25 10:39
 */
public class BubbleSorting {

    /**
     * 两两比较，把大的数往后放
     * <p>
     * 优化：如果在边界到来前，一次循环中一次也没交换过，则有序，退出循环
     *
     * @param arr
     * @return
     */
    public static void sort(int[] arr) {

        //外部循环控制下标的边界
        for (int i = arr.length - 1; i > 0; i--) {

            boolean swapFlag = false;

            //内部循环两两下标取值比较
            for (int j = 0; j < i; j++) {

                //把大的数往后放
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapFlag = true;
                }

                //优化：一次也没交换过，则有序，直接返回
                if (j == i - 1 && !swapFlag) {
                    return;
                }

            }

        }

    }

}
