package cn.link.algorithms4.collection;

/**
 * 双向链表
 * <p>
 * 索引从 0 计算
 * 尾插法
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/7 13:46
 */
public class MyLinkedList<E> {

    private MyNode first;

    private MyNode last;

    private int size;

    /**
     * 添加元素
     */
    public void add(E e) {
        MyNode node = new MyNode();
        node.element = e;
        add(node);
    }

    /**
     * 添加节点 (默认尾插法)
     */
    private void add(MyNode node) {

        //prev 是之前 last，之前 last 成为此次添加的数据的 prev
        //此次添加的数据再成为 last
        if (last != null) {
            node.prev = last;
        }
        last = node;
        last.next = node;

        //如果 first 为空说明还没存数据，设置该数据为 first
        if (size == 0) {
            first = node;
        }

        //大小增加
        size++;

    }

    /**
     * 删除指定位置的元素
     */
    public E remove(int index) {

        if (size == 0) {
            throw new RuntimeException("Index out of bound!");
        }

        MyNode node = getNode(index);

        //大小是 1，首尾都移除即可 (双向链表)
        if (size == 1) {
            first = null;
            last = null;
            size--;
            return node.element;
        }

        MyNode prev = node.prev;
        MyNode next = node.next;

        //若 node 是首尾的情况下，也要随之作出改变
        if (last == node) {
            last = prev;
        } else if (first == node) {
            first = node.next;
        }

        //上一个元素的下一个元素是待删除元素的下一个元素
        prev.next = next;
        //下一个元素的上一个元素是待删除元素的上一个元素
        next.prev = prev;

        size--;

        return node.element;

    }

    /**
     * 获取指定索引的 node 的 element
     */
    public E get(int index) {
        MyNode myNode = getNode(index);
        return myNode == null ? null : myNode.element;
    }

    /**
     * 获取指定索引的 node
     */
    public MyNode getNode(int index) {

        if (index == 0) {
            return first;
        }

        if (index == size - 1) {
            return last;
        }


        MyNode node = first;
        for (int i = 0; i <= index; i++) {

            if (i == index) {
                return node;
            } else {
                node = node.next;
            }

        }

        return null;

    }

    public int getSize() {
        return this.size;
    }

    /**
     * 反转链表
     */
    public void reverse() {

        MyNode node;

        for (int i = size - 1; i >= 0; i--) {

            node = getNode(i);

            //若不是第一个元素，该元素的next为上一个元素，反之为空
            if (i != 0) {
                node.next = getNode(i - 1);
            } else {
                node.next = null;
            }

            //若不是最后一个元素，该元素的 prev 为下一个元素，反之为空
            if (i != size - 1) {
                node.prev = getNode(i + 1);
            } else {
                node.prev = null;
            }

        }

        //交换首位，否则get方法还是会以反转的first为基准
        node = first;
        first = last;
        last = node;

    }

    /**
     * 节点对象，next 属性指向下个对象
     */
    class MyNode {

        E element;

        MyNode next;

        MyNode prev;

    }

}


