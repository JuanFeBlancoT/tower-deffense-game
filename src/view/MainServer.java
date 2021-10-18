package view;

import model.GameManager;
import processing.core.PApplet;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
	}
	//object in charge of administrating the game
	GameManager gm;
	//Object in charge of the communication
	Communication com;
	
	public void settings() {
		size(1200,700);
	}
	
	public void setup() {
		//UI setup
		noStroke();
		
		//technical setup
		com = Communication.ComgetInstance();
		com.setMainServer(this);
		com.start();
		gm = new GameManager();
		
		//gm.createPlayer();
		//gm.createPlayer();
	}
	
	public void createPlayer() {
		if(gm.getPlayer1()==null) {
			gm.createPlayer();
			
		}else {
			gm.createPlayer();
			
		}
	}
	
	public void draw() {
		background(80);
		drawGmPlayerScreens();
		drawUIBaseElements();
		drawTowers();
		drawEnemies();
		
		//text(gm.getPlayer1().getHealth(), 700, 150);
	}

	private void drawEnemies() {
		if(gm.getPlayer1() != null) {
			for (int i = 0; i < gm.getPlayer1().getIncomingEnemies().size() && gm.getPlayer1().getIncomingEnemies().get(i).isAlive() ; i++) {
				//if(gm.getPlayer1().getIncomingEnemies().get(i).isAlive()) {
					fill(255);
					circle(gm.getPlayer1().getIncomingEnemies().get(i).getPosX(), gm.getPlayer1().getIncomingEnemies().get(i).getPosY(), 30);
					gm.getPlayer1().getIncomingEnemies().get(i).move();
					fill(0);
					//text(gm.getPlayer1().getIncomingEnemies().get(i).getHealth()+"; "+i+"; "+gm.getPlayer1().getIncomingEnemies().get(i).isAlive(), gm.getPlayer1().getIncomingEnemies().get(i).getPosX(), gm.getPlayer1().getIncomingEnemies().get(i).getPosY());
					//System.out.println(gm.getPlayer1().getIncomingEnemies().get(i).getHealth());
				}
				
			//}
		}
		
		if(gm.getPlayer2() != null) {
			for (int i = 0; i < gm.getPlayer2().getIncomingEnemies().size() && gm.getPlayer2().getIncomingEnemies().get(i).isAlive(); i++) {
				fill(40,40,40);
				circle(gm.getPlayer2().getIncomingEnemies().get(i).getPosX(), gm.getPlayer2().getIncomingEnemies().get(i).getPosY(), 30);
				gm.getPlayer2().getIncomingEnemies().get(i).move();
			}
		}
	}

	private void drawTowers() {
		if(gm.getPlayer1() != null) {
			for (int i = 0; i < gm.getPlayer1().getTowers().size(); i++) {
				fill(180,80,80,60);
				circle(gm.getPlayer1().getTowers().get(i).getPosX(), gm.getPlayer1().getTowers().get(i).getPosY(), gm.getPlayer1().getTowers().get(i).getAttackRange()*2);
				fill(255);
				circle(gm.getPlayer1().getTowers().get(i).getPosX(), gm.getPlayer1().getTowers().get(i).getPosY(), 20);
			}
		}
		
		if(gm.getPlayer2() != null) {
			for (int i = 0; i < gm.getPlayer2().getTowers().size(); i++) {
				fill(180,80,80);
				circle(gm.getPlayer2().getTowers().get(i).getPosX(), gm.getPlayer2().getTowers().get(i).getPosY(), gm.getPlayer2().getTowers().get(i).getAttackRange()*2);
				fill(255);
				circle(gm.getPlayer2().getTowers().get(i).getPosX(), gm.getPlayer2().getTowers().get(i).getPosY(), 20);
			}
		}
	}

	private void drawGmPlayerScreens() {
		if(gm.getPlayer1() != null) {
			
			textSize(14);
			fill(255);
			text("money player 1:\n"+gm.getPlayer1().getMoney()+"",600,275);
			text("Health player 1:\n"+gm.getPlayer1().getHealth()+"",600,320);
			fill(0);
			fill(200,100,100);
			rect(0, 0, 500, 700);
			fill(150,70,70);
			rect(100, 0, 50, 200);
			rect(100, 200, 350, 50);
			rect(400, 250, 50, 50);
			rect(200, 300, 250, 50);
			rect(200, 350, 50, 50);
			rect(200, 400, 250, 50);
			rect(400, 450, 50, 50);
			rect(50, 500, 400, 50);
			rect(50, 550, 50, 50);
			rect(50, 600, 250, 50);
			rect(250, 650, 50, 50);
		}
		
		if(gm.getPlayer2() != null) {
			
			textSize(14);
			fill(255);
			text("money player 2:\n"+gm.getPlayer2().getMoney()+"",600,360);
			text("Health player 2:\n"+gm.getPlayer2().getHealth()+"",600,400);
			fill(100,100,200);
			rect(700, 0, 500, 700);
			
			fill(70,70,150);
			rect(800, 0, 50, 200);
			rect(800, 200, 350, 50);
			rect(1100, 250, 50, 50);
			rect(900, 300, 250, 50);
			rect(900, 350, 50, 50);
			rect(900, 400, 250, 50);
			rect(1100, 450, 50, 50);
			rect(750, 500, 400, 50);
			rect(750, 550, 50, 50);
			rect(750, 600, 250, 50);
			rect(950, 650, 50, 50);
		}
	}
	
	private void drawUIBaseElements() {
		textSize(30);
		textAlign(CENTER);
		fill(255);
		text("Remaining\ntime: ", 600, 30);
		text(gm.getRemainingTime(), 600, 120);
		//coldown
		text("Next round: ", 600, 190);
		text(gm.getRoundColdDown(), 600, 230);
	}
	
	public void mousePressed() {
		//gm.getPlayer1().addTower(1, mouseX, mouseY);
	}

	public void setScreen(int i) {
		gm.setScreen(i);
	}

	public void onMessage(int x, int y, int idSener) {
		if(idSener == 1) {
			gm.getPlayer1().addTower(1, x, y);
		}else {
			gm.getPlayer2().addTower(1, x, y);
		}
	}
}
