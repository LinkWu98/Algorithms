package cn.link.collection.datatype.bag;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: Link
 * @Date: 2020/7/24 14:07
 * @Version 1.0
 */
public class BagTest {

    Bag<String> bag = new Bag<>();

    @Test
    public void test(){

        LinkedList linkedList = new LinkedList();

        bag.add("aaa");
        bag.add("bbb");
        bag.add("ccc");

        //获取具有随机性
        System.out.println(bag.get());

    }

}
