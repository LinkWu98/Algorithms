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
        singleOrderedLinkedList.add(1, "b");
        singleOrderedLinkedList.add(2, "c");
        singleOrderedLinkedList.add(3, "d");
        singleOrderedLinkedList.add(5, "e");
        singleOrderedLinkedList.add(4, "f");
        System.out.println(singleOrderedLinkedList.toString());
    }


}
