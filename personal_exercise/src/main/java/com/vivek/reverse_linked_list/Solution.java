package com.vivek.reverse_linked_list;

import com.vivek.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverse(ListNode head) {
        /*
         *  node 1 -> node 2 -> node 3
         *  prev      curr
         */
        ListNode prev = null, curr = null, next = null;
        prev = head;
        curr = head.next;
        prev.next = null;
        //next = curr.next;

        do {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        while (curr != null );
        return prev;
    }

    public static void main (String[] args) {
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        Solution s = new Solution();
        ListNode reversedHead = s.reverse(node1);

        while (reversedHead != null) {
            System.out.print(reversedHead.val + " ");
            reversedHead = reversedHead.next;
        }
    }

    public ListNode reverseAgain(ListNode head) {
        ListNode prev = null, curr = null;
        curr = head;
        while ( curr != null ) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
