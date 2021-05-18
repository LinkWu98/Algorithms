package cn.link.data.structure.tree;

import cn.link.data.structure.linkedlist.Stack;

/**
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/13 12:57
 */
public class BinarySortTree<T>  extends Tree<T> {

    /**
     * 根节点
     */
    public Node<T> root;

    /**
     * 非递归前序遍历
     *
     * 遍历顺序其实是：根节点 -> 左子树 -> 右子树 (树的节点也是从左到右去打印)
     *
     * 借助栈实现
     *
     */
    /**
     *
     * 前、中、后序遍历，从根节点开始遍历左边的子树，再遍历右边的子树，按遍历的不同，改变输出根节点的时机即可
     *
     * 实现：从根节点开始递归，先递归左节点，到底后回溯，再递归右节点，再回溯
     *
     */

}
