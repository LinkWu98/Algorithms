package cn.link.data.structure.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 单有序链表 (单链表)
 * <p>
 * 单链表 - 指向下一个
 * 有序 - 元素按照插入顺序存放
 *
 * @author Link50
 * @version 1.0
 * @date 2020/12/18 14:51
 */
public class SinglyLinkedList<T> {

    /**
     * 头节点,不存数据
     */
    private Node<T> head = new Node<>();
    /**
     * 节点数量
     */
    private int count;

    /**
     * 插入到链表最后一条
     */
    public void add(T data) {

        //从头开始遍历，使用临时变量记录当前节点
        Node<T> temp = head;
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
     */
    public void add(int no, T data) {

        Node<T> newNode = new Node<>(data, null, no);

        Node<T> temp = head;
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

        Node<T> temp = head;
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
        Node<T> temp = head;
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
        Node<T> temp = head;
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
     * 传统反转
     * 遍历次数 (count - 2) * 2 + 2 + 1次
     */
    public void reverseTraditional() {

        Node<T> last = new Node<>();

        for (int i = count - 1; i > 0; i--) {

            if (i == count - 1) {
                last = getNode(i);
            }

            Node<T> current = getNode(i);
            Node<T> prev = getNode(i - 1);
            current.next = prev;

            if (i == 1) {

                prev.next = null;
                head.next = last;

            }

        }

    }

    /**
     * map 反转
     * <p>
     * 一般的方式是要从最后一个往前转，不然next变了找不到的，而且遍历次数会很多
     * <p>
     * 但是使用 map 方法就不用，只需遍历 count + 1次
     */
    public void reverseByMap() {

        //一个元素，不需要反转
        if (count == 1) {
            return;
        }

        Map<Integer, Node<T>> idxNodeMap = new HashMap<>();
        for (int currentIndex = 0; currentIndex < count; currentIndex++) {
            idxNodeMap.put(currentIndex, this.getNode(currentIndex));
        }

        for (int i = 0; i < count - 1; i++) {

            if (i == 0) {
                idxNodeMap.get(i).next = null;
            }

            //倒数第二个节点时，改变 head 的 next 指向
            if (i == count - 2) {
                head.next = idxNodeMap.get(i + 1);
            }

            //前后交换，后指向前
            idxNodeMap.get(i + 1).next = idxNodeMap.get(i);

        }

    }

    /**
     * 头插法反转
     * <p>
     * 类似压栈，然后换一个 head 即可
     * <p>
     * 时间复杂度 O(n)
     */
    public void reverseLikeStack() {

        Node<T> newHead = new Node<>();
        Node<T> temp = this.head.next;

        while (temp != null) {

            newHead.next = new Node<>(temp.data, newHead.next, temp.no);

            temp = temp.next;

        }

        head = newHead;

    }

    /**
     * 指定 no 删除节点
     *
     * @param no
     * @return
     */
    public boolean remove(int no) {

        //标记记录是否删除
        boolean flag = false;

        Node<T> temp = head;
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

    /**
     * 合并单链表，合并后依然有序
     *
     * 不使用数组，时间复杂度为 n * m
     * 使用数组为 2m + n(<=n)
     */
    public void addAll(SinglyLinkedList<T> linkedList) throws Exception {

        if (linkedList == null || linkedList.count == 0) {
            return;
        }

        Node<T> temp = this.head.next;
        Node<T> anotherTemp = linkedList.head.next;
        Node<T>[] nodes = new Node[linkedList.count];
        int index = 0;
        //这里拿到传参的内部nodes，为了方便操作内部元素
        while (anotherTemp != null) {

            nodes[index++] = anotherTemp;
            anotherTemp = anotherTemp.next;

        }

        //记录内部遍历到的起始下标
        index = 0;
        boolean finishFlag = false;
        Node<T> prev = this.head;
        //外部遍历被比较的元素
        while (temp != null) {

            //内部遍历比较的元素
            for (int i = index; i < nodes.length; i++) {

                //大于，下标不变，外部继续遍历
                if (nodes[i].no > temp.no) {
                    break;
                }

                //等于 / 小于，插到前面
                if (nodes[i].no < temp.no || nodes[i].no == temp.no) {
                    prev.next = nodes[i];
                    nodes[i].next = temp;
                }

                if (index == nodes.length - 1) {
                    finishFlag = true;
                }

                index++;

            }

            if (finishFlag) {
                break;
            }

            //单向链表，用prev浅拷贝记录当前的
            prev = (Node<T>) temp.clone();
            temp = temp.next;

        }

        //没遍历到内部最后一个结束，说明部分内部元素的 no 大于外部的最后一个no，没插入，直接插入到外部的最后即可
        if (!finishFlag) {
            prev.next = nodes[index];
        }

    }

    public Node<T> getHead() {
        return head;
    }

    /**
     * 从尾到头打印
     */
    public String toStringFromLast() {

        //获取总元素数量
        int count = 0;
        Node<T> temp = head;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }

        StringBuilder returnValue = new StringBuilder();
        returnValue.append("[");

        //从尾到头遍历
        for (int i = count - 1; i >= 0; i--) {

            int currentLoop = 0;
            Node<T> tempInLoop = head.next;
            while (currentLoop++ != i) {
                tempInLoop = tempInLoop.next;
            }

            returnValue.append(tempInLoop.data == null ? "null" : tempInLoop.data);

            if (i != 0) {
                returnValue.append(",");
            }

        }

        returnValue.append("]");

        return returnValue.toString();

    }

    @Override
    public String toString() {

        StringBuilder returnValue = new StringBuilder();

        returnValue.append("[");

        Node<T> next = head.next;
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

}
