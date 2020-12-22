package cn.link.data.structure.queue;

/**
 * @author Link
 * @version 1.0
 * @date 2020/12/2 21:14
 * @description 基于数组实现的队列
 *
 * 问题：一次性数组，假溢出
 * front 队首
 * rear  队末 + 1
 *
 *
 */
public class ArrayQueue<T> extends Queue<T> {

    public ArrayQueue() {
    }

    public ArrayQueue(int maxSize) {
        super(maxSize);
    }

    /**
     * 入队
     */
    @Override
    public boolean add(T element) {

        //达到最大值
        if (rear == maxSize) {
            throw new RuntimeException("队列已满!");
        }

        elements[rear++] = element;
        return true;

    }

    /**
     * 出队
     */
    @Override
    public Object remove() {

        //判空（没加过 / 拿空了）
        if (front == rear) {
            throw new RuntimeException("队列为空!");
        }

        Object returnVal = elements[++front];
        elements[front] = null;

        return returnVal;

    }


}
