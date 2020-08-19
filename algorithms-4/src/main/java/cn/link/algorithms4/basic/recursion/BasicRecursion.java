package cn.link.algorithms4.basic.recursion;

import org.junit.Test;

/**
 * @Author: Link
 * @Date: 2020/7/24 10:29
 * @Version 1.0
 */
public class BasicRecursion {

    @Test
    public void test() {

        System.out.println(doRecursion(3, 11));

    }

    /**
     * 实际上就是看是否是奇数，是就 + a
     * @param a
     * @param b
     * @return
     */
    private int doRecursion(int a, int b) {

        if (b == 0) {
            return 0;
        }

        //b是偶数
        if (b % 2 == 0) {
            return doRecursion(a + a, b / 2);
        }

        //b是奇数
        return doRecursion(a + a, b / 2) + a;

    }

}
