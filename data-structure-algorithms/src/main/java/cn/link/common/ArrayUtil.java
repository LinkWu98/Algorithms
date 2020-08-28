package cn.link.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数组、集合工具类
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/26 17:17
 */
public class ArrayUtil {

    /**
     * 定义一个的稀疏数组
     *
     * @param totalRow 总行数
     * @param totalCol 总列数
     * @param validNum 有效元素个数
     * @param details  记录元素位置和元素的集合 (字符串逗号分隔行、列、值)
     * @return sparseArr
     */
    public static int[][] defineSparseArr(int totalRow, int totalCol, int validNum, List<String> details) {

        if (totalRow == 0 || totalCol == 0 || validNum == 0 || CollectionUtils.isEmpty(details)) {
            return null;
        }

        /*
            行 : 二维数组的有效元素个数 + 1(多一行记录二维数组的总行、列和有效元素个数)
            列 : 3 (记录二维数组的:行、列、值)
        */
        int[][] sparseArr = new int[validNum + 1][3];

        //第一行
        sparseArr[0][0] = totalRow;
        sparseArr[0][1] = totalCol;
        sparseArr[0][2] = validNum;

        //从第二行开始遍历
        for (int i = 1; i < sparseArr.length; i++) {

            if (StringUtils.isBlank(details.get(i - 1))) {
                continue;
            }

            String detail = details.get(i - 1);
            List<String> splitDetailList = Arrays.asList(detail.split(","));
            sparseArr[i][0] = Integer.parseInt(splitDetailList.get(0));
            sparseArr[i][1] = Integer.parseInt(splitDetailList.get(1));
            sparseArr[i][2] = Integer.parseInt(splitDetailList.get(2));

        }

        return sparseArr;

    }

    /**
     * 二维数组转稀疏数组
     *
     * @param secondDimensionArr 二维数组
     * @param invalidVal         无效元素
     * @return 稀疏数组
     */
    public static int[][] convert2dArrToSparseArr(int[][] secondDimensionArr, int invalidVal) {

        //记录二维数组的:行、列
        int totalRow = secondDimensionArr.length;
        int totalCol = secondDimensionArr[0].length;
        //记录有效值个数
        int validNum = 0;
        //记录有效值的集合
        List<String> details = new ArrayList<>();

        //遍历二维数组，开始记录有效值
        for (int i = 0; i < secondDimensionArr.length; i++) {

            for (int j = 0; j < secondDimensionArr[i].length; j++) {

                if (secondDimensionArr[i][j] != invalidVal) {

                    //有效值个数++
                    validNum++;
                    //记录有效值的行、列、值
                    details.add("" + i + "," + j + "," + secondDimensionArr[i][j]);

                }

            }

        }

        return defineSparseArr(totalRow, totalCol, validNum, details);

    }

    /**
     * 稀疏数组转二维数组
     *
     * @param sparseArr  稀疏数组
     * @param invalidVal 无效值
     * @return 二维数组
     */
    public static int[][] convertSparseArrTo2dArr(int[][] sparseArr, int invalidVal) {

        if (sparseArr == null || sparseArr.length <= 1) {
            return null;
        }

        //通过稀疏数组第一行的元素，定义二维数组的行、列
        int totalRow = sparseArr[0][0];
        int totalCol = sparseArr[0][1];
        int[][] twoDimensionArr = new int[totalRow][totalCol];

        //从第二行开始遍历，给二维数组赋有效值
        for (int i = 1; i < sparseArr.length; i++) {

            int row = sparseArr[i][0];
            int col = sparseArr[i][1];
            int val = sparseArr[i][2];
            twoDimensionArr[row][col] = val;

        }

        //二维数组其余元素赋无效值
        for (int i = 0; i < twoDimensionArr.length; i++) {

            for (int j = 0; j < twoDimensionArr[i].length; j++) {

                //0 是O未初始化的默认值，即未赋值
                if (twoDimensionArr[i][j] == 0) {
                    twoDimensionArr[i][j] = invalidVal;
                }

            }

        }

        return twoDimensionArr;

    }

    /**
     * 打印二维数组
     *
     * @param arr 二维数组
     */
    public static void printSecondDimensionArr(int[][] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }

        System.out.println("==========SecondDimensionArr==========");

        for (int[] innerArr : arr) {

            for (int j = 0; j < innerArr.length; j++) {

                if (j == 0) {
                    System.out.print("[" + innerArr[j]);
                    continue;
                }

                if (j == innerArr.length - 1) {
                    System.out.print("," + innerArr[j] + "]");
                    continue;
                }

                System.out.print("," + innerArr[j]);

            }

            System.out.println();

        }

    }

    public static void writeArr2File(int[][] arr, String filepath){

        try {

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filepath));

            out.close();

        }catch (Exception e) {
            e.printStackTrace();

        }


    }

}
