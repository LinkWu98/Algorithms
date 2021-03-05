package cn.link.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void josephTest(){

        Joseph joseph = new Joseph();

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

        System.out.println(joseph.getDequeueSequenceByArray(arr, 1, 2));

    }

}
