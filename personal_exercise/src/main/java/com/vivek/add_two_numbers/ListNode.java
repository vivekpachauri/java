package com.vivek.add_two_numbers;

public class ListNode {
     int val;
     ListNode next;
     public ListNode() {}
     public ListNode(int val) { this.val = val; }
     public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    @Override
        public String toString() {
            return "ListNode [val=" + val + ", next=" + next + "]";
        }
    public int getVal() {
        return val;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + val;
        result = prime * result + ((next == null) ? 0 : next.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListNode other = (ListNode) obj;
        if (val != other.val)
            return false;
        if (next == null) {
            if (other.next != null)
                return false;
        } else if (!next.equals(other.next))
            return false;
        return true;
    }
    public void setVal(int val) {
        this.val = val;
    }
    public ListNode getNext() {
        return next;
    }
    public void setNext(ListNode next) {
        this.next = next;
    }
    }
