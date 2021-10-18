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
	
	public void addTower(int type, int posX, int posY, int origin) {
		boolean canCreate = false;
		if(type == 1 && money >= 100) {
			money-=100;
			canCreate = true;
		}else if(type == 2 && money >= 120) {
			money-=120;
			canCreate = true;
		}
		
		if(canCreate) {
			if(origin == 1) {
				Tower towerX = new Tower(type, posX, posY, this);
				towers.add(towerX);
			}else {
				Tower towerX = new Tower(type, (posX)+700, posY, this);
				towers.add(towerX);
			}
			
		}
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
		//restart the towers calculations
		for (int i = 0; i < towers.size(); i++) {
			towers.get(i).calculateNearestEnemy();
			towers.get(i).setOwner(this);
			//towers.get(i).attack();
		}
		
	}

	public void setIncomingEnemies(ArrayList<Enemy> incomingEnemies) {
		this.incomingEnemies = incomingEnemies;
	}

	public void resetTowers() {
		for (int i = 0; i < towers.size(); i++) {
			towers.get(i).settargetedEnemy(null);
		}	
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	
	
	
}
