package cn.link.data.structure.linkedlist;

import org.junit.Test;

/**
 * @author Link50
 * @version 1.0
 * @date 2020/12/18 15:37
 */
public class LinkedListTest {

    @Test
    public void singleLinkedListTest(){

        SingleOrderedLinkedList<String> singleOrderedLinkedList = new SingleOrderedLinkedList<>();
        singleOrderedLinkedList.add("a");
        singleOrderedLinkedList.add("b");
        System.out.println(singleOrderedLinkedList.toString());

    }

    @Test
    public void singleOrderedLinkedListTest(){

        SingleOrderedLinkedList<String> singleOrderedLinkedList = new SingleOrderedLinkedList<>();
        singleOrderedLinkedList.add(1, "a");
        //替换
        singleOrderedLinkedList.add(1, "b");
        singleOrderedLinkedList.add(2, "c");
        singleOrderedLinkedList.add(4, "d");
        singleOrderedLinkedList.add(3, "e");
        singleOrderedLinkedList.add(5, null);
        singleOrderedLinkedList.add(6, "f");
        System.out.println("正向打印:" + singleOrderedLinkedList.toString());
        //反向遍历
        System.out.println("反向打印:" + singleOrderedLinkedList.toStringFromLast());

        //获取第n个
        int index = 0;
        System.out.println("获取第"+ index +"个data: " + singleOrderedLinkedList.get(index));

        //获取倒数第n个
        int fromLast = 0;
        System.out.println("获取倒数第"+ fromLast +"个data: " + singleOrderedLinkedList.getFromLast(fromLast));

        //传统反转
        singleOrderedLinkedList.reverseTraditional();
        System.out.println("传统反转:" + singleOrderedLinkedList.toString());

        //头插法反转
        singleOrderedLinkedList.reverseLikeStack();
        System.out.println("头插法反转:" + singleOrderedLinkedList.toString());

        //map反转
        singleOrderedLinkedList.reverseByMap();
        System.out.println("map反转:" + singleOrderedLinkedList.toString());

        //有效节点个数
        System.out.println("有效节点个数: " + singleOrderedLinkedList.available());

        //删除
        int removeNo = 5;
        singleOrderedLinkedList.remove(removeNo);
        System.out.println("删除了no" + removeNo + "的元素: " + singleOrderedLinkedList.toString());
        System.out.println("有效节点个数: " + singleOrderedLinkedList.available());


    }

    /**
     * 栈的遍历
     */
    @Test
    public void testStack(){

        Stack<String> stack = new Stack<>();

        stack.push("a");
        stack.push("b");
        stack.push("c");

        while (stack.peek() != null) {
            System.out.println(stack.pop());
        }

    }



}
