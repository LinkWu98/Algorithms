package cn.link.data.structure.linkedlist;

import org.junit.Test;

/**
 * @author Link50
 * @version 1.0
 * @date 2020/12/18 15:37
 */
public class LinkedListTest {

    @Test
    public void singlyLinkedListTest(){

        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.add("a");
        singlyLinkedList.add("b");
        System.out.println(singlyLinkedList.toString());

    }

    @Test
    public void singlyOrderedLinkedListTest(){

        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.add(1, "a");
        //替换
        singlyLinkedList.add(1, "b");
        singlyLinkedList.add(2, "c");
        singlyLinkedList.add(4, "d");
        singlyLinkedList.add(3, "e");
        singlyLinkedList.add(5, null);
        singlyLinkedList.add(6, "f");
        System.out.println("正向打印:" + singlyLinkedList.toString());
        //反向遍历
        System.out.println("反向打印:" + singlyLinkedList.toStringFromLast());

        //获取第n个
        int index = 0;
        System.out.println("获取第"+ index +"个data: " + singlyLinkedList.get(index));

        //获取倒数第n个
        int fromLast = 0;
        System.out.println("获取倒数第"+ fromLast +"个data: " + singlyLinkedList.getFromLast(fromLast));

        //传统反转
        singlyLinkedList.reverseTraditional();
        System.out.println("传统反转:" + singlyLinkedList.toString());

        //头插法反转
        singlyLinkedList.reverseLikeStack();
        System.out.println("头插法反转:" + singlyLinkedList.toString());

        //map反转
        singlyLinkedList.reverseByMap();
        System.out.println("map反转:" + singlyLinkedList.toString());

        //有效节点个数
        System.out.println("有效节点个数: " + singlyLinkedList.available());

        //删除
        int removeNo = 5;
        singlyLinkedList.remove(removeNo);
        System.out.println("删除了no" + removeNo + "的元素: " + singlyLinkedList.toString());
        System.out.println("有效节点个数: " + singlyLinkedList.available());


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

    /**
     * 双向链表测试
     */
    @Test
    public void doublyLinkedListTest(){



    }



}
