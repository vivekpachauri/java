package com.vivek.reverse_linked_list_in_range;

import com.vivek.ListNode;

public class Solution {
    /*
     * Given the head of a singly linked list and two integers left and right where
     * left <= right, reverse the nodes of the list from position left to position
     * right, and return the reversed list.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: head = [1,2,3,4,5], left = 2, right = 4
     * Output: [1,4,3,2,5]
     * Example 2:
     * 
     * Input: head = [5], left = 1, right = 1
     * Output: [5]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is n.
     * 1 <= n <= 500
     * -500 <= Node.val <= 500
     * 1 <= left <= right <= n
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        /*
         * node 1 -> node 2 -> node 3
         * prev curr
         */
        /*
         * iterate over the list elements until we reach the index where we are to start
         * reversing
         * at that point we need to keep the reference to the previous element
         * 
         * then we call a sub-function to reverse the elements and return the head of
         * the new list
         * and we need to keep track of the original head
         * after reversing the final tail of the new head need to be set as the tail of
         * the original head
         */

        ListNode prev = null, curr = null, next = null;
        int index = 1;
        prev = head;
        curr = head;
        for ( int i = 1; i < left; i++ ) {
            ListNode temp = curr.next;
            prev = curr;
            curr = temp;
        }
        //at this point we have the head of the list to reverse, we have the number of elements to reverse, and the reference of the previosu element
        //which should be updted to point to the new head

        if ( left == 1 ) {
            head = reverse(curr, (right-left));
        }
        else {
            prev.next = reverse(curr, (right - left));
        }
        // prev.next = null;
        // next = curr.next;

        // do {
        //     if (index >= left && index <= right) {
        //         ListNode temp = curr.next;
        //         curr.next = prev;
        //         prev = curr;
        //         curr = temp;
        //         index++;
        //     } else {
        //         prev = curr;
        //         curr = curr.next;
        //     }
        // } while (curr != null);
        return head;
    }

    private ListNode reverse(ListNode head, int num) {
        ListNode prev = head, curr = head.next;
        for ( int i = 0; i < num; i++ ) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        head.next = curr;
        return prev;
    }

    public static void main(String[] args) {
        ListNode node6 = new ListNode(6);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode original = node1;
        while (original != null) {
            System.out.print(original.val + " ");
            original = original.next;
        }
        System.out.println();

        Solution s = new Solution();
        ListNode reversedHead = s.reverseBetween(node1, 1, 4);

        while (reversedHead != null) {
            System.out.print(reversedHead.val + " ");
            reversedHead = reversedHead.next;
        }
    }
}
