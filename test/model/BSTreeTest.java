package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTreeTest {
	
	BSTree<Player> tree;
	
	@Test
	void test() {
		tree = new BSTree<Player>();
		Player playerOne = new Player(1000,"Baca",34,"Madrid",5.02,4.02,1.056,6.1,5.2);
		Player playerTwo = new Player(0001,"James",21,"Juventus",1.25,7.6,3.6,4.1,3.2);
		Player playerThree = new Player(0500,"Mosquera",19,"AP",5,4.2,6,5.1,5.2);
		tree.insert(playerThree);
		tree.insert(playerTwo);
		tree.insert(playerOne);
		assertEquals(tree.search(tree.getRoot(), playerThree),playerThree);
		assertEquals(tree.search(tree.getRoot(), playerTwo),playerTwo);
		assertEquals(tree.search(tree.getRoot(), playerOne),playerOne);
	}
	
	
}
