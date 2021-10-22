package model;

public class Node<T extends Comparable<T>> {
	
	private T key;
	private int height;
	private Node<T> left,right;
	
	public Node(T ob) {
		setKey(ob);
		setHeight(1);
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}
}
