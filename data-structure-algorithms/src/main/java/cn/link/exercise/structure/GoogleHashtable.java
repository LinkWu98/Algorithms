package cn.link.exercise.structure;

import cn.link.data.structure.linkedlist.Node;
import cn.link.data.structure.linkedlist.SinglyLinkedList;
import org.apache.poi.ss.formula.functions.T;

/**
 * 看一个实际需求，google公司的一个上机题:
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址..),当输入该员工的id时,要求查找到该员工的 所有信息.
 * 要求: 不使用数据库,尽量节省内存,速度越快越好=>哈希表(散列)
 * <p>
 * 哈希表的结构：数组 + 链表，基于Node节点实现
 * <p>
 * 所以核心就是 —— 通过算法快速确定节点在数组的下标
 * <p>
 * 涵盖了增删改查功能
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/11 18:10
 */
public class GoogleHashtable {

    /**
     * 初始化数组长度
     */
    private final int size;
    /**
     * 数组 + 链表（偷懒就不用node了，原理相同）
     */
    private final SinglyLinkedList<Employee>[] nodes;

    public GoogleHashtable(int size) {
        this.size = size;
        this.nodes = new SinglyLinkedList[size];
    }

    /**
     * 计算下标存储到对应位置，重复就覆盖
     * <p>
     * map结构的key指定了计算的对象，此处就直接用id计算了
     *
     * @param employee
     * @return true 插入成功
     */
    public boolean put(Employee employee) {

        if (employee == null) {
            return false;
        }

        //使用简单的取模法，来计算元素将要插入的下标
        int index = this.calculateIndex(employee.getId());
        if (this.nodes[index] == null) {
            this.nodes[index] = new SinglyLinkedList<>();
        }

        SinglyLinkedList<Employee> nodes = this.nodes[index];
        Node<Employee> node = nodes.getNode(0);
        //尾插法，id重复就覆盖
        while (node != null) {
            if (node.data.getId() == employee.getId()) {
                node.data = employee;
                return true;
            }
            node = node.next;
        }

        nodes.add(employee);

        return true;

    }

    /**
     * 计算下标，找到对应数组中链表，再遍历链表找到对应的数据
     *
     * @param id
     * @return
     */
    public Employee get(int id) {

        int index = this.calculateIndex(id);
        SinglyLinkedList<Employee> nodes = this.nodes[index];
        if (nodes == null) {
            return null;
        }

        Node<Employee> node = nodes.getNode(0);
        while (node != null) {

            if (node.data.getId() == id) {
                return node.data;
            }

            node = node.next;

        }

        //遍历到最后一个，还是要核对一下
        return null;

    }

    public int calculateIndex(int id) {
        return size % id;
    }

    public static class Employee {

        int id;
        String name;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


}

