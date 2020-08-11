package cn.link.collection.datatype.queue;

import org.junit.Test;

/**
 * @author Link
 * @version 1.0
 * @date 2020/8/7 15:16
 */
public class QueueTest {

    @Test
    public void test(){

        Queue<String> queue = new Queue<>();

        queue.enqueue("aaa");
        queue.enqueue("bbb");

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }

}
