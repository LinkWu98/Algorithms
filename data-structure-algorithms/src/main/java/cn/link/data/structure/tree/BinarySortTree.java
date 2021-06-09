package cn.link.data.structure.tree;

import cn.link.data.structure.linkedlist.Stack;
import lombok.Data;
import lombok.ToString;

/**
 * 二叉排序树 - BST (BinarySortTree / BinarySearchTree)
 *
 * 对于二叉排序树的任何一个非叶子节点，要求左子节点的值 < 父节点的值，右子节点的值 > 父节点的值 (值相同，左右都可以放)
 *
 * @author Link50
 * @version 1.0
 * @date 2021/6/9 10:52
 */
public class BinarySortTree {

    /**
     * 根节点
     */
    private BSTNode root;

    /**
     * 插入数据
     *
     * @param data
     */
    public void add(int data) {

        BSTNode node = new BSTNode();
        node.data = data;

        //根没有，直接给根
        if (root == null){
            root = node;
            return;
        }

        BSTNode temp = root;
        BSTNode prev = temp;
        //true 右边， false 左边
        boolean sideFlag = false;
        while (temp != null) {

            prev = temp;

            if (data < temp.data) {
                //小于当前，往左节点去比较
                temp = temp.left;
                sideFlag = false;
                continue;
            }

            //大于或等于当前，都往右节点去比较
            temp = temp.right;
            sideFlag = true;

        }

        if (sideFlag) {
            prev.right = node;
        } else {
            prev.left = node;
        }

    }

    /**
     * 中序遍历
     */
    public void infixTraversal() {

        Stack<BSTNode> stack = new Stack<>();
        BSTNode temp = root;
        while (temp != null || stack.size() > 0) {

            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            BSTNode current = stack.pop();
            System.out.println(current.data);

            if (current.right != null) {
                temp = current.right;
            } else {
                temp = null;
            }

        }

    }

}

@Data
@ToString
class BSTNode {

    public BSTNode left;

    public BSTNode right;

    /**
     * 权
     */
    public int data;

}
