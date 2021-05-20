package cn.link.data.structure.tree;

/**
 * 顺序存储二叉树
 *
 * 数组顺序存储 ->  二叉树
 *
 * 数组中下标为 i 的元素，其左子节点为 2i+1，右为 2i+2，父节点为 (i-1) / 2
 *
 */
public class ArrayBinaryTree {

    /**
     * 数组
     */
    public int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

    public void preOrder() {
        preOrder(0);
    }
    /**
     * 前序遍历
     */
    public void preOrder(int index) {

        if (index >= arr.length) {
            return;
        }

        System.out.println(arr[index]);

        preOrder(2 * index + 1);
        preOrder(2 * index + 2);

    }

    public void infixOrder() {
        infixOrder(0);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(int index) {

        if (index >= arr.length) {
            return;
        }

        infixOrder(2 * index + 1);
        System.out.println(arr[index]);
        infixOrder(2 * index + 2);

    }

    public void postOrder() {
        postOrder(0);
    }

    /**
     * 后序遍历
     */
    public void postOrder(int index) {

        if (index >= arr.length) {
            return;
        }

        postOrder(2 * index + 1);
        postOrder(2 * index + 2);
        System.out.println(arr[index]);

    }

}
