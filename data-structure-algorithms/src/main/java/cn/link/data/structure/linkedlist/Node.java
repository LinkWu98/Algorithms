package cn.link.data.structure.linkedlist;

/**
 * 节点对象,每个节点由:prev域、数据域、next域组成
 */
public class Node<T> {

    /**
     * data域
     */
    public T data;

    /**
     * next域
     */
    public Node<T> prev;

    /**
     * next域
     */
    public Node<T> next;

    /**
     * 节点的编号，在链表中节点会按 no 排序
     */
    public Integer no;

    public Node() {
    }

    public Node(T data, Node<T> next, Integer no) {
        this.data = data;
        this.next = next;
        this.no = no;
    }

    public Node(T data, Node<T> prev, Node<T> next, Integer no) {
        this.data = data;
        this.prev = prev;
        this.next = next;
        this.no = no;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data) {
        this.data = data;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}