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
		
		
		attackType2();
		
		
		//getTarget();
	}

	private void attackType2() {
		new Thread(
				()->{
				while(true) {
					System.out.println(owner.getIncomingEnemies().size());
					
					if(owner.getIncomingEnemies().size() > 0) {
						
						for (int i = 0; i < owner.getIncomingEnemies().size() && owner.getIncomingEnemies().get(i).isAlive(); i++) {
							int distance = distanceBetween(owner.getIncomingEnemies().get(i).getPosX(), posX, owner.getIncomingEnemies().get(i).getPosY(), posY);
							if(distance <= attackRange) {
								
								try {
									
									owner.getIncomingEnemies().get(i).setHealth(owner.getIncomingEnemies().get(i).getHealth()-damage);
									if(owner.getIncomingEnemies().get(i).getHealth()<=0) {
										owner.getIncomingEnemies().get(i).setAlive(false);
									}
									Thread.sleep(700);
								}catch(InterruptedException e) {
									
								}
								
								
							}
						}
					}
				}
			}).start();
		
	}

	/*private void getTarget() {
		
		if(owner.getIncomingEnemies().size()>0) {
			int indexTarget = owner.getIncomingEnemies().size()-1;
		}
		
	}*/

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
				
				while(owner.getIncomingEnemies().size() > 0) {
						
					int min = 2000;
					//int index = -1;
					for (int i = 0; i < owner.getIncomingEnemies().size() ; i++) {
						if(owner.getIncomingEnemies().get(i).isAlive()) {
							int distance = distanceBetween(owner.getIncomingEnemies().get(i).getPosX(), posX, owner.getIncomingEnemies().get(i).getPosY(), posY);
							
							if (distance< min) {
								min = distance;
								targetedEnemy = owner.getIncomingEnemies().get(i);
								//System.out.println(targetedEnemy == null);
							}
						}
						
					}
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
			/*		while(true) {
			System.out.println(targetedEnemy == null);
					if(targetedEnemy != null && targetedEnemy.isAlive()) {	
						try {
							targetedEnemy.setHealth(targetedEnemy.getHealth()-damage);
							if(targetedEnemy.getHealth() <= 0) {
								targetedEnemy.setAlive(false);
								
							}
							Thread.sleep(700);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}*/
		/*	while(true) {
								
				if(targetedEnemy!= null && targetedEnemy.isAlive() && distanceBetween(targetedEnemy.getPosX(), posX, targetedEnemy.getPosY(), posY) < attackRange) {
					try {
						targetedEnemy.setHealth(targetedEnemy.getHealth()-damage);
						if(targetedEnemy.getHealth() <= 0) {
							targetedEnemy.setAlive(false);
							
						}
						Thread.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				*/
			try {
				
				while(targetedEnemy!= null) {
					System.out.println("TRYING TO ATTACK????");
					int dif = distanceBetween(targetedEnemy.getPosX(), posX, targetedEnemy.getPosY(), posY);
					
					if(dif <= attackRange && targetedEnemy.isAlive()) {
						System.out.println("ATTACKED");
						targetedEnemy.setHealth(targetedEnemy.getHealth()-damage);
						if(targetedEnemy.getHealth() <= 0) {
							targetedEnemy.setAlive(false);
						}
					}
					if(type==1) {
						Thread.sleep(700);
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
		//this.targetedEnemy = targetedEnemy;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	
	
	
}
