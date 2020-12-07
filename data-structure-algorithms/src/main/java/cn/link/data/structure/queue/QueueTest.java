package cn.link.data.structure.queue;

import org.junit.Test;

/**
 * @author Link
 * @version 1.0
 * @date 2020/12/3 20:28
 * @description 队列测试
 */
public class QueueTest {

    @Test
    public void arrayQueueTest() {

        ArrayQueue<String> arrayQueue = new ArrayQueue<>(3);

        System.out.println(arrayQueue.toString());
        arrayQueue.add("1");
        arrayQueue.add("1");
        arrayQueue.add("1");
        System.out.println(arrayQueue.toString());

        arrayQueue.remove();

        //假溢出
        arrayQueue.add("1");

    }

}
