package model;

public class Player implements Comparable<Player>{
	private int id;
	private String name;
	private int age;
	private String team;
	private double pointsPerMatch;
	private double reboundPerMatch;
	private double assistPerMatch;
	private double stealPerMatch;
	private double blockPerMatch;
	
	public Player(int id, String name, int age,String team, double points, double rebound, double assist, double steal, double block) {
		this.setId(id);
		this.setName(name);
		this.setAge(age);
		this.setTeam(team);
		setPointsPerMatch(points);
		setReboundPerMatch(rebound);
		setAssistPerMatch(assist);
		setStealPerMatch(steal);
		setBlockPerMatch(block);
	}
	
	public Player(int index) {
		this.setId(index);
	}

	public double getPointsPerMatch() {
		return pointsPerMatch;
	}

	public void setPointsPerMatch(double pointsPerMatch) {
		this.pointsPerMatch = pointsPerMatch;
	}

	public double getReboundPerMatch() {
		return reboundPerMatch;
	}

	public void setReboundPerMatch(double reboundPerMatch) {
		this.reboundPerMatch = reboundPerMatch;
	}

	public double getAssistPerMatch() {
		return assistPerMatch;
	}

	public void setAssistPerMatch(double assistPerMatch) {
		this.assistPerMatch = assistPerMatch;
	}

	public double getStealPerMatch() {
		return stealPerMatch;
	}

	public void setStealPerMatch(double stealPerMatch) {
		this.stealPerMatch = stealPerMatch;
	}

	public double getBlockPerMatch() {
		return blockPerMatch;
	}

	public void setBlockPerMatch(double blockPerMatch) {
		this.blockPerMatch = blockPerMatch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Player o) {
		if(this.id<o.getId()) {
			return -1;
		}else if(this.id>o.getId()){
			return 1;
		}else {
			return 0;
		}
	}
}
