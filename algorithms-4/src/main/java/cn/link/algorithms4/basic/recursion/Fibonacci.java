package cn.link.algorithms4.basic.recursion;

import org.junit.Test;

/**
 * @Author: Link
 * @Date: 2020/7/24 11:07
 * @Version 1.0
 */
public class Fibonacci {

    @Test
    public void test() {

        int month = 10;

        System.out.println("第" + month + "个月的数量：" + fibonacci(month));

    }


    /**
     * Rabbit
     * @param month
     * @return
     */
    private int fibonacci(int month) {

        //第一个月和第二个月都是 1
        if (month == 1 || month == 2) {
            return 1;
        }

        //当前月数量 = 上个月的数量 + 上上个月的数量
        return fibonacci(month - 1) + fibonacci(month - 2);

    }

}
