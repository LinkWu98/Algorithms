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
        //有效节点个数
        System.out.println(singleOrderedLinkedList.toString());
        //反转
        singleOrderedLinkedList.reverse();
        System.out.println("反转:" + singleOrderedLinkedList.toString());
        System.out.println("有效节点个数: " + singleOrderedLinkedList.available());
        //获取倒数第n个data
        int index = 0;
        int fromLast = 0;
        System.out.println("获取第"+ index +"个data: " + singleOrderedLinkedList.get(index));
        System.out.println("获取倒数第"+ fromLast +"个data: " + singleOrderedLinkedList.getFromLast(fromLast));
        //删除
        singleOrderedLinkedList.remove(4);
        System.out.println(singleOrderedLinkedList.toString());
        System.out.println(singleOrderedLinkedList.available());
    }



}
