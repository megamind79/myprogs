package datastructures;

import java.util.*;

public class BinaryTree {

    static class Node<E> {
        E data;
        Node left, right;
        int height, leftHeight, rightHeight;

        Node (E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    /**
     * Build a binary tree of min height with a sorted array
     * @param sortedArray an array sorted in increasing order
     * @param l lower bound of array to be sorted
     * @param h upper bound of array to be sorted
     * @return the root of the binary tree so formed
     */
    Node buildBinaryTree (int sortedArray[], int l, int h) {
        if ((h < l) || (l < 0))
            return null;
        int mid = (l+h)/2;
        root = new Node<Integer>(sortedArray[mid]);
        BinaryTree leftTree = new BinaryTree();
        BinaryTree rightTree = new BinaryTree();
        root.left = leftTree.buildBinaryTree(sortedArray, l, mid-1);
        root.right = rightTree.buildBinaryTree(sortedArray, mid+1, h);

        return root;
    }

    void printLOT () {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print (node.data + " ");
        if (node.left != null)  queue.add(node.left);
        if (node.right != null) queue.add(node.right);
        }
        System.out.println ();
    }

    void printInorderTraversal (Node node) {
        if (node == null)   return;
        printInorderTraversal(node.left);
        System.out.print (node.data + " ");
        printInorderTraversal(node.right);
    }

    /**
     * inorder : left, root, right
     */
    void printInorderTraversal () {
        printInorderTraversal(root);
        System.out.println ();
    }

    /**
     * iterative inorder traversal
     */
    void printInOrderTraversal () {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print (node.data + " ");

            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
    }

    void printPreorderTraversal (Node node) {
        if (node == null)   return;
        System.out.print (node.data + " ");
        printPreorderTraversal(node.left);
        printPreorderTraversal(node.right);
    }

    /**
     * preorder : root, left, right
     */
    void printPreorderTraversal () {
        printPreorderTraversal(root);
        System.out.println ();
    }

    /**
     * iterative preorder traversal
     */
    void printPreOrderTraversal () {
        Stack<Node> stack = new Stack<>();
        if (root == null)   return;

        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print (node.data + " ");
            if (node.right != null) stack.push(node.right);
            if (node.right != null) stack.push(node.left);
        }
    }

    void printPostorderTraversal (Node node) {
        if (node == null)   return;
        printPostorderTraversal(node.left);
        printPostorderTraversal(node.right);
        System.out.print (node.data + " ");
    }

    /**
     * postorder : left, right, root
     */
    void printPostorderTraversal () {
        printPostorderTraversal(root);
        System.out.println ();
    }

    int getHeightIterative () {
        if (root == null)   return 0;

        int height = 0;
        Queue<Node> queue = new LinkedList<>();
        Node marker = new Node(-1);
        queue.add(root);
        queue.add(marker);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == marker) {
                height++;
                if (queue.isEmpty())    return height;
                queue.add(marker);
            } else {
                if (node.left != null)  queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return height;
    }

    int getHeight (Node node) {
        if (node == null)   return 0;
        return (1 + Math.max(getHeight(node.left), getHeight(node.right)));
    }

    int getHeightRecursive () {
        return getHeight(root);
    }

    int getHeightDP (Node node) {
        return node.height;
    }

    static void calculateHeights (Node node) {
        if (node == null)
            return;
        calculateHeights(node.left);
        calculateHeights(node.right);
        node.leftHeight = node.left == null?0:node.left.height;
        node.rightHeight = node.right == null?0:node.right.height;
        node.height = 1 + Math.max(node.leftHeight, node.rightHeight);
    }
    // similarly you can get both recursive and iterative ways to get size
    // of the binary tree - no. of nodes in the tree.

