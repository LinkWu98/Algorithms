package cn.link.sort;

/**
 * 选择排序
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/25 11:47
 */
public class SelectSorting {

    /**
     * 两两比较，小的就与前面的交换，继续比较
     * @param arr
     */
    public static void sort(int[] arr) {

        //外部循环定义被比较的对象
        for (int i = 0; i < arr.length - 1; i++) {

            //内部循环定义比较的对象
            for (int j = i + 1; j < arr.length; j++) {

                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }

        }

    }

}
