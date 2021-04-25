package cn.link.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        //从数组的第二行开始赋值
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

        //二维数组其余未初始化的元素赋无效值
        for (int i = 0; i < twoDimensionArr.length; i++) {

            for (int j = 0; j < twoDimensionArr[i].length; j++) {

                //0 是未初始化的默认值，即未赋值
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

    /**
     * 将二维数组写入文件 TODO test
     *
     * @param arr      二维数组
     * @param filePath 文件路径
     */
    public static void writeArr2File(int[][] arr, String filePath) {

        BufferedWriter writer = null;

        try {

            writer = new BufferedWriter(new FileWriter(filePath));
            StringBuilder sb = new StringBuilder();

            //将二维数组转为 String 字符串存入文本中
            for (int[] row : arr) {

                for (int j = 0; j < row.length; j++) {

                    int col = row[j];
                    sb.append(col);
                    //最后一行不加逗号
                    if (j != row.length - 1) {
                        sb.append(",");
                    }

                }

                //每行末尾的换行符 (根据操作系统的不同换行符也不同 )
                sb.append(System.getProperty("line.separator"));

            }

            writer.write(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 读取保存在文件中的二维数组
     *
     * @param filePath 文件路径
     * @return 二维数组
     */
    public static int[][] read2dArrFromFile(String filePath) {

        LineNumberReader lineNumberReader = null;
        BufferedReader reader = null;

        try {

            //1. 获取总行数 - rowNum
            reader = new BufferedReader(new FileReader(filePath));
            lineNumberReader = new LineNumberReader(new FileReader(filePath));
            lineNumberReader.skip(Integer.MAX_VALUE);
            int rowNum = lineNumberReader.getLineNumber();

            int colNum = 0;
            String line;
            List<String[]> rows = new ArrayList<>();
            while ((line = reader.readLine()) != null) {

                String[] colStrValArr = line.split(",");

                if (colNum == 0) {
                    //2. 获取总列数 - colNum
                    colNum = colStrValArr.length;
                }

                rows.add(colStrValArr);

            }

            //3. 初始化二维数组
            int[][] arr = new int[rowNum][colNum];

            //4. 给二维数组的元素赋值
            for (int i = 0; i < rows.size(); i++) {

                String[] row = rows.get(i);

                for (int j = 0; j < row.length; j++) {

                    String col = row[j];
                    arr[i][j] = Integer.parseInt(col);

                }

            }

            return arr;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }

                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;

    }

    public static void printArray(int[] arr) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {

            sb.append(arr[i]);

            if (i < arr.length - 1) {
                sb.append(",");
            } else {
                sb.append("]");
            }

        }

        System.out.println(sb.toString());

    }

}
