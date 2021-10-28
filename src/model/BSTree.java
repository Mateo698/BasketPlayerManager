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

	
	public T search(Node<T> root, T key){
	    if (root==null) {
	    	   return null;
	    }else if(root.getKey().compareTo(key)==0){
	    	return root.getKey();
	    }
	     
	    if (root.getKey().compareTo(key)<0)
	       return search(root.getRight(), key);
	    return search(root.getLeft(), key);
	}


	
	public void deleteKey(T key) { 
		root = deleteRec(root, key); 
	}
	 
   
    private Node<T> deleteRec(Node<T> root, T key){
        if (root == null)
            return root;
        if (key.compareTo(root.getKey())<0)
            root.setLeft(deleteRec(root.getLeft(), key));
        else if (key.compareTo(root.getKey())>0)
            root.setRight(deleteRec(root.getRight(), key));
        else {
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();
            root.setKey(minValue(root.getRight()));
            root.setRight(deleteRec(root.getRight(), root.getKey()));
        }
 
        return root;
    }
    
    public T minValue(Node<T> root){
        T minv = root.getKey();
        while (root.getLeft() != null)
        {
            minv = root.getLeft().getKey();
            root = root.getLeft();
        }
        return minv;
    }
 
	
}
