package com.vivek.add_two_numbers;

public class Driver {

    public static void main(String[] args) {
        Driver d = new Driver();
        ListNode l1 = new ListNode(5, new ListNode(6, new ListNode(7)));
        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(d.addTwoNumbers(l1, l2));
        return;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
         * digits are in reverse order so will have to read the 
         */
        if (l1 == null ) return l2;
        else if (l2 == null) return l1;

        else {

            int carry = 0;
            ListNode next = new ListNode(0);
            ListNode returnList = next;
            while (l1 != null || l2 != null ) { // while entries in either of the list remains, we will iterate over these entries
                // next = new ListNode();
                int sum = 0;
                sum += (l1 != null? l1.val : 0);
                sum += (l2 != null? l2.val : 0);
                sum += carry;
                carry = 0;
                if (sum > 19) throw new IllegalArgumentException();
                else if (sum > 9) {
                    carry = 1;
                    sum = sum % 10;
                }
                next.val = sum;// = new ListNode(sum)
                l1 = l1.next;
                l2 = l2.next;
                next.next = new ListNode();
                next = next.next;
            }
            // if carry is left then we have to add one more node
            if ( carry > 0 ) {
                next.val = carry;
            }
            // or create one more list node
            // if (carry > 0 ) {
            //     if ( l1 != null || l2 != null ) {
            //         if (l1 != null ) {
            //             l1.val
            //             next.next = l2;
            //         } else if (l2 != null ) {
            //             next.next = l1;
            //         }
            //     } else {
            //         next = new ListNode(1);
            //     }
            // }
            // else {
            //     if (l1 != null ) {
            //         next.next = l2;
            //     } else if (l2 != null ) {
            //         next.next = l1;
            //     }
            // }
            // while (l1.next != null && l2.next != null) {
            //     l1 = l1.next;
            //     l2 = l2.next;

            //     if ( l1 == null ) {
            //         next.next = l2;
            //     } else if (l2 == null ) {
            //         next.next = l1;
            //     } else {
            //         next.next = new ListNode(l1.val + l2.val);
            //         next = next.next;
            //     }
            // }
            return returnList;
        }
    }
}
