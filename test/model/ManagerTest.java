package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ManagerTest {
	
	Manager m;
	
	void setupScenary1() {
		m = new Manager();
	}
	
	@Test
	void test() {
		setupScenary1();
		
		Player playerOne = new Player(1000,"Baca",34,"Madrid",5.02,4.02,1.056,6.1,5.2);
		Player playerTwo = new Player(0001,"James",21,"Juventus",1.25,7.6,3.6,4.1,3.2);
		Player playerThree = new Player(0500,"Mosquera",19,"AP",5,4.2,6,5.1,5.2);
		m.addPlayer(playerThree);
		m.addPlayer(playerTwo);
		m.addPlayer(playerOne);
		assertEquals(m.searchName("Baca"), playerOne);
		assertEquals(m.searchName("James"), playerTwo);
		assertEquals(m.searchName("Mosquera"), playerThree);
	}
	
	@Test
	void test2() {
		setupScenary1();
		Player playerOne = new Player(1000,"Baca",34,"Madrid",5.02,4.02,1.056,6.1,5.2);
		Player playerTwo = new Player(0001,"James",21,"Juventus",1.25,7.6,3.6,4.1,3.2);
		Player playerThree = new Player(0500,"Mosquera",19,"AP",5,4.2,6,5.1,5.2);
		m.addPlayer(playerThree);
		m.addPlayer(playerTwo);
		m.addPlayer(playerOne);
		assertEquals(m.searchName("Pepe"), null);
	}
	
	@Test
	void test3() {
		setupScenary1();
		Player playerOne = new Player(1000,"Baca",34,"Madrid",5.02,4.02,1.056,6.1,5.2);
		Player playerTwo = new Player(0001,"James",21,"Juventus",1.25,7.6,3.6,4.1,3.2);
		Player playerThree = new Player(0500,"Mosquera",19,"AP",5,4.2,6,5.1,5.2);
		m.addPlayer(playerThree);
		m.addPlayer(playerTwo);
		m.addPlayer(playerOne);
		assertEquals(m.quickSearch(1, 5.02), null);
	}

}
