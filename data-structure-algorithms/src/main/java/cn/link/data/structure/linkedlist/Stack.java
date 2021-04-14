package cn.link.data.structure.linkedlist;

/**
 * 基于链表实现的栈
 *
 * @author Link50
 * @version 1.0
 * @date 2021/1/19 15:52
 */
public class Stack<T> {

    /**
     * 栈顶
     */
    private Node<T> top;

    private int size = 0;

    public Stack() {
    }

    /**
     * 将 element 压入栈
     */
    public void push(T element) {

        size++;

        //第一次压栈，头就是该元素
        if (top == null) {
            top = new Node<>(element);
            return;
        }

        //后续压栈，当前元素称为 head ，next 指向之前的 head
        Node<T> temp = top;
        top = new Node<>(element);
        top.next = temp;

    }

    /**
     * 栈顶的元素出栈，但不删除
     */
    public T peek() {

        if (top == null) {
            return null;
        }

        return top.data;

    }

    /**
     * 栈顶的元素出栈，并删除
     */
    public T pop() {

        if (top == null) {
            return null;
        }

        T returnValue = top.data;

        top = top.next;

        size--;

        return returnValue;

    }

    public void reverse() {

        Node<T> head = new Node<>();
        while (top != null) {
            head.next = new Node<>(top.data, head.next);
            //舍弃原栈顶
            this.top = this.top.next;
        }

        top = head.next;

    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node<T> temp = top;
        while (temp != null) {
            sb.append(temp.data).append(" ");
            temp = temp.next;
        }

        return sb.toString();

    }

    public int size() {
        return size;
    }


}
