package cn.link.data.structure.tree;

/**
 * 线索二叉树
 *
 * 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。
 * 根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 *
 * <p>
 * n个结点的二叉链表中含有 n+1 个空指针域
 * 公式 2n-(n-1) = n+1
 * 2n-(n-1)的理解：n个节点一共 2n 个指针，除根节点外每个节点用掉一个(n-1)，那么用到的节点就是 2n - (n - 1)
 *
 * 前驱结点：对应的遍历顺序中的上一个节点
 * 后继节点：对应的遍历顺序中的下一个节点
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/20 15:36
 */
public class ThreadedBinaryTree<T> {

    public Node<T> root;

    private Node<T> prev;

    /**
     * 线索化二叉树
     */
    public void threadNodes() {
        threadNodes(root);
    }

    /**
     * 线索化中序二叉树
     */
    public void threadNodes(Node<T> node) {

        if (node == null) {
            return;
        }

        threadNodes(node.left);

        if (node.left == null && prev != null) {
            //前驱节点
            node.left = prev;
            node.leftType = 1;
        }

        if (prev != null && prev.right == null) {
            //后继节点
            prev.right = node;
            prev.rightType = 1;
        }

        prev = node;

        threadNodes(node.right);

    }

    /**
     * 线索化后序二叉树
     */
    public void threadNodesPostOrder() {

        threadNodesPostOrder(root);

    }

    public void threadNodesPostOrder(Node<T> node) {

        if (node == null) {
            return;
        }

        threadNodesPostOrder(node.left);

        threadNodesPostOrder(node.right);

        if (node.left == null) {
            node.left = prev;
            node.leftType = 1;
        }

        if (prev != null && prev.right == null) {
            prev.right = node;
            prev.rightType = 1;
        }

        prev = node;

    }

    /**
     * 遍历线索节点
     */
    public void threadNodesTraversal() {

        Node<T> node = root;
        while (node != null) {

            //为了输出线索节点本身
            while (node.leftType == 0) {
                node = node.left;
            }

            System.out.println(node.data);

            //为了输出线索节点的后一个节点
            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node.data);
            }

            node = node.right;

        }

    }

}
