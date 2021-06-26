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
     * 线索化中序二叉树
     */
    public void infixThread() {
        infixThread(root);
    }

    /**
     * 线索化中序二叉树
     */
    public void infixThread(Node<T> node) {

        if (node == null) {
            return;
        }

        infixThread(node.left);

        if (node.left == null) {
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

        infixThread(node.right);

    }

    /**
     * 中序遍历线索节点
     */
    public void infixTraversal() {

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

    /**
     * 前序线索化二叉树
     */
    public void preThread() {

        preThread(root);

    }

    public void preThread(Node<T> node) {

        if (node == null) {
            return;
        }

        if (node.left == null) {
            node.left = prev;
            node.leftType = 1;
        }

        if (prev != null && prev.right == null) {
            prev.right = node;
            prev.rightType = 1;
        }

        prev = node;

        //前序线索化给子节点的left/right赋值的缘故，会进入死循环，因此添加判断，不是线索再进入判断
        if (node.leftType == 0) {
            preThread(node.left);
        }

        if (node.rightType == 0) {
            preThread(node.right);
        }

    }

    /**
     * 前序遍历线索节点
     * 左指针不为空，且不是前驱节点，则遍历
     * 右指针不空，且是后继节点，则遍历
     */
    public void preTraversal() {

        Node<T> node = root;
        while (node != null) {

            //为了输出线索节点本身
            while (node.leftType == 0) {
                System.out.println(node.data);
                node = node.left;
            }

            System.out.println(node.data);

            node = node.right;

        }

    }

    /**
     * 后序线索化二叉树
     */
    public void postThread() {

        postThread(root);

    }

    public void postThread(Node<T> node) {

        if (node == null) {
            return;
        }

        postThread(node.left);

        postThread(node.right);

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
     * 后续遍历线索节点
     */
    public void postTraversal() {

        //TODO Node需要添加一个parent属性，记录父节点
        //先遍历左边到底，一直往右找直到不是线索节点，再遍历右边同理，最后输出root

    }

}
