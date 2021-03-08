package cn.link.data.structure.linkedlist;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表
 * <p>
 * 有指向上一个和下一个节点的指针，尾部指向头部
 *
 * 两个变量分别记录头结点和尾节点，用于新增和删除时操作
 * 核心就是新增和删除时改变节点的指针
 *
 * @author Link50
 * @version 1.0
 * @date 2021/3/5 14:57
 */
@Data
public class DoublyLinkedList<T> {

    /**
     * 起始节点
     */
    private Node<T> head;

    /**
     * 尾部节点
     */
    private Node<T> tail;

    /**
     * 当前大小
     */
    private int size = 0;

    /**
     * 新增节点
     */
    public void add(T data) {

        if (data == null) {
            return;
        }

        Node<T> node = new Node<>(data);

        if (head == null) {

            //第一次，初始化头部、尾部
            head = node;
            tail = node;

        } else {

            Node<T> prevTail = tail;
            tail = node;
            prevTail.next = node;
            node.prev = prevTail;

        }

        tail.next = head;
        head.prev = tail;

        size++;

    }

    public void addAll(T[] arr) {

        if (arr.length == 0) {
            return;
        }

        for (T t : arr) {

            this.add(t);

        }

    }

    /**
     * 环形链表解决出队问题
     * <p>
     * 核心思路:出队后链表上一个指向下一个
     *
     * @param startFrom 从哪个编号开始
     * @param dequeueNo 数到几出队
     */
    public String getJosephDequeueSequence(int startFrom, int dequeueNo) throws Exception {

        if (startFrom <= 0 || dequeueNo <= 0 || size() == 0 || startFrom > size()) {
            throw new Exception("Wrong parameter.");
        }

        int currentIndex = 1;
        Node<T> temp = head;
        while (startFrom != currentIndex) {
            temp = temp.next;
            currentIndex++;
        }

        List<T> dequeueSequence = new ArrayList<>();

        int currentNo = 1;
        while (size() > 0) {

            if (dequeueNo != currentNo) {

                currentNo++;

            } else {

                dequeueSequence.add(temp.data);
                this.remove(temp);
                currentNo = 1;

            }

            temp = temp.next;

        }

        return dequeueSequence.toString();

    }

    /**
     * 移除指定节点
     */
    public void remove(Node<T> node) {

        if (size() == 0 || node == null) {
            return;
        }

        if (size() > 1) {

            //指针移动
            Node<T> prevNode = node.prev;
            Node<T> nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

        } else {

            head = null;
            tail = null;

        }

        size--;

    }

    public int size() {
        return size;
    }

}
