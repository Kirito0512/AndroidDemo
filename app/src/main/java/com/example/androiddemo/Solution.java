package com.example.androiddemo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private static final String TAG = "Solution";
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        TreeNode result = lowestCommonAncestor_test(root, root.left, root.right);
        System.out.println("main: result = " + result.val);

        ListNode node = new ListNode(2);
        node.next = new ListNode(3);
        node.next.next = new ListNode(4);
        int[] array = reversePrint(node);
        System.out.println("main: array = " + array);
    }
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        if (root == null) {
//            return Collections.emptyList();
//        }
//
//        List<List<Integer>> mResult = new ArrayList<>();
//        mResult.add(Arrays.asList(root.val));
//        List<List<Integer>> mResult1 = levelOrder(root.left);
//        List<List<Integer>> mResult2 = levelOrder(root.right);
//        mResult.addAll(mergeList(mResult, mResult2));
//        return mResult;
//    }
//
//    private List<List<Integer>> mergeList(List<List<Integer>> list1, List<List<Integer>> list2) {
//        if (list1 == null || list1.size() == 0) {
//            return list2;
//        }
//        if (list2 == null || list2.size() == 0) {
//            return list1;
//        }
//        List<List<Integer>> mResule = new ArrayList<>();
//        int maxLevel = Math.max(list1.size(), list2.size());
//        for (int i = 0; i < maxLevel ; i++) {
//            if (i < list1.size() && i < list2.size()) {
//                List<Integer> temp = list1.get(i);
//                temp.addAll(list2.get(i));
//                mResule.add(temp);
//            } else if (i < list1.size()) {
//                mResule.add(list1.get(i));
//            } else {
//                mResule.add(list2.get(i));
//            }
//        }
//        return mResule;
//    }

    public static TreeNode lowestCommonAncestor_test(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode node1 = lowestCommonAncestor_test(root.left, p, q);
        TreeNode node2 = lowestCommonAncestor_test(root.right, p, q);
        if (node1 != null && node2 != null)
            return root;
        if (node1 != null) {
            return node1;
        } else {
            return node2;
        }

//        List<TreeNode> listp = inOrderListFindNode(root, p);
//        List<TreeNode> listq = inOrderListFindNode(root, q);
//        int size = Math.min(listp.size(), listq.size());
//        TreeNode result = null;
//        for (int i = 0; i < size; i++) {
//            if (listp.get(i).val == listq.get(i).val) {
//                result = listp.get(i);
//            } else {
//                break;
//            }
//        }
//        return result;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listp = inOrderListFindNode(root, p);
        List<TreeNode> listq = inOrderListFindNode(root, q);
        int size = Math.min(listp.size(), listq.size());
        TreeNode result = null;
        for (int i = 0; i < size; i++) {
            if (listp.get(i).val == listq.get(i).val) {
                result = listp.get(i);
            } else {
                break;
            }
        }
        return result;
    }

    private static List<TreeNode> inOrderListFindNode(TreeNode root, TreeNode target) {
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> mList = new LinkedList<>();
        while(root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                mList.add(root);
                if (root.val == target.val) {
                    break;
                }
                root = root.left;
            } else {
                while(!stack.isEmpty()) {
                    root = stack.peek();
                    if (root .right == null) {
                        stack.pop();
                        mList.remove(root);
                    } else {
                        root = root.right;
                        break;
                    }
                }
            }
        }
        return mList;
    }

    public static int[] reversePrint(ListNode head) {
        ListNode reverseHead = reverseList(head);
        int size = 0;
        ListNode temp = reverseHead;
        while(temp != null) {
            size++;
            temp = temp.next;
        }

        int[] result = new int[]{size};
        for (int i = 0; i<size; i++) {
            result[i] = reverseHead.val;
            reverseHead = reverseHead.next;
        }
        return result;
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pref = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pref;
            pref = head;
            head = next;
        }
        return pref;
    }
}
