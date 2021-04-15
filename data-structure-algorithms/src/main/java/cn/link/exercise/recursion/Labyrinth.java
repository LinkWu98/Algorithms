package cn.link.exercise.recursion;

import cn.link.common.ArrayUtil;

/**
 * 迷宫问题
 *
 * @author Link50
 * @version 1.0
 * @date 2021/4/15 11:33
 */
public class Labyrinth {

    /**
     * 迷宫围墙 = 1，未走过路径 = 0，走过的位置 = 2，死路 = 3
     * 终点位置 [length-2], [length-2]
     */
    private static final int[][] MAZE = new int[6][8];

    static {

        for (int i = 0; i < MAZE.length; i++) {

            for (int j = 0; j < MAZE[i].length; j++) {

                if (i == 0 || i == MAZE.length - 1) {

                    MAZE[i][j] = 1;

                } else if (j == 0
                        || j == MAZE[i].length - 1
                        //路障
                        || (i == 3 && j <= 2)) {

                    MAZE[i][j] = 1;

                } else {

                    MAZE[i][j] = 0;

                }
            }
        }

        MAZE[4][4] = 1;
        MAZE[3][5] = 1;
        System.out.println("============初始化迷宫============");
        ArrayUtil.printSecondDimensionArr(MAZE);

    }

    /**
     * 尝试次数
     */
    private int attemptCount;

    /**
     * 最简单的实现,穷举
     * <p>
     * 实际实现思路：
     * 下右上左 / 上右下左，各方向的排列组合，一个不行走另一个，走就完了
     *
     * @param currentPositionY 当前位置 x
     * @param currentPositionX 当前位置 y
     * @param endPositionY     终点 x
     * @param endPositionX     终点 y
     */
    public boolean findWay(int currentPositionY, int currentPositionX, int endPositionY, int endPositionX) {

        attemptCount++;
        if (currentPositionY == endPositionY && currentPositionX == endPositionX) {
            System.out.println("到达终点，查找路线次数: " + attemptCount + " 次");
            return true;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int currentPosition = MAZE[currentPositionY][currentPositionX];
        //当前位置
        if (currentPosition == 0) {
            MAZE[currentPositionY][currentPositionX] = 2;
            System.out.println("=======当前行迹路线=======");
            ArrayUtil.printSecondDimensionArr(MAZE);
            if (findWay(currentPositionY + 1, currentPositionX, endPositionY, endPositionX)) {
                //下
                return true;
            } else if (findWay(currentPositionY, currentPositionX + 1, endPositionY, endPositionX)) {
                //右
                return true;
            } else if (findWay(currentPositionY - 1, currentPositionX, endPositionY, endPositionX)) {
                //上
                return true;
            } else if (findWay(currentPositionY, currentPositionX - 1, endPositionY, endPositionX)) {
                //左
                return true;
            } else {
                //死路
                MAZE[currentPositionY][currentPositionX] = 3;
                return false;
            }
        } else {
            //围墙 / 死路
            return false;
        }

    }
}
