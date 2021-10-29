package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTreeTest {
	
	AVLTree<Integer> tree;
	
	@Test
	void test() {
		tree = new AVLTree<Integer>();
		tree.add(1);
		assertTrue(tree.getRoot() != null);
	}

}
