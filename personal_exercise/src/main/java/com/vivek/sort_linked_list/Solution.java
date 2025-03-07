package com.vivek.sort_linked_list;

import com.vivek.ListNode;

public class Solution {

    /*
     * Given the head of a linked list, return the list after sorting it in
     * ascending order.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: head = [4,2,1,3]
     * Output: [1,2,3,4]
     * Example 2:
     * 
     * 
     * Input: head = [-1,5,3,4,0]
     * Output: [-1,0,3,4,5]
     * Example 3:
     * 
     * Input: head = []
     * Output: []
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is in the range [0, 5 * 104].
     * -105 <= Node.val <= 105
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        } else if (head.next == null) {
            return head;
        }
        ListNode mid = findMiddle(head);
        // System.out.println("printing first half list");
        // ListNode temp = head;
        // while (temp != null ) {
        // System.out.print(temp.val + " -> ");
        // temp = temp.next;
        // }
        // System.out.println();
        // System.out.println("printing second half list");
        // temp = mid;
        // while (temp != null ) {
        // System.out.print(temp.val + " -> ");
        // temp = temp.next;
        // }
        // System.out.println();
        return mergeSort(head, mid);
    }

    private ListNode mergeSort(ListNode first, ListNode second) {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        } else {
            ListNode sortedFirst = null;
            if (first.next != null) {
                ListNode firstMid = findMiddle(first);
                sortedFirst = mergeSort(first, firstMid);
            } else {
                sortedFirst = first;
            }
            ListNode sortedSecond = null;
            if (second.next != null) {
                ListNode secondMid = findMiddle(second);
                sortedSecond = mergeSort(second, secondMid);
            } else {
                sortedSecond = second;
            }
            return merge(sortedFirst, sortedSecond);

        }
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head, prev = head;
        while (fast != null && fast.next != null) {

            // System.out.println(prev.val + ", " + slow.val + ", " + fast.val);
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        return slow;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            ListNode newHead = null;
            if (list1.val <= list2.val) {
                newHead = list1;
                list1 = list1.next;
            } else {
                newHead = list2;
                list2 = list2.next;
            }
            newHead.next = merge(list1, list2);
            return newHead;
        }
    }

    private ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        boolean sorted = false;
        while (!sorted) {
            ListNode temp = head;
            // System.out.println("printing full list");
            // while (temp != null ) {
            // System.out.print(temp.val + " -> ");
            // temp = temp.next;
            // }
            // System.out.println();
            sorted = true;
            // check if head node need to be replaced
            if (head.val > head.next.val) {
                head = swap(head, head.next);
            }
            ListNode prev = head;
            ListNode first = head.next;
            ListNode next = first.next;
            while (next != null) {
                if (first.val > next.val) {
                    sorted = false;
                    first = swap(first, next);
                    prev.next = first;
                }
                prev = first;
                first = next;
                next = first.next;
            }
        }
        return head;
    }

    private ListNode swap(ListNode first, ListNode second) {
        // ListNode temp = second.next;
        first.next = second.next;
        second.next = first;
        // System.out.println("after swapping " + second.val + " -> " + second.next.val
        // + " -> " + second.next.next);
        return second;
    }
}
