package model;

public class BSTree<T extends Comparable<T>> {
	private Node<T> root;

	BSTree()    {
		root = null;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public void insert(T key){
		root = insertRec(root, key);
	}

	public Node<T> insertRec(Node<T> root, T key){
		if (root == null){
			root = new Node<T>(key);
			return root;
		}
		if (key.compareTo(root.getKey())<0)
			root.setLeft(insertRec(root.getLeft(), key));
		else if (key.compareTo(root.getKey())>0)
			root.setRight(insertRec(root.getRight(), key));
		return root;
	}

	
	public Node<T> search(Node<T> root, T key){
	    if (root==null || root.getKey().compareTo(key)==0)
	        return root;
	    if (root.getKey().compareTo(key)<0)
	       return search(root.getRight(), key);
	    return search(root.getLeft(), key);
	}


	
	public Node<T> deleteNode(Node<T> root, T k){
        if (root == null)
            return root;
 
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key < root.key)
            root.left = deleteNode(root.left, key);
 
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
 
        // if key is same as root's key, then this is the node
        // to be deleted
        else
        {
 
            // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
 
                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                                // the non-empty child
            }
            else
            {
 
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);
 
                // Copy the inorder successor's data to this node
                root.key = temp.key;
 
                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }
 
        // If the tree had only one node then return
        if (root == null)
            return root;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
 
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
 
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
 
        return root;
	}
	
}
