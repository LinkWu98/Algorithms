package cn.link.leetcode.linkedlist;

import lombok.val;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class 两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode current = new ListNode();
        head.next = current;
        //当前是否进一位
        boolean upFlag = false;
        while (true) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int thisLoopVal = l1Val + l2Val + (upFlag ? 1 : 0);
            upFlag = thisLoopVal >= 10;
            current.val = thisLoopVal % 10;
            l1 = l1 == null || l1.next == null ? null : l1.next;
            l2 = l2 == null || l2.next == null ? null : l2.next;
            if (l1 == null && l2 == null) {
                //最后一个了，但还要进一位的情况
                current.next = upFlag ? new ListNode(1) : null;
                break;
            }
            current.next = new ListNode();
            current = current.next;
        }
        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
