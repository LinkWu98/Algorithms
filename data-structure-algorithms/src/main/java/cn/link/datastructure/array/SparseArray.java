package cn.link.datastructure.array;

import cn.link.common.ArrayUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * 稀疏数组
 * <p>
 * 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/26 16:43
 */
public class SparseArray {

    /**
     * 定义一个 10 * 10 的二维数组
     */
    public static final int[][] SECOND_DIMENSION_ARRAY = new int[10][10];

    /**
     * 定义稀疏数组
     * 行 : 二维数组的长度 + 1(多一行记录二维数组的总行、列和有效元素个数)
     * 列 : 3 (记录二维数组的:行、列、值)
     */
    public static final int[][] SPARSE_ARRAY = new int[SECOND_DIMENSION_ARRAY.length + 1][3];

    /**
     * 定义无效值
     */
    public static final int INVALID_VAL = 0;

    /**
     * 二维 -> 稀疏
     */
    @Test
    public void secondDimensionArray2SparseArray() {

        //1. 第一行数组，记录二维数组的:行、列、值
        SPARSE_ARRAY[0][1] = SECOND_DIMENSION_ARRAY.length;
        SPARSE_ARRAY[0][2] = SECOND_DIMENSION_ARRAY[0].length;
        int validVal = 0;
        //行数从 1 开始
        int sparseArrayRow = 1;

        for (int i = 0; i < SECOND_DIMENSION_ARRAY.length; i++) {

            for (int j = 0; j < SECOND_DIMENSION_ARRAY[i].length; j++) {

                if (SECOND_DIMENSION_ARRAY[i][j] != INVALID_VAL) {
                    //有效值个数++
                    validVal++;
                    //记录有效值的行、列、值
                    SPARSE_ARRAY[sparseArrayRow][0] = i;
                    SPARSE_ARRAY[sparseArrayRow][1] = j;
                    SPARSE_ARRAY[sparseArrayRow][2] = SECOND_DIMENSION_ARRAY[i][j];
                    sparseArrayRow++;
                }

            }

        }

        SPARSE_ARRAY[0][3] = validVal;

        ArrayUtil.printSecondDimensionArray(SPARSE_ARRAY);

    }

    /**
     * 初始化一个 2/3 概率值为 INVALID_VAL (无效值)的二维数组
     */
    @Before
    public void initSecondDimensionArray() {

        Random random = new Random();

        for (int i = 0; i < SECOND_DIMENSION_ARRAY.length; i++) {

            for (int j = 0; j < SECOND_DIMENSION_ARRAY[i].length; j++) {

                //正常值
                if (random.nextInt(2) == 0) {

                    //随机给一个 1 ~ 10 的数字作为元素
                    SECOND_DIMENSION_ARRAY[i][j] = random.nextInt(9) + 1;

                } else {

                    //无效值
                    SECOND_DIMENSION_ARRAY[i][j] = INVALID_VAL;

                }

            }

        }

    }

}
