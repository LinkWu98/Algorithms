package cn.link.exercise.recursion;

import cn.link.common.AlgorithmUtil;
import cn.link.common.ArrayUtil;

import java.util.*;
import java.util.stream.Collectors;

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

    public void initMaze() {

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
     * 尝试步数
     */
    private int stepCount;

    /**
     * 最简单的实现,按指定路径策略寻找
     * <p>
     * 实际实现思路：
     * 下右上左 / 上右下左，各方向的排列组合，一个不行走另一个，走就完了
     *
     * @param currentPositionY 当前位置 y
     * @param currentPositionX 当前位置 x
     * @param endPositionY     终点 y
     * @param endPositionX     终点 x
     */
    public boolean findWay(int currentPositionY, int currentPositionX, int endPositionY, int endPositionX) {

        stepCount++;
        if (currentPositionY == endPositionY && currentPositionX == endPositionX) {
            MAZE[currentPositionY][currentPositionX] = 2;
            System.out.println("到达终点，查找路线次数: " + stepCount + " 次");
            return true;
        }

        try {
            Thread.sleep(500);
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

    /**
     * 找出最短路径
     * <p>
     * 其实就是各种方向策略存入一个集合，遍历集合，策略都走一遍，记录下每种策略行径次数，次数最少的就是最短的
     */
    public void findShortestWay(int currentPositionY, int currentPositionX, int endPositionY, int endPositionX) {

        Map<String, Integer> strategyStepCountMap = new HashMap<>();

        //排列组合所有行动策略
        List<List<Integer>> permutation = new ArrayList<>();
        AlgorithmUtil.permute(permutation, new ArrayList<>(), new int[]{1, 2, 3, 4});
        for (List<Integer> strategy : permutation) {

            //根据策略找路
            findWayByStrategy(strategy, currentPositionY, currentPositionX, endPositionY, endPositionX);

            //保存该策略的行径步数
            strategyStepCountMap.put(strategyToString(strategy), stepCount);

            //重新初始化迷宫
            stepCount = 0;
            initMaze();

        }

        //最短路径
        Integer shortestStep = strategyStepCountMap
                .values()
                .stream()
                .sorted()
                .collect(Collectors.toList())
                .get(0);

        Map<String, String> shortestStrategyMap = new HashMap<>();
        strategyStepCountMap.forEach((k, v) -> {
            if (v.equals(shortestStep)) {
                shortestStrategyMap.put(k, v + "步");
            }
        });

        System.out.println(shortestStrategyMap);

    }

    /**
     * 根据策略寻找位置
     *
     * @param strategy         方向策略
     * @param currentPositionY 当前位置 y
     * @param currentPositionX 当前位置 x
     * @param endPositionY     终点 y
     * @param endPositionX     终点 x
     */
    public boolean findWayByStrategy(List<Integer> strategy, int currentPositionY, int currentPositionX,
                                     int endPositionY, int endPositionX) {

        stepCount++;
        int currentPosition = MAZE[currentPositionY][currentPositionX];

        //0才能走
        if (currentPosition != 0) {
            return false;
        } else {
            //走过了，标记一下(bug记录:StackOverflowError, 如果不标记已走过，会一直来回走直到堆栈溢出)
            MAZE[currentPositionY][currentPositionX] = 2;
        }

        //到达终点
        if (currentPositionY == endPositionY && currentPositionX == endPositionX) {
            return true;
        }

        for (int i = 0; i < strategy.size(); i++) {

            switch (strategy.get(i)) {
                //上
                case 1:
                    if (findWayByStrategy(strategy, currentPositionY - 1, currentPositionX, endPositionY, endPositionX)) {
                        return true;
                    }
                    break;
                //下
                case 2:
                    if (findWayByStrategy(strategy, currentPositionY + 1, currentPositionX, endPositionY, endPositionX)) {
                        return true;
                    }
                    break;
                //左
                case 3:
                    if (findWayByStrategy(strategy, currentPositionY, currentPositionX - 1, endPositionY, endPositionX)) {
                        return true;
                    }
                    break;
                //右
                case 4:
                    if (findWayByStrategy(strategy, currentPositionY, currentPositionX + 1, endPositionY, endPositionX)) {
                        return true;
                    }
                    break;
            }

            //所有方向都尝试了还是不行
            if (i == strategy.size() - 1) {
                MAZE[currentPositionY][currentPositionX] = 3;
            }

        }

        return false;

    }

    private String strategyToString(List<Integer> strategy) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strategy.size(); i++) {

            switch (strategy.get(i)) {

                case 1:
                    sb.append("上");
                    break;
                case 2:
                    sb.append("下");
                    break;
                case 3:
                    sb.append("左");
                    break;
                case 4:
                    sb.append("右");
                    break;
            }

            if (i != strategy.size() - 1) {
                sb.append(",");
            }

        }

        return sb.toString();

    }

    public static void main(String[] args) {

        Labyrinth labyrinth = new Labyrinth();
        //labyrinth.findWayByStrategy(Arrays.asList(2, 4, 1, 3), 1, 1, 4, 6);
        //ArrayUtil.printSecondDimensionArr(MAZE);
        labyrinth.initMaze();
        labyrinth.findShortestWay(2, 2, 4, 6);

    }

}

