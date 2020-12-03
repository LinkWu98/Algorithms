package cn.link.data.structure.queue;

/**
 * @author Link
 * @version 1.0
 * @date 2020/12/2 21:14
 * @description 基于数组实现的队列
 */
public class ArrayQueue<T> extends Queue<T> {

    /**
     * 添加元素
     */
    public boolean add(T element) {

        //达到最大值
        if (rear + 1 == maxSize) {
            return false;
        }

        //第一次加元素
        if (front == -1) {
            elements[++front] = element;
            rear++;
            return true;
        }

        elements[++rear] = element;
        return true;

    }

    /**
     * 移除元素
     */
    public boolean remove() {

        //没加过元素
        if (front == -1) {
            return false;
        }

        //达到了边界
        if (front == rear) {
            elements[front] = null;
            //初始化
            front = -1;
            rear = -1;
            return true;
        }

        elements[front++] = null;

        return true;

    }



}
