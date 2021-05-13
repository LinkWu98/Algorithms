package cn.link.data.structure.tree;

import lombok.Data;
import lombok.ToString;

/**
 * 树的节点
 *
 * @author link
 * @version 1.0
 * @date 2021/5/13 11:39 下午
 */
@Data
@ToString
public class Node<T> {

    public Node<T> left;

    public Node<T> right;

    /**
     * 父节点
     */
    public Node<T> fatherNode;

    /**
     * 权
     */
    public T data;

}
