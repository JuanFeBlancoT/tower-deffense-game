package model;

public class Tower {

	private int posX;
	private int posY;
	private int type;
	private int attackRange;
	private int damage;	
	
	private Player owner;
	private Enemy targetedEnemy;
	
	public Tower(int type, int posX, int posY, Player owner) {
		
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		
		if(this.type == 1) {
			attackRange = 130;
			damage = 5;
		}else {
			attackRange = 70;
			damage = 2;
		}
		
		this.owner = owner;
		attack();
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
	
	public void calculateNearestEnemy(){
		
		new Thread(
			()->{			
				try {
					while(owner.getIncomingEnemies().size() > 0) {
						int min = 2000;
						int index = 0;
						for (int i = 0; i < owner.getIncomingEnemies().size(); i++) {
							int distance = distanceBetween(owner.getIncomingEnemies().get(i).getPosX(), posX, owner.getIncomingEnemies().get(i).getPosY(), posY);
							//System.out.println(distance);
							if (distance< min) {
								min = distance;
								index = i;
									
							}
						}
					targetedEnemy = owner.getIncomingEnemies().get(index);
					System.out.println("Target index: "+ index);	
					}
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
	}
	
	public int distanceBetween(int x1, int x2, int y1, int y2) {
		int xdif = (int) Math.pow((x2-x1), 2);
		int ydif = (int) Math.pow((y2-y1), 2);
		int dif = (int) Math.sqrt((xdif+ydif));
		
		return dif;
	}
	
	public void attack() {
	new Thread(()->{
			try {
				
				if(targetedEnemy!= null && targetedEnemy.isAlive()) {
					int dif = distanceBetween(targetedEnemy.getPosX(), posX, targetedEnemy.getPosY(), posY);
					System.out.println("*** "+targetedEnemy.isAlive()+"; dif: "+dif+"; "+targetedEnemy.getHealth());
					if(targetedEnemy.isAlive() && dif <= attackRange) {
						
						targetedEnemy.setHealth(targetedEnemy.getHealth()-damage);
						if(targetedEnemy.getHealth() <= 0) {
							targetedEnemy.setAlive(false);
						}
					}
					if(type==1) {
						Thread.sleep(800);
					}else {
						Thread.sleep(1000);
					}
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}).start();		
}

	public Enemy gettargetedEnemy() {
		return targetedEnemy;
	}

	public void settargetedEnemy(Enemy targetedEnemy) {
		this.targetedEnemy = targetedEnemy;
	}
	
	
}
