package cn.link.basic.test;

import org.junit.Test;

import java.util.Random;

/**
 * @Author: Link
 * @Date: 2020/7/10 13:26
 * @Version 1.0
 */
public class BasicTest {

    /**
     * 错题记录
     */
    @Test
    public void test() {

        System.out.println((1 + 2.236) / 2);

    }

    /**
     * 将整数换位二进制再以String类型输出
     */
    @Test
    public void toBinaryString() {

        String s = "";

        int i = 10;

        for (int j = i; j > 0; j /= 2) {

            int b = j % 2;
            s += b;

        }

        System.out.println("二进制结果：" + s);

    }


    /**
     * 二维数组
     */
    @Test
    public void arrTest() {

        boolean[][] boolArr = new boolean[4][4];

        for (int i = 0; i < boolArr.length; i++) {

            for (int j = 0; j < boolArr[i].length; j++) {

                int nextInt = new Random().nextInt(2);
                boolArr[i][j] = (nextInt == 0);


            }

        }

        System.out.print(" ");
        for (int i = 0; i < boolArr.length; i++) {
            System.out.print(" " + (i + 1));
        }

        System.out.println();

        for (int i = 0; i < boolArr.length; i++) {

            System.out.print(i + 1);

            for (int j = 0; j < boolArr[i].length; j++) {

                if (boolArr[i][j]) {
                    System.out.print(" *");
                } else {
                    System.out.print("  ");
                }

            }

            System.out.println();

        }

    }


    /**
     * TODO 数组元素去重
     */
    @Test
    public void noRepTest() {

        int[] arr = {2, 1, 3, 1, 9, 7, 2};



    }

}
