package cn.link.sort;

/**
 * 基数排序(桶排序)
 *
 * <p>
 * 基数排序（radix sort） 属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）或bin sort
 * 顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
 * <p>
 *
 * 是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
 * 经典的空间换时间的方式，占用内存很大，当对海量数据排序时，容易造成 OutOfMemoryError
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/6 11:13
 */
public class RadixSort {

    /**
     * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。
     * 然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列
     *
     * 负数的基数排序，把负号去掉，排好后数组反转即可
     *
     * @param arr
     */
    public static void sort(int[] arr) {

        //初始化桶，用二维数组表示，10个桶，最多arr.length条数据
        int[][] bucket = new int[10][arr.length];
        //记录每个桶有效元素的个数，下标表示第几个桶，数据表示元素个数，这样就不用每次清空数组，只拿需要的就行了
        int[] radixValidCount = new int[10];

        //获取最大数及其最大位数
        int maxNum = 0;
        for (int num : arr) {
            if (maxNum < num) {
                maxNum = num;
            }
        }

        int maxRadix = 1;
        while (maxNum / 10 > 0) {
            maxNum /= 10;
            maxRadix++;
        }

        //0. 获取对应位数的数字，放到对应的桶中，最多maxRadix轮结束
        for (int currentRadix = 1, n = 1; currentRadix <= maxRadix; currentRadix++, n *= 10) {

            //外部每循环一次就是一轮
            for (int j = 0; j < arr.length; j++) {
                //1. 位数不够，放在0桶中
                if (arr[j] / n == 0) {
                    bucket[0][radixValidCount[0]++] = arr[j];
                    continue;
                }
                //1. 位数够，获取对应位数的数字
                int num = arr[j] / n % 10;
                //2. 放入对应的桶中，当前位上有效基数个数++
                bucket[num][radixValidCount[num]++] = arr[j];
            }

            int currentArrIdx = 0;
            //一轮结束，将桶中的数据按顺序放到数组中
            for (int num = 0; num < 10; num++) {
                //把当前位数的数字放到数组中
                for (int currentIdx = 0; currentIdx < radixValidCount[num]; currentIdx++) {
                    arr[currentArrIdx++] = bucket[num][currentIdx];
                }
                //有效个数清零
                radixValidCount[num] = 0;
            }

        }

    }

}