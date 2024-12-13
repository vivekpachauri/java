package com.vivek.merge_sorted_lists;

import com.vivek.add_two_numbers.ListNode;

public class Driver {

    public static void main(String[] args) {
        Driver driver = new Driver();
        ListNode listNodeOne = new ListNode(1, new ListNode(2, new ListNode(3, null)));
        ListNode listNodeTwo = new ListNode(4, new ListNode(5, new ListNode(6, null)));
        System.out.println(driver.mergeTwoLists(listNodeOne, listNodeTwo));
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //corner case check
        if ( list1 == null && list2 == null ) {
            return null;
         }
         else if (list1 == null ) {
            return list2;
         }
         else if (list2 == null ) {
            return list1;
         }
        /*
         * starting - any of the lists could be the beginning, check the two heads and pick the smaller one
         */
        ListNode start;
        if (list1.getVal() < list2.getVal()) {
            start = list1;
            mergeListRecursive(list1.getNext(), list2, start);
        }
        else {
            start = list2;
            mergeListRecursive(list1, list2.getNext(), start);
        }
        System.out.println("final result " + start);

        

        /*
         * next check which of the element is smaller
         */
        return start;
    }

    private void mergeTwoListsIterative(ListNode list1, ListNode list2, ListNode result) {

        while (list1 != null && list2 != null ) {

        }

    }

    private void mergeListRecursive(ListNode list1, ListNode list2, ListNode result) {

        //base case
        //if both the lists are empty then return the result

        if (list1 == null && list2 == null ) {
            return;
        }

        //if first is empty then simply tack on the second one and return the result
        else if (list1 == null ) {
            result.setNext(list2);
            return;
        }

        //if second is empty then simply tack on the first one and return the result
        else if (list2 == null ) {
            result.setNext(list1);
            return;
        }
        else {
            //pick the next node from the two lists and make the recursive call
            if (list1.getVal() < list2.getVal()) {
                 result.setNext(list1);
                 mergeListRecursive(list1.getNext(), list2, result.getNext());
            }
            else {
                result.setNext(list2);
                mergeListRecursive(list1, list2.getNext(), result.getNext());
            }
        }



    }
}
