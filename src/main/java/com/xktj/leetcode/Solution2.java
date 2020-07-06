package com.xktj.leetcode;

import java.util.List;

public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       int  i =  l1.val + l2.val;
    }
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    public static void main(String[] args){
        /**
         * (2 -> 4 -> 3) +
         * (5 -> 6 -> 4)
         */
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        l1.next = new ListNode(4);
        l2.next =new ListNode(6);

    }
}
