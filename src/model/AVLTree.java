package model;

import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> {
	private Node<T> root;

	public AVLTree() {
		setRoot(null);
	}

	public int height(Node<T> n) {
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
		y.setLeft(x);
		x.setRight(T2);
		x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
		y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
		return y;
	}

	int getBalance(Node<T> N) {
		if (N == null)
			return 0;

		return height(N.getLeft()) - height(N.getRight());
	}
	
	public Node<T> search(Node<T> root, T key){
	    if (root==null || root.getKey().compareTo(key)==1)
	        return root;
	    if (root.getKey().compareTo(key)<0)
	       return search(root.getRight(), key);
	    return search(root.getLeft(), key);
	}
	
	public void add(T key) {
			root = insert(root, key);
	}
	
	public Node<T> insert(Node<T> node, T key) {
		if (node == null)
			return (new Node<T>(key));
		if (key.compareTo(node.getKey())<0)
			node.setLeft(insert(node.getLeft(), key));
		else if (key.compareTo(node.getKey())>0)
			node.setRight(insert(node.getRight(), key));
		else
			return node;
		node.setHeight(1 + max(height(node.getLeft()),height(node.getRight())));
		int balance = getBalance(node);
		if (balance > 1 && key.compareTo(node.getLeft().getKey())<0)
			return rightRotate(node);
		if (balance < -1 && key.compareTo(node.getRight().getKey())>0)
			return leftRotate(node);
		if (balance > 1 && key.compareTo(node.getRight().getKey())>0) {
			node.setLeft(leftRotate(node.getLeft()));
			return rightRotate(node);
		}
		if (balance < -1 && key.compareTo(node.getLeft().getKey())<0) {
			node.setRight(rightRotate(node.getRight()));
			return leftRotate(node);
		}
		return node;
	}


	public void preOrder(Node<T> node) {
		if (node != null) {
			System.out.print(node.getKey() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}

	public Node<T> minValueNode(Node<T> node)
	{
		Node<T> current = node;
		while (current.getLeft() != null)
			current = current.getLeft();

		return current;
	}

	@SuppressWarnings("unused")
	public Node<T> deleteNode(Node<T> root, T key){
		if (root == null)
			return root;
		if (key.compareTo(root.getKey())<0)
			root.setLeft(deleteNode(root.getLeft(), key));
		else if (key.compareTo(root.getKey())>0)
			root.setRight(deleteNode(root.getRight(), key));
		else {
			if ((root.getLeft() == null) || (root.getRight() == null)){
				Node<T> temp = null;
				if (temp == root.getLeft())
					temp = root.getRight();
				else
					temp = root.getLeft();
				if (temp == null){
					temp = root;
					root = null;
				}
				root = temp;
			}
			else
			{	
				Node<T> temp = minValueNode(root.getRight());
				root.setKey(temp.getKey());
				root.setRight(deleteNode(root.getRight(), temp.getKey()));
			}
		}
		if (root == null) {
			return root;
		}
		
		root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);
		int balance = getBalance(root);
		if (balance > 1 && getBalance(root.getLeft()) >= 0)
			return rightRotate(root);
		if (balance > 1 && getBalance(root.getLeft()) < 0)
		{
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}
		if (balance < -1 && getBalance(root.getRight()) <= 0)
			return leftRotate(root);
		if (balance < -1 && getBalance(root.getRight()) > 0)
		{
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}
		return root;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	
}