    // to build a binary tree with preorder and inorder tree traversal
    // similarly something with postorder and inorder; level order and preorder can be built
    // notice that inorder is compulsory to build a binary tree with another traversal
    static Node buildBinaryTreeWithPreorderList (LinkedList<Character> inorder, LinkedList<Character> preorder) {
        Character rootChar = preorder.get(0);
        Node root = new Node(rootChar);
        int inorderRootIndex = inorder.indexOf(rootChar);

        LinkedList<Character> leftTreeInorder = new LinkedList<>();
        for (int i = 0; i<inorderRootIndex; i++)
            leftTreeInorder.add(inorder.get(i));
        LinkedList<Character> rightTreeInorder = new LinkedList<>();
        for (int i = (inorderRootIndex+1); i<inorder.size(); i++)
            rightTreeInorder.add(inorder.get(i));

        //System.out.println ("For root " + rootChar + "\nleftInorder : " + leftTreeInorder + "\trightInorder : " + rightTreeInorder);

        LinkedList<Character> leftTreePreorder = new LinkedList<>();
        for (int i = 1; i<= inorderRootIndex; i++)
            leftTreePreorder.add(preorder.get(i));
        LinkedList<Character> rightTreePreorder = new LinkedList<>();
        for (int i = (inorderRootIndex+1); i<preorder.size(); i++)
            rightTreePreorder.add(preorder.get(i));

        //System.out.println ("For root " + rootChar + "\nleftPreorder : " + leftTreePreorder + "\trightPreorder : " + rightTreePreorder);

        if ((leftTreeInorder.size() > 0) && (leftTreePreorder.size() > 0))
            root.left = buildBinaryTreeWithPreorderList(leftTreeInorder, leftTreePreorder);

        if ((rightTreeInorder.size() > 0) && (rightTreePreorder.size() > 0))
            root.right = buildBinaryTreeWithPreorderList(rightTreeInorder, rightTreePreorder);

        return root;
    }

    int getDiameter (Node node) {
        if (node == null)
            return 0;
        int leftDiameter = getDiameter(node.left);
        int rightDiameter = getDiameter(node.right);

        return Math.max((node.leftHeight + node.rightHeight + 1), Math.max(leftDiameter, rightDiameter));
    }

    /**
     * The diameter of a tree (sometimes called the width) is the number of nodes on the longest
     * path between two leaves in the tree. Longest path either is diameter of left subtree, right
     * subtree or goes through the root
     * @return the diameter of the binary tree
     */
    int getDiameter () {
        return getDiameter(root);
    }

    public static void main (String args[]) {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.root = new Node<Integer>(1);
        binaryTree.root.left = new Node<Integer>(2);
        binaryTree.root.right = new Node<Integer>(3);
        binaryTree.root.left.left = new Node<Integer>(4);
        binaryTree.root.left.right = new Node<Integer>(5);
        // extra nodes added later
        binaryTree.root.left.right.left = new Node<Integer>(6);
        binaryTree.root.left.right.right = new Node<Integer>(7);
        binaryTree.root.right.right = new Node<Integer>(8);
        binaryTree.root.right.right.right = new Node<Integer>(9);
        binaryTree.root.right.right.right.right = new Node<Integer>(11);
        binaryTree.root.right.right.right.left = new Node<Integer>(10);
        binaryTree.root.right.right.right.left.left = new Node<Integer>(12);
        binaryTree.root.right.right.right.left.right = new Node<Integer>(13);

        calculateHeights(binaryTree.root);

        System.out.println ("height : " + binaryTree.getHeightRecursive());

        System.out.println ("diameter : " + binaryTree.getDiameter());

        // to build a binary tree from preorder and inorder trees
//        LinkedList<Character> inorder = new LinkedList<>();
//        LinkedList<Character> preorder = new LinkedList<>();
//
//        inorder.add('D');
//        inorder.add('B');
//        inorder.add('E');
//        inorder.add('A');
//        inorder.add('F');
//        inorder.add('C');
//
//        preorder.add('A');
//        preorder.add('B');
//        preorder.add('D');
//        preorder.add('E');
//        preorder.add('C');
//        preorder.add('F');
//
//        binaryTree.root = buildBinaryTreeWithPreorderList(inorder, preorder);

//        int sottedArray[] = {1, 2, 3, 4, 5, 6, 7};
//        datastructures.BinaryTree binaryTree1 = new datastructures.BinaryTree();
//        binaryTree1.buildBinaryTree(sottedArray, 0, 6);


    }

}
