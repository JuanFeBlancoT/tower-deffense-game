package model;

import java.util.ArrayList;

public class GameManager {
	//23
	public final int ROUND_COLDOWN = 24;
	private int remainingTime;
	private int round;
	private int roundColdDown;
	
	private Player player1;
	private Player player2;
	
	public GameManager() {
		remainingTime = 251;
		roundColdDown = ROUND_COLDOWN;
		round = 0;
		
		calculateTimeLeft();
		startRound();
		
	}
	
	public void createPlayer() {
		if (player1 == null) {
			player1 = new Player(1);
		}else {
			player2 = new Player(2);
		}
	}
	
	public void calculateTimeLeft(){
		new Thread(
				()->{
					while(remainingTime > 0) {
						remainingTime--;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}).start();
	}
	
	public void startRound() {
		new Thread(
				()->{
					while(roundColdDown >= 0) {
						roundColdDown--;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if(roundColdDown == 0) {
							
							round++;
							if(player1!= null) {
								player1.getIncomingEnemies().clear();
								player2.getIncomingEnemies().clear();
								addEnemies();
								
							}
							roundColdDown = ROUND_COLDOWN;
						}
					}					
				}).start();
	}
	
	public void addEnemies() {
		new Thread(
			()->{
				ArrayList<Enemy> enemies1 = new ArrayList<>();
				ArrayList<Enemy> enemies2 = new ArrayList<>();
				int numEnem = 0;
				if(round <=4) {
					numEnem = (int) (Math.random()*round)+round;
				}else {
					numEnem = (int) (Math.random()*round)+round/2;
				}
				
				if(numEnem > 12) {
					numEnem = 12;
				}
				
				for (int i = 0; i < numEnem; i++) {
					int type = (int)((Math.random()*2)+1);
					if(type > 2) {
						type = 2;
					}
					int posY = -i*50;
					enemies1.add(new Enemy(type, player1, 125, posY));
					enemies2.add(new Enemy(type, player2, 825, posY));
					
				}

				player1.setIncomingEnemies(enemies1);
				player2.setIncomingEnemies(enemies2);
				
				player1.activateTowers();
				player2.activateTowers();
		}).start();
	}
	
	public boolean createTower(int playerIdentifier, int type, int posX, int posY) {
		return false;
	}
	
	//Getters

	public int getRemainingTime() {
		return remainingTime;
	}

	public int getRound() {
		return round;
	}

	public int getRoundColdDown() {
		return roundColdDown;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	
	
}
