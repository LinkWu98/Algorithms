package cn.link.data.structure.linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单有序链表
 *
 * 单链表 - 指向下一个
 * 有序 - 元素按照插入顺序存放
 *
 * @author Link50
 * @version 1.0
 * @date 2020/12/18 14:51
 */
public class SingleOrderedLinkedList<T> {

    /**
     * 节点数量
     */
    private int count;

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
        count++;

    }

    /**
     * 按指定编号插入节点到链表中
     * 编号重复会替换 data
     * <p>
     * 遍历节点后插入，数据量大时效率差
     *
     */
    public void add(int no, T data) {

        Node<T> newNode = new Node<>(data, null, no);

        Node<T> temp = HEAD;
        while (temp.next != null) {

            Node<T> nextTemp = temp.next;

            //插入的 no < 下一个的 no ，插到 下一个的 前面
            if (no < nextTemp.no) {
                temp.next = newNode;
                newNode.next = nextTemp;
                count++;
                return;
            }

            //no 相同就替换下一个的 data
            if (no == nextTemp.no) {
                nextTemp.data = data;
                return;
            }

            //插入的 no > 下一个的 no，继续遍历
            temp = nextTemp;

        }

        //没下一个了，就在下一个插入
        temp.next = newNode;
        count++;

    }

    /**
     * 有效节点的个数(data != null)
     */
    public int available() {

        int count = 0;

        Node<T> temp = HEAD;
        //有效节点
        while ((temp = temp.next) != null) {
            if (temp.data != null) {
                count++;
            }
        }

        return count;

    }

    /**
     * 获取第 n 个节点的 data(从 0 开始)
     */
    public Object get(int index) {

        //1. 获取有效节点个数
        if (index > this.count) {
            return null;
        }

        //2. 获取对应下标的节点
        int tempIndex = 0;
        Node<T> temp = HEAD;
        while (temp.next != null) {

            if (tempIndex++ == index) {
                return temp.next.data;
            }

            temp = temp.next;
        }

        return null;

    }

    /**
     * 获取第 n 个节点的 data(从 0 开始)
     */
    public Node<T> getNode(int index) {

        //1. 获取有效节点个数
        if (index > this.count) {
            return null;
        }

        //2. 获取对应下标的节点
        int tempIndex = 0;
        Node<T> temp = HEAD;
        while (temp.next != null) {

            if (tempIndex++ == index) {
                return temp.next;
            }

            temp = temp.next;
        }

        return null;

    }


    /**
     * 获取倒数第 n 个节点的 data(从 0 开始)
     */
    public Object getFromLast(int n) {

        //1. 获取有效节点个数
        if (n > this.count) {
            return null;
        }

        //2. 计算正数所需的是第几个
        int index = this.count - 1 - n;

        //3. 获取对应下标的data
        return this.get(index);

    }

    /**
     * 反转单链表
     *
     * 使用 map 方法，减少遍历次数 ^.^
     */
    public void reverse() {

        //少于两个元素，不需要反转
        if (count < 2) {
            return;
        }

        Map<Integer, Node<T>> idxNodeMap = new HashMap<>();
        for (int currentIndex = 0; currentIndex < count - 1; currentIndex++) {
            idxNodeMap.put(currentIndex, this.getNode(currentIndex));
        }

        for (int i = 0; i < count; i++) {

            //倒数第二个节点时，改变 head 的 next 指向
            if (i == count - 2) {
                HEAD.next = idxNodeMap.get(i + 1);
            }

            //前后交换，后指向前
            idxNodeMap.get(i + 1).next = idxNodeMap.get(i);

        }

    }


    /**
     * 指定 no 删除节点
     * @param no
     * @return
     */
    public boolean remove(int no) {

        //标记记录是否删除
        boolean flag = false;

        Node<T> temp = HEAD;
        while (temp.next != null) {

            Node<T> nextTemp = temp.next;
            if (no != nextTemp.no) {
                temp = nextTemp;
                continue;
            }

            //编号相同时，temp的 next 指向 nextTemp 的 next
            temp.next = nextTemp.next;
            flag = true;
            count--;

        }

        return flag;

    }

    @Override
    public String toString() {

        StringBuilder returnValue = new StringBuilder();

        returnValue.append("[");

        Node<T> next = HEAD.next;
        while (next != null) {

            if (next.data != null) {
                returnValue.append(next.data.toString());
            } else {
                returnValue.append("null");
            }
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
         * 节点的编号，在链表中节点会按 no 排序
         */
        private int no;

        public Node() {
        }

        public Node(T data, Node<T> next, int no) {
            this.data = data;
            this.next = next;
            this.no = no;
        }

    }
}
