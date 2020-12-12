/*
https://leetcode.com/problems/add-two-numbers/submissions/
*/
package cn.businside.rong.leetcode.list;

public class SumTwoList {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode resultPoint = result;
        ListNode lastPoint = result;

        while (l1 != null || l2 != null) {
            result.val = result.val + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            ListNode next = new ListNode(0);

            next.val = result.val / 10;
            result.val = result.val % 10;

            result.next = next;
            lastPoint = result;
            result = next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (result.val == 0)
            lastPoint.next = null;

        return resultPoint;

    }

    /*
    1ms
    */
    public ListNode addTwoNumbersLessRam(ListNode l1, ListNode l2) {

        ListNode resultPoint = l1;
        // ListNode lastPoint = l1;

        while (l2 != null) {

            l1.val = l1.val + l2.val;

            if (l1.next == null) {
                l1.next = l2.next;
                l2.next = null;
            }

            if (l1.val / 10 > 0) {
                if (l1.next != null) {
                    l1.next.val = l1.next.val + l1.val / 10;
                } else {
                    l1.next = l2.next != null ? l2.next : new ListNode(0);
                    l1.next.val = l1.next.val + l1.val / 10;
                    l2.next = null;

                }

                l1.val = l1.val % 10;
            }
            // lastPoint = l1;

            l1 = l1.next;
            l2 = l2.next;

        }

        while (l1 != null && l1.val >= 10) {
            if (l1.next == null) {
                l1.next = new ListNode(l1.val / 10);
            } else
                l1.next.val = l1.next.val + l1.val / 10;

            l1.val = l1.val % 10;

            l1 = l1.next;
        }

        return resultPoint;

    }

}