package model;

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

		return height(N.left) - height(N.right);
	}

	Node insert(Node node, int key) {

		/* 1.  Perform the normal BST insertion */
		if (node == null)
			return (new Node(key));

		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else // Duplicate keys not allowed
			return node;

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left),
				height(node.right));

		/* 3. Get the balance factor of this ancestor
	              node to check whether this node became
	              unbalanced */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	// A utility function to print preorder traversal
	// of the tree.
	// The function also prints height of every node
	void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();

		/* Constructing tree given in the above figure */
		tree.root = tree.insert(tree.root, 10);
		tree.root = tree.insert(tree.root, 20);
		tree.root = tree.insert(tree.root, 30);
		tree.root = tree.insert(tree.root, 40);
		tree.root = tree.insert(tree.root, 50);
		tree.root = tree.insert(tree.root, 25);

		/* The constructed AVL Tree would be
	             30
	            /  \
	          20   40
	         /  \     \
	        10  25    50
		 */
		System.out.println("Preorder traversal" +
				" of constructed tree is : ");
		tree.preOrder(tree.root);
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
}
