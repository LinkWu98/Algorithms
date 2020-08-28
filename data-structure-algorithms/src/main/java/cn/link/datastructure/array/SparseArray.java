package cn.link.datastructure.array;

import cn.link.common.ArrayUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * 稀疏数组
 * <p>
 * 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
 * <p>
 * 行 : 二维数组的有效元素个数 + 1(多一行记录二维数组的总行、列和有效元素个数)
 * 列 : 3 (记录二维数组的:行、列、值)
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
     * 定义无效值 (在五子棋盘中 0 表示没放子)
     */
    public static final int INVALID_VAL = 0;

    /**
     * 输出文件路径
     */
    public static final String FILEPATH = "K://test.txt";

    /**
     * 二维 -> 稀疏
     */
    @Test
    public void secondDimensionArr2SparseArr() {

        int[][] sparseArr = ArrayUtil.convert2dArrToSparseArr(SECOND_DIMENSION_ARRAY, INVALID_VAL);

        ArrayUtil.printSecondDimensionArr(sparseArr);

    }

    /**
     * 稀疏 -> 二维
     */
    @Test
    public void sparseArr2secondDimensionArr() {

        int[][] sparseArr = ArrayUtil.convert2dArrToSparseArr(SECOND_DIMENSION_ARRAY, INVALID_VAL);
        ArrayUtil.printSecondDimensionArr(sparseArr);

        int[][] secondDimensionArr = ArrayUtil.convertSparseArrTo2dArr(sparseArr, INVALID_VAL);
        ArrayUtil.printSecondDimensionArr(secondDimensionArr);


    }

    /**
     * 将数组写入文件 ( 存档 )
     * 将文件中数组读出 ( 读档 )
     */
    @Test
    public void writeAndReadArr() throws Exception {

        ArrayUtil.write2dArrToFile(SECOND_DIMENSION_ARRAY, FILEPATH);

        int[][] arr = ArrayUtil.read2dArrFromFile(FILEPATH);

        ArrayUtil.printSecondDimensionArr(arr);

    }


    /**
     * 初始化一个 3/4 概率值是 无效值 的二维数组
     */
    @Before
    public void initSecondDimensionArray() {

        Random random = new Random();

        for (int i = 0; i < SECOND_DIMENSION_ARRAY.length; i++) {

            for (int j = 0; j < SECOND_DIMENSION_ARRAY[i].length; j++) {

                //正常值
                if (random.nextInt(3) == 0) {

                    //随机给一个 1 ~ 10 的数字作为元素
                    SECOND_DIMENSION_ARRAY[i][j] = random.nextInt(9) + 1;

                } else {

                    //无效值
                    SECOND_DIMENSION_ARRAY[i][j] = INVALID_VAL;

                }

            }

        }

        ArrayUtil.printSecondDimensionArr(SECOND_DIMENSION_ARRAY);

    }

}
