package cn.link.datastructure.queue;

/**
 * 队列，FIFO
 * <p>
 * 基于数组实现，记录头和尾，添加在尾，移除在头
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/28 11:08
 */
public class ArrayQueue implements Queue {

    /**
     * 默认数组最大长度
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * 内部数组
     */
    private Object[] elements;

    /**
     * 最大容量
     */
    private int maxSize;

    /**
     * 当前容量
     */
    private int currentSize;

    /**
     * 记录队首元素的索引
     */
    private int front = -1;

    /**
     * 记录队尾元素的索引(也是添加时元素的位置)
     */
    private int rear = 0;


    /**
     * 初始化
     */
    public ArrayQueue() {
        currentSize = 0;
        maxSize = DEFAULT_SIZE;
        elements = new Object[maxSize];
    }

    public ArrayQueue(int size) {

        if (size <= 0) {
            size = DEFAULT_SIZE;
        }

        currentSize = 0;
        maxSize = size;
        elements = new Object[maxSize];

    }


    /**
     * 添加元素到队尾
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(Object element) {

        //超过最大容量，返回
        if (size() == DEFAULT_SIZE) {
            return;
        }

        //第一次加元素，给到队首
        if (front == -1) {
            elements[++front] = element;
            rear++;
            currentSize++;
            return;
        }

        //其他情况，先给到队尾，然后索引 + 1
        elements[rear++] = element;
        currentSize++;

    }

    /**
     * 移除队首的元素
     *
     * @return 队首的元素
     */
    @Override
    public Object remove() {

        //空
        if (size() == 0) {
            return null;
        }

        Object element = elements[front];
        elements[front] = null;

        //最后一个
        if (size() == 1) {
            //初始化头尾
            front = -1;
            rear = 0;
            return element;
        }

        front++;
        return element;

    }

    /**
     * @return 有效元素个数
     */
    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

}
