package cn.link.datastructure.queue;

/**
 * 队列接口，定义一些通用函数
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/28 13:29
 */
public interface Queue {

    /**
     * 添加元素
     *
     * @param obj 元素
     */
    void add(Object obj);

    /**
     * 移除元素
     *
     * @return 被移除的元素
     */
    Object remove();

    /**
     * 空校验
     *
     * @return 空与否
     */
    boolean isEmpty();

    /**
     * 实际元素个数
     *
     * @return 实际元素个数
     */
    int size();

}
