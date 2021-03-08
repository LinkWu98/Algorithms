package cn.link.exercise;

import cn.link.data.structure.linkedlist.DoublyLinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 约瑟夫环问题
 * 问题：
 * 设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到 m 的那个人出列，它的下一位又从1开始报数，
 * 数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 *
 * @author Link50
 * @version 1.0
 * @date 2021/2/20 14:04
 */
public class Joseph {

    /**
     * 连续的编号
     */
    private int[] arr;

    /**
     * 从哪个编号开始
     */
    private int startFrom;

    /**
     * 数组解决获取出队顺序
     *
     * 核心思路：出队后标记，下次数过来直接跳过
     *
     * 实现方法有很多，这里借助辅助对象DequeueObj4Arr，标记已出队，只复制一次数组，效率高
     *
     * @param arr       连续的编号
     * @param startFrom 从哪个编号开始
     * @param dequeueNo 数到几出队
     */
    public String getDequeueSequenceByArray(int[] arr, int startFrom, int dequeueNo) {

        if (arr.length == 0 || startFrom < 1 || startFrom > arr.length || dequeueNo < 0) {
            System.out.println("Wrong parameter.");
            return null;
        }

        DequeueObj4Arr[] dequeueObj4ArrArr = new DequeueObj4Arr[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dequeueObj4ArrArr[i] = new DequeueObj4Arr(arr[i], false);
        }

        //record dequeue code
        List<Integer> dequeSequence = new ArrayList<>();

        int tempNo = 1;
        for (int i = startFrom - 1; i < dequeueObj4ArrArr.length; i++) {

            //已出队，继续遍历，不自增编号
            if (dequeueObj4ArrArr[i].dequeuedFlag) {
                //到头了，从头遍历(环形)
                if (i == dequeueObj4ArrArr.length - 1) {
                    //会++
                    i = -1;
                }
                continue;
            }

            if (tempNo == dequeueNo) {

                dequeueObj4ArrArr[i].dequeuedFlag = true;
                dequeSequence.add(dequeueObj4ArrArr[i].code);
                //temp重置
                tempNo = 1;

            } else {

                tempNo++;

            }

            //到头了，从头遍历(环形)
            if (i == dequeueObj4ArrArr.length - 1) {
                //会++
                i = -1;
            }

            if (dequeSequence.size() == dequeueObj4ArrArr.length) {
                break;
            }

        }

        return dequeSequence.toString();

    }

    /**
     * 通过环形链表解决出队问题
     *
     * 核心思路:出队前，当前链表的上一个指向下一个
     *
     * @param arr       连续的编号
     * @param startFrom 从哪个编号开始
     * @param dequeueNo 数到几出队
     */
    public String getDequeueSequenceByDoublyLinkedList(Integer[] arr, int startFrom, int dequeueNo) throws Exception {

        if (arr.length == 0 || startFrom <= 0 || dequeueNo <= 0) {
            System.out.println("Wrong parameter.");
        }

        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addAll(arr);
        return doublyLinkedList.getJosephDequeueSequence(startFrom, dequeueNo);

    }

    private class DequeueObj4Arr {

        /**
         * 编号
         */
        int code;

        /**
         * 是否已出队
         */
        boolean dequeuedFlag;

        public DequeueObj4Arr(int code, boolean dequeuedFlag) {
            this.code = code;
            this.dequeuedFlag = dequeuedFlag;
        }
    }

}
