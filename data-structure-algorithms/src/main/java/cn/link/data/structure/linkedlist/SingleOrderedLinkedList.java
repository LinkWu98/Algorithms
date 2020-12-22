package cn.link.data.structure.linkedlist;

/**
 * 单有序链表
 *
 * @author Link50
 * @version 1.0
 * @date 2020/12/18 14:51
 */
public class SingleOrderedLinkedList<T> {

    /**
     * 头节点,不存数据
     */
    private final Node<T> HEAD = new Node<>();

    /**
     * 插入到链表最后一条
     */
    public void add(T data) {

        //从头开始遍历，使用临时变量记录当前节点
        Node<T> temp = HEAD;
        while (temp.next != null) {
            temp = temp.next;
        }

        Node<T> next = new Node<>();
        next.data = data;
        temp.next = next;

    }

    /**
     * 按指定排名插入节点到链表中
     * <p>
     * 与重载的 add 割裂，仅作尝试，后续去不完善
     */
    public void add(int rank, T data) {

        Node<T> temp = HEAD;
        while (temp.next != null) {

            //插入的 rank < 当前 rank
            if (temp.next.rank < rank) {
                Node<T> nextTemp = temp.next;
                temp.next.data = data;
                temp.next.rank = rank;
            }

            temp = temp.next;

        }

    }

    @Override
    public String toString() {

        StringBuilder returnValue = new StringBuilder();

        returnValue.append("[");

        Node<T> next = HEAD.next;
        while (next != null) {

            returnValue.append(next.data.toString());
            next = next.next;
            if (next != null) {
                returnValue.append(",");
            } else {
                break;
            }

        }

        returnValue.append("]");

        return returnValue.toString();

    }

    /**
     * 节点对象,每个节点由:数据域、next域组成
     */
    private class Node<T> {

        /**
         * data域
         */
        private T data;

        /**
         * next域
         */
        private Node<T> next;

        /**
         * 节点在链表中的位置
         */
        private int rank;

    }


}
