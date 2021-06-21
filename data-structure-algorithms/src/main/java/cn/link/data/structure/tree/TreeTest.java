package cn.link.data.structure.tree;

import org.junit.Test;

/**
 * @author Link50
 * @version 1.0
 * @date 2021/5/14 12:51
 */
public class TreeTest {

    @Test
    public void testTreeOrders() {

        Tree<Integer> tree = new Tree<>();
        Tree.Node<Integer> root = new Tree.Node<>();
        tree.setRoot(root);
        root.setData(2);
        Tree.Node<Integer> left = new Tree.Node<>();
        Tree.Node<Integer> leftLeft = new Tree.Node<>();
        left.setData(1);
        leftLeft.setData(0);
        left.setLeftChild(leftLeft);
        root.setLeftChild(left);
        Tree.Node<Integer> right = new Tree.Node<>();
        right.setData(3);
        root.setRightChild(right);

        //tree.preOrderRecursive();
        //tree.infixOrderRecursive();
        //tree.postOrderRecursive();

        //tree.preOrder();
        tree.remove(1);
        tree.preOrder();
        //tree.infixOrder();
        //tree.postOrder();

    }

    @Test
    public void testArrayTree(){
        ArrayBinaryTree tree = new ArrayBinaryTree();
        //tree.preOrder();
        //tree.infixOrder();
        tree.postOrder();

    }

    @Test
    public void testBST(){

        BinarySortTree bst = new BinarySortTree();
        bst.add(9);
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(7);
        bst.infixTraversal();

        bst.remove(5);
        bst.infixTraversal();
        bst.remove(7);
        bst.infixTraversal();
    }

}
