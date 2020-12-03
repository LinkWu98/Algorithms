package cn.link.data.structure.queue;

/**
 * @author Link
 * @version 1.0
 * @date 2020/12/3 20:31
 * @description 队列接口，包含一些公共属性，遵循 FIFO
 *
 */
public abstract class Queue<T> {

    /**
     * 元素
     */
    public T[] elements;

    /**
     * 队首元素的位置
     */
    public int front = -1;

    /**
     * 队末元素的位置
     */
    public int rear = -1;

    /**
     * 最大容量
     */
    public int maxSize = 10;


    public Queue() {
    }

    public Queue(int maxSize) {
        this.maxSize = maxSize;
    }

}
