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
		roundColdDown = ROUND_COLDOWN;
		round = 0;
		
		calculateTimeLeft();
		startRound();
	}
	
	public void createPlayer() {
		if (player1 == null) {
			player1 = new Player();
		}else {
			player2 = new Player();
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
								player1.getIncomingEnemies().add(new Enemy(1));
							}
							roundColdDown = ROUND_COLDOWN;
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
