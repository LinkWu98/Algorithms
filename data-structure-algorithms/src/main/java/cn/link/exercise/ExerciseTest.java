package cn.link.exercise;

import cn.link.exercise.recursion.EightQueens;
import cn.link.exercise.recursion.Labyrinth;
import cn.link.exercise.structure.CalculatorByStack;
import cn.link.exercise.structure.GoogleHashtable;
import cn.link.exercise.structure.Joseph;
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
     * 栈实现综合计算器测试
     */
    @Test
    public void calculatorByStackTest() {

        CalculatorByStack calculator = new CalculatorByStack();

        //System.out.println(calculator.calculate("(30+2)"));
        System.out.println(calculator.calculate("(2+3)+(4-5)*2"));

        //中缀转后缀
        System.out.println(calculator.commonToRPN("1+((2+3)*4)-5"));
        System.out.println(calculator.commonToRPN("1+2*3+4"));

    }
    @Test
    public void testLabyrinth() {

        Labyrinth labyrinth = new Labyrinth();
        //指定方向倾向的行走路径
        labyrinth.findWay(1, 1, 4, 6);

        labyrinth = new Labyrinth();


    }

    /**
     * 八皇后问题
     */
    @Test
    public void testEightQueens() {

        EightQueens eightQueens = new EightQueens();
        eightQueens.getEightQueenPossibilities();

    }

    /**
     * 谷歌哈希表测试
     */
    @Test
    public void testGoogleHashtable(){

        GoogleHashtable hashtable = new GoogleHashtable(10);
        GoogleHashtable.Employee employee1 = new GoogleHashtable.Employee(1, "link");
        GoogleHashtable.Employee employee2 = new GoogleHashtable.Employee(2, "link");
        GoogleHashtable.Employee employee6 = new GoogleHashtable.Employee(11, "link");

        hashtable.put(employee1);
        hashtable.put(employee2);
        hashtable.put(employee6);

        GoogleHashtable.Employee employee = hashtable.get(11);
        System.out.println(employee == null ? "null" : employee.toString());

        GoogleHashtable.Employee employee7 = new GoogleHashtable.Employee(11, "zelda");
        hashtable.put(employee7);
        employee = hashtable.get(11);
        System.out.println(employee == null ? "null" : employee.toString());

        hashtable.remove(11);
        employee = hashtable.get(11);
        System.out.println(employee == null ? "null" : employee.toString());

    }

}
