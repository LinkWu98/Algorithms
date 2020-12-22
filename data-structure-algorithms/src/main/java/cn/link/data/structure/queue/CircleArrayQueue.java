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
 * 解决假溢出：核心就是改变队满判断，解决二义性
 * <p>
 * 本次选择空闲单元法，将队满判断改为 (rear + 1) % maxSize == front ，解决二义性
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
    @Override
    public boolean add(Object element) {

        //队满判断 (后一位是不是 front 的位置)
        if (((rear + 1) % maxSize) == front) {
            throw new RuntimeException("队列已满!");
        }

        elements[rear] = element;

        //后移一位
        rear = (rear + 1) % maxSize;

        return true;

    }

    /**
     * 出队
     */
    @Override
    public Object remove() {

        //判空
        if (front == rear) {
            throw new RuntimeException("队列为空!");
        }

        Object returnVal = elements[front];
        elements[front] = null;

        //后移一位
        front = (front + 1) % maxSize;

        return returnVal;

    }

    /**
     * 获取元素个数
     */
    public int size() {

        return (rear - front + maxSize) % maxSize;

    }

    /**
     * 打印队列
     */
    public void printQueue() {

        //从 front 开始打印
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, elements[i % maxSize]);
        }

    }

}
