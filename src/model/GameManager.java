package model;

public class GameManager {

	public final int ROUND_COLDOWN = 25;
	private int remainingTime;
	private int round;
	private int roundColdDown;
	
	private Player player1;
	private Player player2;
	
	public GameManager() {
		remainingTime = 251;
		roundColdDown = 0;
		round = ROUND_COLDOWN;
		
		startRound();
	}
	
	public void createPlayer() {
		if (player1 == null) {
			player1 = new Player();
		}else {
			player2 = new Player();
		}
	}
	
	public void startRound() {
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
	
	public boolean addEnemies(int playerIdentifier, int enemyType) {
		return false;
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