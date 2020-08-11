package cn.link.collection.datatype.queue;

import cn.link.collection.MyLinkedList;
import lombok.NoArgsConstructor;

/**
 * 队列
 * FIFO 先进先出
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/7 14:31
 */
@NoArgsConstructor
public class Queue<E> {

    private MyLinkedList<E> myLinkedList = new MyLinkedList<>();

    /**
     * 入队
     */
    void enqueue(E e) {
        myLinkedList.add(e);
    }

    /**
     * 出队 (移除最早添加的元素)
     */
    E dequeue() {
        return myLinkedList.remove(0);
    }

}
