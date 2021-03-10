package cn.link.data.structure.array;

/**
 * 数组模拟栈
 *
 * @author Link50
 * @version 1.0
 * @date 2021/3/10 16:55
 */
public class Stack<T> {

    /**
     * 默认容量为 10,就不做扩容操作了
     */
    private static final int INIT_SIZE = 10;

    private Object[] elementData;

    private int currentIndex = -1;

    public Stack() {
        elementData = new Object[INIT_SIZE];
    }

    /**
     * 压栈
     */
    public void push(T data) {

        elementData[++currentIndex] = data;

    }

    /**
     * 查看栈顶
     */
    public T peek() {

        return (T) elementData[currentIndex];

    }

    /**
     * 弹栈
     */
    public T pop() {

        if (currentIndex == -1) {
            return null;
        }

        T data = (T) elementData[currentIndex];
        elementData[currentIndex--] = null;

        return data;

    }

}
