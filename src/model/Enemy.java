package model;

public class Enemy {

	private int health;
	private int damage;
	private int speed;
	private int type;
	private int posX;
	private int posY;
	
	public Enemy(int type) {
		this.type = type;
		posX = 100;
		posY = 0;
		
		if(this.type == 1) {
			health = 20;
			damage = 4;
			speed = 4;
		}else {
			health = 30;
			damage = 6;
			speed = 2; 
		}
	}
	
	public void move() {
		posY+=speed;
	}

	
	//Getters and setters
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	
}
