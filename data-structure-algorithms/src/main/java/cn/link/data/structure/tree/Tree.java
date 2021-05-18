package cn.link.data.structure.tree;

import cn.link.data.structure.linkedlist.Stack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 树结构
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/14 12:42
 */
@Data
public class Tree<T> {

    public Node<T> root;

    /**
     * 非递归的前序遍历，借助栈实现
     * <p>
     * 1.先将根节点入栈
     * 2.访问根节点
     * 3.如果根节点存在右孩子，则将右孩子入栈
     * 4.如果根节点存在左孩子，则将左孩子入栈（注意：一定是右孩子先入栈，然后左孩子入栈）
     * 5.重复2-4
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

    /**
     * 非递归中序遍历
     * <p>
     * <p>
     * 1.先将根节点入栈
     * 2.将当前节点的所有左孩子入栈，直到左孩子为空
     * 3.访问栈顶元素，如果栈顶元素存在右孩子，则继续第2步
     * 4.重复第2、3步，直到栈为空并且所有的节点都被访问
     */
    public void infixOrder() {

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        Node<T> node = root;
        while (node != null || stack.size() > 0) {

            //重点：通过 node == null，判断：1.往回拿 / 2.继续往左找到底
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }

            /*
                核心就是 pop
                两种情况:
                1.左边再往下没有了，获取栈顶元素，也就是左边的叶子结点输出(左中右的左或中)
                2.之前的结点没有右节点，*此处可以往回拿节点
             */
            node = stack.pop();
            System.out.println(node.data);
            //该节点已输出，此时视该节点为父节点，取右边的节点
            if (node.rightChild != null) {
                node = node.rightChild;
            } else {
                node = null;
            }

        }

    }

    /**
     * 非递归后续遍历
     * <p>
     * 思路：
     * 1. 一直入栈左边节点，直到没有左边节点
     * 2. 看栈顶元素是否有右节点，有就入栈右节点，重复 1
     * 3. 没有就输出栈顶元素
     */
    public void postOrder() {

        Stack<Node<T>> stack = new Stack<>();
        Node<T> temp = root;
        Node<T> prev = null;
        while (temp != null || stack.size() > 0) {

            while (temp != null) {
                stack.push(temp);
                temp = temp.leftChild;
            }

            //后序不同于中序，栈顶元素不马上pop，peek出来找右边元素
            temp = stack.peek();

            if (temp != null && temp.rightChild != null && prev != temp.rightChild) {
                temp = temp.rightChild;
            } else {
                Node<T> node = stack.pop();
                //记录下当前输出的元素，以免后续rightChild重复入栈
                prev = node;
                temp = null;
                System.out.println(node.data);
            }

        }

    }

    public void preOrderRecursive() {
        if (root == null) {
            System.out.println("根节点为空");
        }
        root.preOrderRecursive();
    }

    public void infixOrderRecursive() {
        if (root == null) {
            System.out.println("根节点为空");
        }
        root.infixOrderRecursive();
    }

    public void postOrderRecursive() {
        if (root == null) {
            System.out.println("根节点为空");
        }
        root.postOrderRecursive();
    }

    /**
     * 节点
     */
    @Data
    static class Node<T> {

        /**
         * 权
         */
        public T data;

        public Node<T> leftChild;

        public Node<T> rightChild;

        /**
         * 基于递归的前序遍历
         * <p>
         * 中左右
         */
        public void preOrderRecursive() {

            System.out.println(this.data);

            if (this.leftChild != null) {
                this.leftChild.preOrderRecursive();
            }

            if (this.rightChild != null) {
                this.rightChild.preOrderRecursive();
            }

        }

        /**
         * 基于递归的中序遍历(左子树 -> 父节点 -> 右子树)
         * <p>
         * 左中右
         */
        public void infixOrderRecursive() {

            if (this.leftChild != null) {
                this.leftChild.infixOrderRecursive();
            }

            System.out.println(this.data);

            if (this.rightChild != null) {
                this.rightChild.infixOrderRecursive();
            }

        }

        /**
         * 基于递归的后序遍历(左子树 -> 右子树 -> 父节点)
         * <p>
         * 左右中
         */
        public void postOrderRecursive() {

            if (this.leftChild != null) {
                this.leftChild.infixOrderRecursive();
            }

            if (this.rightChild != null) {
                this.rightChild.infixOrderRecursive();
            }

        }

    }

}
