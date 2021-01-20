package cn.link.data.structure.linkedlist;

/**
 * 基于链表实现的栈
 *
 * @author Link50
 * @version 1.0
 * @date 2021/1/19 15:52
 */
public class Stack<T> {

    private Node<T> head;

    public Stack() {
    }

    /**
     * 将 element 压入栈
     */
    public void push(T element) {

        //第一次压栈，头就是该元素
        if (head == null) {
            head = new Node<>(element);
            return;
        }

        //后续压栈，当前元素称为 head ，next 指向之前的 head
        Node<T> temp = head;
        head = new Node<>(element);
        head.next = temp;

    }

    /**
     * 栈顶的元素出栈，但不删除
     */
    public T peek() {

        if (head == null) {
            return null;
        }

        return head.data;

    }

    /**
     * 栈顶的元素出栈，并删除
     */
    public T pop() {

        if (head == null) {
            return null;
        }

        T returnValue = head.data;

        head = head.next;

        return returnValue;

    }


}
