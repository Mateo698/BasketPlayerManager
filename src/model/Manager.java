package model;

import java.util.ArrayList;

public class Manager {
	private ArrayList<AVLTree<StatisticData>> fastAccess; //1.Points per match 2.Rebounds per match 3.Assist 4.Stolen
	private BSTree<Player> mainData;
	
	public Manager() {
		fastAccess = new ArrayList<AVLTree<StatisticData>>();
		mainData = new BSTree<Player>();
		fastAccess.add(new AVLTree<StatisticData>());
		fastAccess.add(new AVLTree<StatisticData>());
		fastAccess.add(new AVLTree<StatisticData>());
		fastAccess.add(new AVLTree<StatisticData>());
	}

	public BSTree<Player> getMainData() {
		return mainData;
	}

	public void addPlayer(int id, String name, int age,String team, double points, double rebound, double assist, double steal, double block) {
		Player newP = new Player(id, name, age, team, points, rebound, assist, steal, block);
		mainData.insert(newP);
		fastAccess.get(0).insert(fastAccess.get(0).getRoot(), new StatisticData(id,points));
		fastAccess.get(0).insert(fastAccess.get(1).getRoot(), new StatisticData(id,rebound));
		fastAccess.get(0).insert(fastAccess.get(2).getRoot(), new StatisticData(id,assist));
		fastAccess.get(0).insert(fastAccess.get(3).getRoot(), new StatisticData(id,steal));
	}
	
	public void addPlayer(Player p) {
		mainData.insert(p);
		fastAccess.get(0).insert(fastAccess.get(0).getRoot(), new StatisticData(p.getId(),p.getPointsPerMatch()));
		fastAccess.get(0).insert(fastAccess.get(1).getRoot(), new StatisticData(p.getId(),p.getReboundPerMatch()));
		fastAccess.get(0).insert(fastAccess.get(2).getRoot(), new StatisticData(p.getId(),p.getAssistPerMatch()));
		fastAccess.get(0).insert(fastAccess.get(3).getRoot(), new StatisticData(p.getId(),p.getStealPerMatch()));
	}
	
	public Player quickSearch(int selected, double data) {
		Node<StatisticData> searched = null;
		StatisticData aux = new StatisticData(0,data);
		switch (selected) {
		case 1:
			searched = fastAccess.get(0).search(fastAccess.get(0).getRoot(), aux);
			break;

		case 2:
			searched = fastAccess.get(1).search(fastAccess.get(0).getRoot(), aux);
			break;

		case 3:
			searched = fastAccess.get(2).search(fastAccess.get(0).getRoot(), aux);
			break;

		case 4:
			searched = fastAccess.get(3).search(fastAccess.get(0).getRoot(), aux);
			break;

		default:
			break;
		}
		
		if(searched == null) {
			return null;
		}else {
			return search(searched.getKey().getIndex());	
		}
	}
	
	private Player search(int index) {
		Player aux = new Player(index);
		Player found = (mainData.search(mainData.getRoot(), aux)).getKey();
		return found;
	}
	
	public Player searchId(int data) {
		Node<Player> root = mainData.getRoot();
		if(root.getKey().getId()==data) {
			return root.getKey();
		}else {
			Player left = id(root.getLeft(),data);
			if(left != null) {
				return left;
			}
			Player right = id(root.getRight(),data);
			return right;
		}
	}
	
	private Player id(Node<Player> node,int id) {
		if(node == null) {
			return null;
		}else if(node.getKey().getId() == id) {
			return node.getKey();
		}else{
			Player left = id(node.getLeft(),id);
			if(left != null) {
				return left;
			}
			Player right = id(node.getRight(),id);
			return right;
		}
	}
	
	public Player searchName(String name) {
		Node<Player> root = mainData.getRoot();
		if(root.getKey().getName()==name) {
			return root.getKey();
		}else {
			Player left = name(root.getLeft(),name);
			if(left != null) {
				return left;
			}
			Player right = name(root.getRight(),name);
			return right;
		}
	}
	
	private Player name(Node<Player> node, String name) {
		if(node.getKey().getName().equalsIgnoreCase(name)) {
			return node.getKey();
		}else {
			Player left = name(node.getLeft(),name);
			if(left != null) {
				return left;
			}
			Player right = name(node.getRight(),name);
			return right;
		}
	}
	
	public Player searchAge(int age) {
		Node<Player> root = mainData.getRoot();
		if(root.getKey().getAge()==age) {
			return root.getKey();
		}else {
			Player left = age(root.getLeft(),age);
			if(left != null) {
				return left;
			}
			Player right = age(root.getRight(),age);
			return right;
		}
	}
	
	private Player age(Node<Player> node, int age) {
		
		return null;
	}
	
	public Player searchTeam(int selected, String team) {
		Node<Player> root = mainData.getRoot();
		if(root.getKey().getTeam().equals(team)) {
			return root.getKey();
		}else {
			Player left = team(root.getLeft(),team);
			Player right = team(root.getRight(),team);
			if(left != null) {
				return left;
			}else {
				return right;
			}
		}
	}
	
	private Player team(Node<Player> node,String team) {
		if(node == null) {
			return null;
		}else if(node.getKey().getTeam().equals(team)) {
			return node.getKey();
		}else {
			Player left = team(node.getLeft(),team);
			Player right = team(node.getRight(),team);
			if(left != null) {
				return left;
			}else {
				return right;
			}
		}
	}
	
	
	public void removePlayer(Player remove) {
		mainData.deleteKey(remove);
		fastAccess.get(0).deleteNode(fastAccess.get(0).getRoot(), new StatisticData(remove.getId(), remove.getPointsPerMatch()));
		fastAccess.get(1).deleteNode(fastAccess.get(1).getRoot(), new StatisticData(remove.getId(), remove.getReboundPerMatch()));
		fastAccess.get(2).deleteNode(fastAccess.get(2).getRoot(), new StatisticData(remove.getId(), remove.getAssistPerMatch()));
		fastAccess.get(3).deleteNode(fastAccess.get(3).getRoot(), new StatisticData(remove.getId(), remove.getStealPerMatch()));
	}
	
	public void loadData() {
		
	}
}
