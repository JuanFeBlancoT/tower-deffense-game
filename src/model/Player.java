package model;

import java.util.ArrayList;

public class Player {
	
	private int health;
	private double money;
	private boolean onRound;
	
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> incomingEnemies;
	
	public Player() {
		health = 100;
		money = 100;
		onRound = false;
		
		towers = new ArrayList<>();
		incomingEnemies = new ArrayList<>();
	}
	
	public void addTower(int type, int posX, int posY) {
		Tower towerX = new Tower(type, posX, posY);
		towers.add(towerX);
	}
	
	public void verifyDamage() {
		
	}
	
	public void removeEnemy(Enemy targeted) {
		
	}
	
	//Getters

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public ArrayList<Enemy> getIncomingEnemies() {
		return incomingEnemies;
	}
	
	
	
}
