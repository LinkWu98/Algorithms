package cn.link.data.structure.tree;

import cn.link.data.structure.linkedlist.Stack;
import lombok.Data;

import java.util.*;

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
     * 有重复的数就歇菜了，删除的时候无限循环，可以考虑再加个插入时间条件去匹配（类似版本号）
     *
     * @return true 删除成功
     */
    public boolean remove(int data) {

        if (root == null) {
            return false;
        }

        BSTNode parent;
        //用中序遍历找target的父节点
        if ((parent = getParentNode(data)) == null) {
            return false;
        }

        //获取目标节点
        BSTNode target = null;
        if (parent.childSideFlag) {
            target = parent.left;
        } else {
            target = parent.right;
        }

        //三种情况
        BSTNode rightOfTarget = target.right;
        BSTNode leftOfTarget = target.left;
        if (rightOfTarget != null && leftOfTarget != null) {

            //1. 有两颗子树 ->
            //方案一   右边子树的最小节点替换要删除的节点(叶子结点,因为左右都没有节点,可以直接赋值,也符合二叉树的规律)，删除原最小节点
            //方案二   左边子树的最大节点替换要删除的节点，删除原最大节点
            BSTNode tempSwapNode = rightOfTarget;
            while (tempSwapNode.left != null) {
                tempSwapNode = tempSwapNode.left;
            }
            //删除原来的节点，如果后删除会死循环
            this.remove(tempSwapNode.data);
            //再左右赋上值
            tempSwapNode.left = target.left;
            tempSwapNode.right = target.right;
            this.setNodeByParent(parent, tempSwapNode);

        } else if (rightOfTarget != null || leftOfTarget != null) {

            //2. 有单个子树 -> 子树的根节点替换要删除的节点
            BSTNode tempSwapNode = rightOfTarget == null ? leftOfTarget : rightOfTarget;
            this.setNodeByParent(parent, tempSwapNode);

        } else {
            //3. 无子节点，直接赋空
            this.setNodeByParent(parent, null);
        }

        return false;

    }

    /**
     * 根据选边，替换子节点
     */
    private void setNodeByParent(BSTNode parent, BSTNode swapNode) {
        if (parent.childSideFlag) {
            parent.left = swapNode;
        } else {
            parent.right = swapNode;
        }
    }

    /**
     * 根据指定内容查询其父节点
     *
     * 主要就是查找的时候，记录当前节点temp，每次往下查找，查找到了，temp就是父节点了
     *
     * 思路一 ：中序获取查询想查找节点的父节点
     * 思路二 ：根节点开始比较（充分运用树的性质）
     *
     * 此处采用思路一
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
                    //父节点标记要删除的子节点的边 true 左， false 右
                    temp.childSideFlag = true;
                    return temp;
                }
            }

            if (temp.right != null) {
                if (temp.right.data == data) {
                    //父节点标记要删除的子节点的边 true 左， false 右
                    temp.childSideFlag = false;
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
class BSTNode {

    public BSTNode left;

    public BSTNode right;

    /**
     * 用于删除时，父节点标记要删除的子节点的边 true 左， false 右
     */
    public boolean childSideFlag;

    /**
     * 权
     */
    public int data;

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", left=" + (left == null ? "null" : left.data) +
                ", right=" + (right == null ? "null" : right.data) +
                ", childSideFlag=" + childSideFlag +
                '}';
    }
}
