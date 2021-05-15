package cn.link.data.structure.tree;

import cn.link.data.structure.linkedlist.Node;
import cn.link.data.structure.linkedlist.Stack;

/**
 * 树结构
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/14 12:42
 */
public class Tree<T> {

    public Node<T> root;

    /**
     * 非递归的前序遍历，借助栈实现
     */
    public void preOrder() {

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (stack.size() > 0) {

            Node<T> node = stack.pop();
            System.out.println(node.data);

            //栈的先入后出，让其先pop左
            if (node.rightChild != null) {
                stack.push(node.rightChild);
            }

            if (node.leftChild != null) {
                stack.push(node.leftChild);
            }

        }

    }

    public void preOrderRecursive() {
        root.preOrder();
    }

    /**
     * 节点
     */
    class Node<T> {

        /**
         * 权
         */
        public T data;

        public Node<T> leftChild;

        public Node<T> rightChild;

        public void preOrder() {

            System.out.println(this.data);

            if (this.leftChild != null) {
                this.leftChild.preOrder();
            }

            if (this.rightChild != null) {
                this.rightChild.preOrder();
            }

        }

    }

}
