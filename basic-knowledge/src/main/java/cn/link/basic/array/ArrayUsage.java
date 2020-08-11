package cn.link.basic.array;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * 数组的处理使用
 *
 * @Author: Link
 * @Date: 2020/7/10 9:00
 * @Version 1.0
 */
public class ArrayUsage {

    private static int[] arr = {2, 1, 5, 7, 3};

    /**
     * 找出数组中最大的元素
     */
    @Test
    public void findMaxElementOfArray() {

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {

            if (max < arr[i]) {
                max = arr[i];
            }

        }

        System.out.println("数组中最大的元素：" + max);

    }

    /**
     * 计算数组的平均元素
     */
    @Test
    public void avgOfArray() {

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

        }

        System.out.println("数组的平均值：" + sum / arr.length);

    }

    /**
     * 复制数组
     */
    @Test
    public void arrayCopy() {

        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        System.out.println("数组的元素：" + Arrays.toString(newArr));

    }

    /**
     * 颠倒数组元素
     */
    @Test
    public void reverseArray() {

        for (int i = 0; i < arr.length / 2; i++) {

            int swap = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = swap;

        }
        System.out.println("数组的元素：" + Arrays.toString(arr));

    }


    /**
     * TODO 矩阵相乘
     */

    @Test
    public void test3() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        System.out.println(cal.getTime());
    }

    @Test
    public void test4() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        System.out.println(cal.getTime());
    }

    @Test
    public void test(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        System.out.println(calendar.getTime());
    }


}
