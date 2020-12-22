package cn.link.data.structure.queue;

/**
 * @author Link
 * @version 1.0
 * @date 2020/12/3 20:31
 * @description 队列接口，包含一些公共属性，遵循 FIFO
 */
public abstract class Queue<T> {

    /**
     * 元素
     */
    public Object[] elements;

    /**
     * 队首的元素下标
     */
    public int front = 0;

    /**
     * 队末的元素下标 + 1
     */
    public int rear = 0;

    /**
     * 最大容量
     */
    public int maxSize = 5;


    public Queue() {
        elements = new Object[maxSize];
    }

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        elements = new Object[maxSize];
    }

    public boolean add(T element) {
        return false;
    }

    public Object remove() {
        return null;
    }

    @Override
    public String toString() {

        if (elements == null) {
            return "null";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.length; i++) {

            if (elements[i] != null) {

                sb.append(elements[i]);

                if (i < elements.length - 1 && elements[i + 1] != null) {
                    sb.append(",");
                }

            }

        }
        sb.append("]");

        return sb.toString();

    }

}
