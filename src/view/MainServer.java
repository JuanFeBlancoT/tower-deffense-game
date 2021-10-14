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
	}
}
