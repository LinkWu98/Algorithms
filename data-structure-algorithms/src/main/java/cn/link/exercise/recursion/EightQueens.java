package cn.link.exercise.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 * <p>
 * 在8×8格中，任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法
 * <p>
 * 用一维数组表示棋的位置即可，下标对应第n行，元素对应第n列，好操作一些
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/21 14:22
 */
public class EightQueens {

    public void getEightQueenPossibilities() {

        List<List<Integer>> possibilities = new ArrayList<>();
        this.setQueens(possibilities, new ArrayList<>(), 0);
        System.out.println("可能性：" + possibilities.size() + "种");
        System.out.println("具体摆法：");
        possibilities.forEach(System.out::println);

    }

    /**
     * 不同行：数组下标表示行，必然不是同一行
     * 不同列：遍历之前的元素，不能相等
     * 不斜线：上一个元素 +1/-1 != 当前位置
     *
     * @param possibilities  所有可能性
     * @param queenPositions 当前已有的皇后位置摆放
     * @param currentIndex   当前需要摆放的位置
     * @return 所有可能性
     */
    public boolean setQueens(List<List<Integer>> possibilities, List<Integer> queenPositions, int currentIndex) {

        //放置完成，返回
        if (queenPositions.size() == 8) {
            possibilities.add(new ArrayList<>(queenPositions));
            return true;
        }

        OUTER:
        for (int i = 1; i <= 8; i++) {

            for (int j = queenPositions.size() - 1; j >= 0; j--) {
                Integer queenPosition = queenPositions.get(j);
                //不同列：遍历之前的元素，不能相等
                if (i == queenPosition) {
                    continue OUTER;
                }

                //不斜线：上n个元素 +n/-n != 当前位置
                Integer prevPosition = queenPositions.get(j);
                if (prevPosition + (currentIndex - j) == i || prevPosition - (currentIndex - j) == i) {
                    continue OUTER;
                }

            }

            queenPositions.add(i);
            setQueens(possibilities, queenPositions, ++currentIndex);
            queenPositions.remove(--currentIndex);

        }

        //如果全遍历了没满说明行不通,必然是false。。。都移除掉了
        return queenPositions.size() == 8;

    }

}
