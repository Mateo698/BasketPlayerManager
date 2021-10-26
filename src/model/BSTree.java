package model;

public class BSTree<T extends Comparable<T>> {
	Node<T> root;

	BSTree()    {
		root = null;
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

	public void inorder(){
		inorderRec(root);
	}
	
	public Node<T> search(Node<T> root, T key){
	    if (root==null || root.getKey().compareTo(key)==0)
	        return root;
	    if (root.getKey().compareTo(key)<0)
	       return search(root.getRight(), key);
	    return search(root.getLeft(), key);
	}

	public void inorderRec(Node<T> root){
		if (root != null) {
			inorderRec(root.getLeft());
			System.out.println(root.getKey());
			inorderRec(root.getRight());
		}
	}
	
	public Node<T> deleteNode(Node<T> root, T k){
	    if (root == null)
	        return root;
	    if (root.getKey().compareTo(k)>0)
	    {
	        root.setLeft(deleteNode(root.getLeft(), k));
	        return root;
	    }
	    else if (root.getKey().compareTo(k)<0)
	    {
	        root.setRight(deleteNode(root.getRight(), k));
	        return root;
	    }
	 
	    if (root.getLeft() == null)
	    {
	        Node<T> temp = root.getRight();
	        return temp;
	    }
	    else if (root.getRight() == null)
	    {
	        Node<T> temp = root.getLeft();
	        return temp;
	    }
	 
	    else{
	        Node<T> succParent = root;
	        Node<T> succ = root.getRight();
	         
	        while (succ.getLeft() != null){
	            succParent = succ;
	            succ = succ.getLeft();
	        }
	 
	        
	        if (succParent != root)
	            succParent.setLeft(succ.getRight());
	        else
	            succParent.setRight(succ.getRight());
	 
	        root.setKey(succ.getKey());
	 
	        return root;
	    }
	}
	
}
