package cn.link.collection.datatype.bag;

import cn.link.collection.MyLinkedList;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.Random;

/**
 * 背包
 *
 * 不支持删元素
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/7 13:33
 */
@NoArgsConstructor
public class Bag<E> implements Iterator<E> {

    /**
     * 记录实际元素数量
     */
    private int size;

    private MyLinkedList<E> myLinkedList = new MyLinkedList<>();


    public void add(E e) {
        myLinkedList.add(e);
        size++;
    }

    /**
     * 包的获取具有随机性
     */
    public E get() {
        return myLinkedList.get( new Random().nextInt(myLinkedList.getSize()) );
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }
}
