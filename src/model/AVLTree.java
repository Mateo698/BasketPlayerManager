package model;

public class AVLTree<T extends Comparable<T>> {
	Node<T> root;

	public AVLTree() {
		root = null;
	}

	public T Maximum() {
		Node<T> local = root;
		if (local == null)
			return null;
		while (local.getRight() != null)
			local = local.getRight();
		return local.getKey();
	}

	public T Minimum() {
		Node<T> local = root;
		if (local == null)
			return null;
		while (local.getLeft() != null) {
			local = local.getLeft();
		}
		return local.getKey();
	}

	private int depth(Node<T> node) {
		if (node == null)
			return 0;
		return node.getDepth();
		// 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
	}

	public Node<T> add(T data) {
		root = insert(root, data);
		switch (balanceNumber(root)) {
		case 1:
			root = rotateLeft(root);
			break;
		case -1:
			root = rotateRight(root);
			break;
		default:
			break;
		}
		return root;
	}

	public Node<T> insert(Node<T> node, T data) {
		if (node == null)
			return new Node<T>(data);
		if (node.getKey().compareTo(data) > 0) {
			node = new Node<T>(node.getKey(), insert(node.getLeft(), data),
					node.getRight());
			// node.setLeft(insert(node.getLeft(), data));
		} else if (node.getKey().compareTo(data) < 0) {
			// node.setRight(insert(node.getRight(), data));
			node = new Node<T>(node.getKey(), node.getLeft(), insert(
					node.getRight(), data));
		}
		// After insert the new node, check and rebalance the current node if
		// necessary.
		switch (balanceNumber(node)) {
		case 1:
			node = rotateLeft(node);
			break;
		case -1:
			node = rotateRight(node);
			break;
		default:
			return node;
		}
		return node;
	}

	private int balanceNumber(Node<T> node) {
		int L = depth(node.getLeft());
		int R = depth(node.getRight());
		if (L - R >= 2)
			return -1;
		else if (L - R <= -2)
			return 1;
		return 0;
	}

	private Node<T> rotateLeft(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getRight();
		Node<T> c = q.getLeft();
		Node<T> a = p.getLeft();
		Node<T> b = p.getRight();
		q = new Node<T>(q.getKey(), c, a);
		p = new Node<T>(p.getKey(), q, b);
		return p;
	}

	private Node<T> rotateRight(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getLeft();
		Node<T> c = q.getRight();
		Node<T> a = p.getLeft();
		Node<T> b = p.getRight();
		q = new Node<T>(q.getKey(), b, c);
		p = new Node<T>(p.getKey(), a, q);
		return p;
	}

	public T search(T data) {
		Node<T> local = root;
		while (local != null) {
			if (local.getKey().compareTo(data) == 0)
				return local.getKey();
			else if (local.getKey().compareTo(data) > 0)
				local = local.getLeft();
			else
				local = local.getRight();
		}
		return null;
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
		
		root.level = max(height(root.getLeft()), height(root.getRight())) + 1;
		int balance = balanceNumber(root);
		if (balance > 1 && balanceNumber(root.getLeft()) >= 0)
			return rotateRight(root);
		if (balance > 1 && balanceNumber(root.getLeft()) < 0)
		{
			root.setLeft(rotateLeft(root.getLeft()));
			return rotateRight(root);
		}
		if (balance < -1 && balanceNumber(root.getRight()) <= 0)
			return rotateLeft(root);
		if (balance < -1 && balanceNumber(root.getRight()) > 0)
		{
			root.setRight(rotateRight(root.getRight()));
			return rotateLeft(root);
		}
		return root;
	}
	
	public int max(int one, int two) {
		return (one > two) ? one : two;
	}
	
	public Node<T> minValueNode(Node<T> node)
	{
		Node<T> current = node;
		while (current.getLeft() != null)
			current = current.getLeft();

		return current;
	}
	
	public int height(Node<T> n) {
		if(n == null) {
			return 0;
		}else {
			return n.level;
		}
	}

	public String toString() {
		return root.toString();
	}
	
	public Node<T> getRoot() {
		return root;
	}
}
