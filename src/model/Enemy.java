package model;

public class Enemy {

	private int health;
	private int damage;
	private int speed;
	private int type;
	private int posX;
	private int posY;
	private boolean alive;
	
	private Player player;
	
	public Enemy(int type, Player p, int posX, int posY) {
		alive = true;
		player = p;
		this.type = type;
		this.posX = posX;
		this.posY = posY;;
		
		if(this.type == 1) {
			health = 18;
			damage = 4;
			speed = 3;
		}else {
			health = 22;
			damage = 6;
			speed = 2; 
		}
	}
	
	public void move() {
		if(alive) {
			if(posX%700 == 125 && posY < 225 ) {
				posY+=speed;
			}else if(posY >= 225  && posY<= 227 && posX%700 < 425) {
				posX+=speed;
			}else if(posX%700 >= 425 && posY>=225 && posY < 325) {
				posY+=speed;
			}else if(posY>=325 && posY<328 && posX%700 >225) {
				posX-=speed;
			}else if(posX%700 >=222 && posX%700 <225 && posY>325 && posY<425) {
				posY+=speed;
			}else if(posX%700 >=222 && posX%700 < 425 && posY > 425 && posY <525) {
				posX+=speed;
			}else if(posX%700 >= 425 && posY>=425 && posY < 525) {
				posY+=speed;
			}else if(posX%700 <= 428 && posY >=525 && posY <529 && posX%700> 75) {
				posX-=speed;
			}else if(posX%700 > 70 && posY >=525 && posY < 625) {
				posY+=speed;
			}else if(posX%700 > 70 && posX%700< 275 && posY >625) {
				posX+=speed;
			}else {
				posY+=speed;
			}
			
			/*
			 * 
			 * rect(100, 0, 50, 200);
			 * 
			rect(100, 200, 350, 50);
			rect(400, 250, 50, 50);
			rect(200, 300, 250, 50);
			rect(200, 350, 50, 50);
			rect(200, 400, 250, 50);
			rect(400, 450, 50, 50);
			rect(50, 500, 400, 50);
			rect(50, 550, 50, 50);
			rect(50, 600, 250, 50);
			rect(250, 650, 50, 50);*/
			
			
			
			if(posY>= 720) {
				alive = false;
				player.setHealth(player.getHealth()-damage);
			}
		}
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

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	
	
}
