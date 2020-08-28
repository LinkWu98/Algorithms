package cn.link.datastructure.queue;

import org.junit.Test;

/**
 * 队列测试类
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/28 13:02
 */
public class QueueTest {

    @Test
    public void arrayQueueTest(){

        ArrayQueue queue = new ArrayQueue();

        queue.add(new Object());
        queue.add(new Object());
        queue.add(new Object());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

    }

}
