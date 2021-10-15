package model;

public class Tower {

	private int posX;
	private int posY;
	private int type;
	private int attackRange;
	private int damage;	
	private Enemy TargetedEnemy;
	
	public Tower(int type, int posX, int posY) {
		
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		
		if(this.type == 1) {
			attackRange = 100;
			damage = 5;
		}else {
			attackRange = 60;
			damage = 2;
		}
	}

	//Getters
	
	public int getPosX() {
		return posX;
	}


	public int getPosY() {
		return posY;
	}


	public int getAttackRange() {
		return attackRange;
	}
	
	public void attack() {
		
	}

	public Enemy getTargetedEnemy() {
		return TargetedEnemy;
	}

	public void setTargetedEnemy(Enemy targetedEnemy) {
		TargetedEnemy = targetedEnemy;
	}
	
	
	
	
}
