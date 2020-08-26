package cn.link.common;

/**
 * 数组、集合工具类
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/26 17:17
 */
public class ArrayUtil {

    /**
     * 打印二维数组
     * @param arr
     */
    public static void printSecondDimensionArray(int[][] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }

        for (int[] innerArr : arr) {

            for (int j = 0; j < innerArr.length; j++) {

                if (j == 0) {
                    System.out.print("[" + innerArr[j]);
                    continue;
                }

                if (j == innerArr.length - 1) {
                    System.out.print("," + innerArr[j] + "]");
                }

                System.out.print("," + innerArr[j]);

            }

            System.out.println();

        }

    }

}
