package cn.link.exercise;

import org.junit.Test;

/**
 * @author Link50
 * @version 1.0
 * @date 2021/2/22 11:37
 */
public class ExerciseTest {

    /**
     * 约瑟夫问题
     */
    @Test
    public void josephTest() throws Exception {

        Joseph joseph = new Joseph();

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        Integer[] arr2 = new Integer[]{1, 2, 3, 4, 5, 6, 7};

        //数组解决
        System.out.println(joseph.getDequeueSequenceByArray(arr, 1, 3));
        //双向链表解决
        System.out.println(joseph.getDequeueSequenceByDoublyLinkedList(arr2, 1, 3));

    }

    /**
     * 栈计算器测试
     */
    @Test
    public void calculatorByStackTest(){

        CalculatorByStack calculator = new CalculatorByStack();

        System.out.println(calculator.calculate("2+3/2*2+1*2"));

    }

}
