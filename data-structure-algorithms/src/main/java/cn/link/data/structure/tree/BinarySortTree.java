package cn.link.data.structure.tree;

import cn.link.data.structure.linkedlist.Stack;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
     * 根没有，直接给根
     * 小于当前，往左节点去比较
     * 大于或等于当前，都往右节点去比较
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
     * 删除节点
     *
     * 1. 删除叶子节点 (直接 parent.left/right = null 即可)
     * 2. 删除有单个子树的节点 (parent.left/right = target.left/right，用目标节点的子节点替换目标节点)
     * 3. 删除有两颗子树的节点 (
     *                       用target右子树最小的节点替换target节点, 因为右子树最小的节点一定是叶子节点，left/right直接赋值即可
     *                       temp.left/right = target.left/right; parent.left/right = temp
     *                       )
     *
     * @return true 删除成功
     */
    public boolean remove(int data) {

        //用中序遍历来找节点删除
        if (root == null) {
            return false;
        }

        BSTNode parent = null;
        if ((parent = getParentNode(data)) == null) {
            return false;
        }



        return false;

    }

    /**
     * 中序获取查询节点的父节点
     */
    public BSTNode getParentNode(int data) {

        BSTNode temp = root;
        Stack<BSTNode> stack = new Stack<>();
        while (stack.size() > 0 || temp != null) {

            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            if (temp.left != null) {
                if (temp.left.data == data) {
                    //TODO 左右标记
                    return temp;
                }
            }

            if (temp.right != null) {
                if (temp.right.data == data) {
                    return temp;
                }
            }

            if (temp.right != null) {
                temp = temp.right;
            } else {
                temp = null;
            }

        }

        return null;

    }

    /**
     * 中序遍历
     */
    public void infixTraversal() {

        List<Integer> list = new ArrayList<>();
        Stack<BSTNode> stack = new Stack<>();
        BSTNode temp = root;
        while (temp != null || stack.size() > 0) {

            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            BSTNode current = stack.pop();
            list.add(current.data);

            if (current.right != null) {
                temp = current.right;
            } else {
                temp = null;
            }

        }

        System.out.println(list);

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
