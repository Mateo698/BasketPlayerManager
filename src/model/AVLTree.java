package model;

public class AVLTree<T extends Comparable<T>> {
	private Node<T> root;
	
	public AVLTree() {
		root = null;
	}
	
	public int height(Node n) {
		if(n == null) {
			return 0;
		}else {
			return n.getHeight();
		}
	}
	
	public int max(int one, int two) {
		 return (one > two) ? one : two;
	}
	
	public Node<T> rightRotate(Node<T> y) {
		Node<T> x = y.getLeft();
        Node<T> T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        return x;
	}
	
	 public Node<T> leftRotate(Node<T> x) {
	        Node<T> y = x.getRight();
	        Node<T> T2 = y.getLeft();
	        y.left = x;
	        x.right = T2;
	        x.height = max(height(x.left), height(x.right)) + 1;
	        y.height = max(height(y.left), height(y.right)) + 1;
	 
	        // Return new root
	        return y;
	    }
}
