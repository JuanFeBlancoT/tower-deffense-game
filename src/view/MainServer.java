package view;

import model.GameManager;
import processing.core.PApplet;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
	}

	GameManager gm;
	
	public void settings() {
		size(1200,700);
	}
	
	public void setup() {
		//UI setup
		noStroke();
		
		
		//actual game setup
		gm = new GameManager();
		gm.createPlayer();
		gm.createPlayer();
	}
	
	public void draw() {
		background(80);
		drawGmPlayerScreens();
		drawUIBaseElements();
		drawTowers();
		drawEnemies();
		
		text(gm.getPlayer1().getHealth(), 700, 150);
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
				circle(gm.getPlayer2().getTowers().get(i).getPosX(), gm.getPlayer1().getTowers().get(i).getPosY(), gm.getPlayer1().getTowers().get(i).getAttackRange()*2);
				fill(255);
				circle(gm.getPlayer2().getTowers().get(i).getPosX(), gm.getPlayer1().getTowers().get(i).getPosY(), 20);
			}
		}
	}

	private void drawGmPlayerScreens() {
		if(gm.getPlayer1() != null) {
			fill(0);
			text(gm.getPlayer1().getMoney()+"",650,250);
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
		text(gm.getRemainingTime(), 600, 50);
		//coldown
		text(gm.getRoundColdDown(), 600, 100);
	}
	
	public void mousePressed() {
		gm.getPlayer1().addTower(1, mouseX, mouseY);
	}
}
