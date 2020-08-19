package cn.link.algorithms4.collection.datatype;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Author: Link
 * @Date: 2020/7/24 14:37
 * @Version 1.0
 */
public class StackTest {

    /*

        经典应用：

        (1 + (5 * (4 * 5))

        运算符栈 ( + ( * (  * ))
        操作数栈  1   5   4  5

        压栈时
            忽略左括号
            遇到右括号，弹出前面的运算符、弹出其对应的操作数，计算结果后压栈入之前的操作数

     */

    @Test
    public void test(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        System.out.println();

    }
}
