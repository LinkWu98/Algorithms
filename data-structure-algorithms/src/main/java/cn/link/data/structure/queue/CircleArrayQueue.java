package cn.link.data.structure.queue;

/**
 * @author Link
 * @version 1.0
 * @date 2020/12/7 21:00
 * @description 循环数组队列
 * <p>
 * front 队首
 * rear  队末 + 1
 * <p>
 * 解决假溢出：核心就是改变队满判断，解决二义性，front 和 rear可以环形往后推
 * <p>
 * 本次选择空闲单元法，解决二义性
 * 空闲单元默认为最后一个位置
 */
public class CircleArrayQueue<T> extends Queue<T> {

    public CircleArrayQueue() {
    }

    public CircleArrayQueue(int maxSize) {
        //使用空闲单元法，容量 + 1
        super.maxSize = maxSize + 1;
        super.elements = new Object[super.maxSize];
    }

    /**
     * 入队
     */
    public boolean add(Object element) {

        //队满判断
        if (((rear + 1) % maxSize) == front) {
            throw new RuntimeException("队列已满!");
        }

        elements[rear++] = element;
        //若 rear 已超过空闲单元，直接到跳第一个元素位置
        if (rear == maxSize) {
            rear = 0;
        }

        return true;

    }

    /**
     * 出队
     */
    public Object remove() {

        //判空
        if (front == rear) {
            throw new RuntimeException("队列为空!");
        }

        Object returnVal = elements[front];
        elements[front++] = null;

        //若 front 已超过空闲单元，直接到跳第一个元素位置
        if (front == maxSize) {
            front = 0;
        }

        return returnVal;

    }

}
