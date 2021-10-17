package model;

import java.util.ArrayList;

public class Player {
	
	private int health;
	int id;
	private double money;
	
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> incomingEnemies;
	
	public Player(int id) {
		health = 100;
		money = 100;
		this.id = id;
		
		towers = new ArrayList<>();
		incomingEnemies = new ArrayList<>();
	}
	
	public void addTower(int type, int posX, int posY) {
		Tower towerX = new Tower(type, posX, posY, this);
		towers.add(towerX);
	}
	
	public void removeEnemy(Enemy targeted) {
		
	}
	
	//Getters
	
	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public int getHealth() {
		return health;
	}

	public ArrayList<Enemy> getIncomingEnemies() {
		return incomingEnemies;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getId() {
		return id;
	}

	public void activateTowers() {
		System.out.println(id+"; incoming enemies size: "+incomingEnemies.size());
		System.out.println(id+"; Towers array size: "+ towers.size());
		for (int i = 0; i < towers.size(); i++) {
			towers.get(i).calculateNearestEnemy();
		}
		
	}

	public void setIncomingEnemies(ArrayList<Enemy> incomingEnemies) {
		this.incomingEnemies = incomingEnemies;
	}

	
	
	
}
