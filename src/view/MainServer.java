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
	}

	private void drawEnemies() {
		if(gm.getPlayer1() != null) {
			for (int i = 0; i < gm.getPlayer1().getIncomingEnemies().size(); i++) {
				fill(40,40,40);
				circle(gm.getPlayer1().getIncomingEnemies().get(i).getPosX(), gm.getPlayer1().getIncomingEnemies().get(i).getPosY(), 30);
				gm.getPlayer1().getIncomingEnemies().get(i).move();
			}
		}
		
		if(gm.getPlayer2() != null) {
			for (int i = 0; i < gm.getPlayer2().getIncomingEnemies().size(); i++) {
				fill(40,40,40);
				circle(gm.getPlayer2().getIncomingEnemies().get(i).getPosX(), gm.getPlayer1().getIncomingEnemies().get(i).getPosY(), 30);
				gm.getPlayer2().getIncomingEnemies().get(i).move();
			}
		}
	}

	private void drawTowers() {
		if(gm.getPlayer1() != null) {
			for (int i = 0; i < gm.getPlayer1().getTowers().size(); i++) {
				fill(180,80,80);
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
			fill(200,100,100);
			rect(0, 0, 500, 700);
		}
		
		if(gm.getPlayer2() != null) {
			fill(100,100,200);
			rect(700, 0, 500, 700);
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
